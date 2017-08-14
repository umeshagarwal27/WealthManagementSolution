/*****************************************************************************************************
 ** Program Name            - UserLogBean.java
 ** Program Description     - This class contains the logic of User Log Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.system.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class UserLogBean implements Serializable {
    @SuppressWarnings("compatibility:-2088177602380036604")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(UserLogBean.class);

    /**
     *  Default constructor.
     */
    public UserLogBean() {
        super();
    }
}
