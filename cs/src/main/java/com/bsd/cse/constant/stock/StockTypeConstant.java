/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.constant.stock;

import com.bsd.cse.app.stock.StockTypeCore;
import com.bsd.cse.model.stock.StockType;
import java.util.List;

/**
 *
 * @author bento
 */
public class StockTypeConstant {
    public final static Long INCOMING_TYPE_ID = 1L;
    public final static Long OUTGOING_TYPE_ID = 2L;
    private static List<StockType> stockTypes;

    public static List<StockType> getStockTypes() throws Exception {
        if(stockTypes == null)
        {
            stockTypes = StockTypeCore.getStockTypes();
        }
        return stockTypes;
    }
}
