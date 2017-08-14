/*****************************************************************************************************
 ** Program Name            - DashboardBean.java
 ** Program Description     - This class contains the logic of Dashboard Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.trading.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class DashboardBean implements Serializable {
    @SuppressWarnings("compatibility:1511816040401172361")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(DashboardBean.class);

    /**
     *  Default constructor.
     */
    public DashboardBean() {
        super();
    }
}
