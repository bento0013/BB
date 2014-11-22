/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.constant;

import com.bsd.cse.common.Configuration;

/**
 *
 * @author bento
 */
public interface PagingConstant {
    public final static Integer PAGING_SIZE = Configuration.getInt("cs.paging.size",100);
}
