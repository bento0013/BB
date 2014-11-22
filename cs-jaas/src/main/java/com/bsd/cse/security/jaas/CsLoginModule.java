/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.security.jaas;


import com.bsd.security.principal.RolePrincipal;
import com.bsd.security.principal.UserPrincipal;
import com.bsd.util.DigestUtil;
import com.bsd.util.JNDIUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import java.security.Principal;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 *
 * @author bento
 */
public class CsLoginModule implements LoginModule{

    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(CsLoginModule.class);

    //~ Instance fields ························································

//    private Authentication authen;
    private CallbackHandler callbackHandler;
    private Map<String, ?> options;
    private Map<String, ?> sharedState;
//    private String digesterConfig;
//    private String digesterRules;    
//    private Subject loginInfo;
    private String loginID;
    private String dataSourceName;
    private String digest;
    private String charset;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private char[] password;
    private boolean commitSucceeded;
    private boolean debug;
    private boolean loginSucceeded;

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @param subject DOCUMENT ME!
     * @param callbackHandler DOCUMENT ME!
     * @param sharedState DOCUMENT ME!
     * @param options DOCUMENT ME!
     *
     * @throws RuntimeException DOCUMENT ME!
     */
    @Override
    public void initialize(final Subject subject,
                           final CallbackHandler callbackHandler,
                           final Map<String, ?> sharedState,
                           final Map<String, ?> options)
    {
        try
        {
            this.subject = subject;
            this.callbackHandler = callbackHandler;
            this.sharedState = sharedState;
            this.options = options;
            this.debug = "true".equalsIgnoreCase((String) options.get("debug"));
            this.dataSourceName = (String) options.get("datasourceName");
            this.digest = (String) options.get("digest");
            this.charset = (String) options.get("charset");
//            this.digesterConfig = (String) options.get("digesterConfig");
//            this.digesterRules = (String) options.get("digesterRules");

//            String tmp = (String) options.get("ldapHost");
//
//            if (null != tmp)
//            {
//                System.setProperty("ldap.host", tmp);
//            }
//
//            tmp = (String) options.get("ldapPort");
//
//            if (null != tmp)
//            {
//                System.setProperty("ldap.port", tmp);
//            }

//            LOG.info(String.format("::initialize() ~ digesterConfig=%1$s",
//                                   digesterConfig));
//            LOG.info(String.format("::initialize() ~ digesterRules=%1$s",
//                                   digesterRules));
//
//            this.authen = AuthenticationFactory.create(digesterConfig,
//                                                       digesterRules);
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws LoginException DOCUMENT ME!
     */
    public boolean abort()
        throws LoginException
    {
        if (!loginSucceeded)
        {
            return false;
        }
        else if (loginSucceeded && !commitSucceeded)
        {
            // login succeeded but overall authen failed
            loginSucceeded = false;
            loginID = null;
            userPrincipal = null;

            clearPassword();
        }
        else
        {
            // overall authen succeeded, commit succeeded
            // but someone else's commit failed
            logout();
        }

        if (debug)
        {
            System.out.println(String.format("[DEBUG] %1$s::abort() ~ login aborted.",
                                             getClass().getName()));
        }

        return true;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws LoginException DOCUMENT ME!
     */
    @Override
    public boolean commit()
        throws LoginException
    {
        if (!loginSucceeded)
        {
            return false;
        }

        Set<Principal> principals = subject.getPrincipals();

        userPrincipal = new UserPrincipal(loginID.toUpperCase());
        principals.add(userPrincipal);
        try
        {
            QueryRunner runner = new QueryRunner((DataSource)JNDIUtils.locate(this.dataSourceName));
            List<Long> roles = runner.query(
                    " select function_id from cs_function_role function " +
                    " inner join cs_role_user role on function.role_id = role.role_id " +
                    " inner join cs_user users on role.user_id = users.user_id and users.username = ? "
                    , new ResultSetHandler<List<Long>>() {

                @Override
                public List<Long> handle(ResultSet rs) throws SQLException {
                    List<Long> roles = new ArrayList<Long>();
                    while(rs.next())
                    {
                        roles.add(rs.getLong(1));
                    }
                    return roles;
                }
            }, loginID.toUpperCase());
            
            if(roles != null && !roles.isEmpty())
            {
                for(Long role : roles)
                {                    
                    RolePrincipal rolePrincipal = new RolePrincipal(String.valueOf(role));
                    principals.add(rolePrincipal);
                }
            }
        }
        catch (NamingException ex) {
            throw new LoginException(ex.toString());
        }
        catch(SQLException e)
        {
            throw new LoginException(e.toString());
        }

        clearPassword();

        loginID = null;
        password = null;

        return (commitSucceeded = true);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws LoginException DOCUMENT ME!
     * @throws FailedLoginException DOCUMENT ME!
     */
    @Override
    public boolean login()
        throws LoginException
    {
        if (null == this.callbackHandler)
        {
            throw new LoginException("No callback handler available!");
        }

        Callback[] callbacks =
            {
                new NameCallback("Login ID: "),
                new PasswordCallback("Password: ", false)
            };

        try
        {
            callbackHandler.handle(callbacks);
            loginID = ((NameCallback) callbacks[0]).getName();            

            char[] tmppasswd = ((PasswordCallback) callbacks[1]).getPassword();

            if (null == tmppasswd)
            {
                tmppasswd = new char[0];
            }

            password = new char[tmppasswd.length];
            System.arraycopy(tmppasswd, 0, password, 0, tmppasswd.length);
            ((PasswordCallback) callbacks[1]).clearPassword();
            System.out.println("String.valueOf(password) = '"+String.valueOf(password)+"'");
            String hashPassword = DigestUtil.hashPassword(digest,String.valueOf(password),charset);
            QueryRunner runner = new QueryRunner((DataSource)JNDIUtils.locate(this.dataSourceName));            
//            Integer count = runner.query("select count(1) from cs_user where username = ? and password = ? ", new ResultSetHandler<Integer>() {
//                @Override
//                public Integer handle(ResultSet rs) throws SQLException {
//                    if(rs.next())
//                    {
//                        return rs.getInt(1);
//                    }
//                    return 0;
//                }
//            }, loginID.toUpperCase(), hashPassword);

            String passwordChk = hashPassword.isEmpty()?"select username,disabled from cs_user where username = ? and password is null":"select username,disabled from cs_user where username = ? and password = ? ";
            System.out.println("passwordChk = "+passwordChk);
            System.out.println("hashPassword.isEmpty() = "+hashPassword.isEmpty());
            System.out.println("hashPassword = "+hashPassword);
            List<Object> results = runner.query(passwordChk, new ResultSetHandler<List<Object>>() {
                @Override
                public List<Object> handle(ResultSet rs) throws SQLException {
                    List<Object> results = new ArrayList<Object>();
                    if(rs.next())
                    {                        
                        results.add(new String(rs.getString(1)));
                        results.add(new Boolean(rs.getBoolean(2)));
                        return results;
                    }
                    return results;
                }
            }, loginID.toUpperCase(), hashPassword);

            if(results == null || results.isEmpty())
            {
                throw new LoginException("Incorrect Username And/Or Password");
            }           
            
            
        }
        catch (SQLException ex) {
            throw new LoginException(ex.toString());        
        }
        catch (NamingException ex) {
            throw new LoginException(ex.toString());
        }
        catch (final IOException e)
        {
            throw new LoginException(e.toString());
        }
        catch (final UnsupportedCallbackException e)
        {
            throw new LoginException(e.toString());
        }

        if (debug)
        {
            System.out.println(String.format("[DEBUG] %1$s::login() ~ user '%2$s' is attempting to login...",
                                             getClass().getName(), loginID));
        }
        

        //TODO implement login
        

        return (loginSucceeded = true);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws LoginException DOCUMENT ME!
     */
    public boolean logout()
        throws LoginException
    {
        subject.getPrincipals().remove(userPrincipal);
        clearPassword();
        loginID = null;
        userPrincipal = null;
        loginSucceeded = (commitSucceeded = false);

        return true;
    }

    private void clearPassword()
    {
        if (null != password)
        {
            Arrays.fill(password, (char) 0x0);
            password = null;
        }

        if (debug)
        {
            System.out.println(String.format("[DEBUG] %1$s::clearPassword() ~ zeroes-out password stored in memory.",
                                             getClass().getName()));
        }
    }
}

