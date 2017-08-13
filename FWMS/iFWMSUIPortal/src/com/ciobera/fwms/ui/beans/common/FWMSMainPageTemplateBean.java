/*****************************************************************************************************
 ** Program Name            - FWMSMainPageTemplateBean.java
 ** Program Description     - This class contains the logic to handle page template level navigations etc.
 ** Date written            - 
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.ui.beans.common;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import oracle.adf.share.logging.ADFLogger;


public class FWMSMainPageTemplateBean implements Serializable{
    @SuppressWarnings("compatibility:2593146772368353432")
    private static final long serialVersionUID = 1L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(FWMSMainPageTemplateBean.class);
    
    /**
     * Default constructor.
     */
    public FWMSMainPageTemplateBean() {
        super();
    }
    
    /**
     * This method is called when the user clicks on Accounting Module link in Accounting Module Page
     * @return
     */
    public String navigateToAccountingModule() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("navigation", "adfMenu_AccountingModule");
//        if (!isSkipDirtyCheck() && checkAMDirty()) {
//            return null;
//        }
        return "adfMenu_AccountingModule";
    }
    
    
}
