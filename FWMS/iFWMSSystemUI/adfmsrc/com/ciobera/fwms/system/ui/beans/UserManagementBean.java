/*****************************************************************************************************
 ** Program Name            - UserManagementBean.java
 ** Program Description     - This class contains the logic of User management Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.system.ui.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class UserManagementBean implements Serializable {
    @SuppressWarnings("compatibility:8569241378204144218")
    private static final long serialVersionUID = 1L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(UserManagementBean.class);

    /**
     *  Default constructor.
     */
    public UserManagementBean() {
        super();
    }
}
