/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.common.catalog;

import java.util.Locale;

/**
 *
 * @author bento
 */
public class LanguageCatalog {

    public static String ENGLISH = "E";
    public static String THAI = "T";
    public static Locale THAI_LOCALE= new Locale("th","TH");
    public static Locale ENGLISH_LOCALE= Locale.US;

    public static Locale getLocale(String langType)
    {
        if(ENGLISH.equals(langType))
        {
            return ENGLISH_LOCALE;
        }

        if(THAI.equals(langType))
        {
            return THAI_LOCALE;
        }

        return ENGLISH_LOCALE;
    }
}
