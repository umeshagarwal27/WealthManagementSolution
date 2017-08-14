/*****************************************************************************************************
 ** Program Name            - OrderManagementBean.java
 ** Program Description     - This class contains the logic of Order management Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.trading.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class OrderManagementBean implements Serializable {
    @SuppressWarnings("compatibility:-6256500565093724974")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(OrderManagementBean.class);

    /**
     *  Default constructor.
     */
    public OrderManagementBean() {
        super();
    }
}
