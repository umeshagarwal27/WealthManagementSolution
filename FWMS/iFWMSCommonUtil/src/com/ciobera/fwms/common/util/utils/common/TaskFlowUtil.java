/*****************************************************************************************************
 ** Program Name            - TaskFlowUtil.java
 ** Program Description     - This class contains the TaskFLow Utility Methods.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.utils.common;


import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;

public class TaskFlowUtil {
    public TaskFlowUtil() {
        super();
    }

    public static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(TaskFlowUtil.class);
    // Name of the DataControl used in the respective taskflow
    private String dataControlName;


    public void invokeTaskFlowInitializer() {
    }

    /**
     * This method is executed when a bounded taskflow exits and
     * rollbacks the user changes if any.
     */
    public void invokeTaskFlowFinalizer() {

        ApplicationModule am = null;

        am = iFWMSUtil.getErpRootAM();
        if (am != null && am.getTransaction().isDirty()) {
            am.getTransaction().rollback();
        }

    }


}
