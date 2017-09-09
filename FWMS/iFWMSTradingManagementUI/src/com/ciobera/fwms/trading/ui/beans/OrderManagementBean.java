/*****************************************************************************************************
 ** Program Name            - OrderManagementBean.java
 ** Program Description     - This class contains the logic of Order management Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.trading.ui.beans;

import com.ciobera.fwms.common.util.bean.GlobalBean;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

public class OrderManagementBean implements Serializable {
    @SuppressWarnings("compatibility:-5064459763820671720")
    private static final long serialVersionUID = -3351246699426832772L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(OrderManagementBean.class);
    
    private String userId =
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")) != null ?
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")).getUserId() : "SRINI";
    

    /**
     * Default Constructor
     */
    public OrderManagementBean() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
