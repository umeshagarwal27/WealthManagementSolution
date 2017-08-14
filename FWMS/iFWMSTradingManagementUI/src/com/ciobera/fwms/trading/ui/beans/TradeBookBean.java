/*****************************************************************************************************
 ** Program Name            - TradeBookBean.java
 ** Program Description     - This class contains the logic of Trade Book Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.trading.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class TradeBookBean implements Serializable {
    @SuppressWarnings("compatibility:8973619606005074989")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(TradeBookBean.class);

    /**
     *  Default constructor.
     */
    public TradeBookBean() {
        super();
    }
}
