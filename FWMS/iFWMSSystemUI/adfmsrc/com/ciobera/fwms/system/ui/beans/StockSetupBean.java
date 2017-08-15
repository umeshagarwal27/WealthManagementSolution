/*****************************************************************************************************
 ** Program Name            - StockSetupBean.java
 ** Program Description     - This class contains the logic of Stock Setup Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.system.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

public class StockSetupBean implements Serializable {
    @SuppressWarnings("compatibility:3262679831039105241")
    private static final long serialVersionUID = 2L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(StockSetupBean.class);

    /**
     *  Default constructor.
     */
    public StockSetupBean() {
        super();
    }
}
