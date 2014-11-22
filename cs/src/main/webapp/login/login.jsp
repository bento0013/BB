<%-- 
    Document   : login
    Created on : May 22, 2011, 9:24:22 PM
    Author     : bento
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <form id="security_check" action="j_security_check" method="post">
            Username : <input type="text" name="j_username"/>
            Password : <input type="password" name="j_password"/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
