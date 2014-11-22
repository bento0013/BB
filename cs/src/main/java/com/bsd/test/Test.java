/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.test;

import com.bsd.util.DigestUtil;

/**
 *
 * @author thanasith
 */
public class Test {
    public static void main(String args[])
    {
        System.out.println(DigestUtil.hashPassword("SHA-256", "cs!2345678", "UTF-8"));
    }
}
