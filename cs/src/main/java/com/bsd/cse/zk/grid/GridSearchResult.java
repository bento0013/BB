/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.grid;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thanasith
 */
public class GridSearchResult<E> implements Serializable{
    private List<E> list;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
        
}
