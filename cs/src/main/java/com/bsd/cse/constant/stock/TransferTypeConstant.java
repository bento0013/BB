/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.constant.stock;

import com.bsd.cse.app.stock.TransferTypeCore;
import com.bsd.cse.model.stock.TransferType;
import java.util.List;

/**
 *
 * @author bento
 */
public class TransferTypeConstant {    
    private static List<TransferType> incomingTypes;
    private static List<TransferType> outgoingTypes;

    public static List<TransferType> getIncomingTypes(Long materialType) throws Exception {
        incomingTypes = TransferTypeCore.getTranferTypes(StockTypeConstant.INCOMING_TYPE_ID,materialType);
        
        return incomingTypes;
    }

    public static List<TransferType> getOutgoingTypes(Long materialType) throws Exception {
        
        outgoingTypes = TransferTypeCore.getTranferTypes(StockTypeConstant.OUTGOING_TYPE_ID,materialType);
        
        return outgoingTypes;
    }


}
