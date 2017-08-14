/*****************************************************************************************************
 ** Program Name            - StockInformationBean.java
 ** Program Description     - This class contains the logic of Stock Information Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.system.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class StockInformationBean implements Serializable {
    @SuppressWarnings("compatibility:-4516352839573023390")
    private static final long serialVersionUID = 1L;


    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(StockInformationBean.class);
    
    /**
     *  Default constructor.
     */
    public StockInformationBean() {
        super();
    }
}
