/**
 * Package : com.arg.zktheme
 * File : ThemeProvider.java
 * Creator : Wirapong Chanto
 * Date : Aug 27, 2010 10:30:20 AM
 *
 * Copyright Notice:
 * Copyright (C) 2010 Advanced Research Group Co., Ltd.
 * All rights reserved. ARG PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms.
 */

package com.bsd.zktheme;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.zkoss.zk.ui.Execution;

/**
 * @author <a herf="wirapongc@ar.co.th">Wirapong Chanto</a>
 */
public class ThemeProvider implements org.zkoss.zk.ui.util.ThemeProvider
{
	private String themeName, fileList;

    public ThemeProvider() {
        try {
            InputStream is = getClass().getResourceAsStream("/zkthemer.properties");
            if (is == null)
                throw new RuntimeException("Cannot find zkthemer.properties");
            Properties prop = new Properties();
            prop.load(is);
            themeName = (String) prop.get("theme");
            fileList = (String) prop.get("fileList");
            
            if (themeName == null) {
                throw new RuntimeException("zkthemer.properties found, but missing 'theme' entry");
            }
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public Collection getThemeURIs(Execution exec, List uris) {

        List newUris = new ArrayList(uris);
        
        for (Object object : newUris) {
             String uri = (String) object;
             if (uri.startsWith("~./")) {
//                 uri = "/bi/" + themeName + "/" + uri.substring(3);
                 uri = "~./" + themeName + "/" + uri.substring(3);
             }
             uris.add(uri);
        }
        return uris;
    }

    @Override
    public String beforeWCS(Execution exec, String uri) {
        return uri;
    }

    @Override
    public String beforeWidgetCSS(Execution exec, String uri) {
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        
        if (!(fileList.indexOf(fileName) < 0 ))
//            uri = (new StringBuilder("/")).append(themeName).append("/")
//                    .append(uri.substring(3)).toString();
        
            uri = (new StringBuilder("~./")).append(themeName).append("/")
                    .append(uri.substring(3)).toString();
        return uri;
    }

    @Override
    public int getWCSCacheControl(Execution exec, String uri) {
        return -1;//safe to cache
    }
}
