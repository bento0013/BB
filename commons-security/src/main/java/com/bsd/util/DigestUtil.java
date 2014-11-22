/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bento
 */
public class DigestUtil {
    private static Log LOG = LogFactory.getLog(DigestUtil.class);
    private static String SHA1 = "SHA-1";
    private static String SHA256 = "SHA-256";
    private static String SHA512 = "SHA-512";
    private static String CHARSET_UTF8 = "UTF-8";
    public static String hashPassword(String algr,String text,String charset)
    {
        try
        {
           byte[] input = text.getBytes(charset);
            MessageDigest md = MessageDigest.getInstance(algr);
            byte[] encbyte = md.digest(input);
           return Hex.encodeHexString(encbyte);
        }catch(Exception e)
        {
            LOG.warn(e.getMessage(),e);
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        DigestUtil digest = new DigestUtil();
        System.out.println(digest.hashPassword(SHA256, "password", CHARSET_UTF8));
    }
}
