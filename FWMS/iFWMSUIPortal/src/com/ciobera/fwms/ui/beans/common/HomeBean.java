/*****************************************************************************************************
 ** Program Name            - HomeBean.java
 ** Program Description     - This class contains the logic for Home Page.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.ui.beans.common;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.ui.pattern.dynamicShell.TabContext;


public class HomeBean implements Serializable {
    @SuppressWarnings("compatibility:-5778377143055031128")
    private static final long serialVersionUID = 1L;

    private static final String STOCK_INFORMATION_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/system/ui/taskflows/stock-information-task-flow-definition.xml#stock-information-task-flow-definition";
    private static final String STOCK_SETUP_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/system/ui/taskflows/stock-setup-task-flow-definition.xml#stock-setup-task-flow-definition";
    private static final String USER_LOG_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/system/ui/taskflows/user-log-task-flow-definition.xml#user-log-task-flow-definition";
    private static final String USER_MANAGEMENT_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/system/ui/taskflows/user-management-task-flow-definition.xml#user-management-task-flow-definition";
    private static final String DASHBOARD_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/dashboard-task-flow-definition.xml#dashboard-task-flow-definition";
    private static final String ORDER_MANAGEMENT_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/order-management-task-flow-definition.xml#order-management-task-flow-definition";
    private static final String TRADE_BOOK_TASKFLOWID =
        "/WEB-INF/com/ciobera/fwms/trading/ui/taskflows/trade-book-task-flow-definition.xml#trade-book-task-flow-definition";
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(HomeBean.class);
    private String asdf;

    /**
     *  Default Constructor
     */
    public HomeBean() {
        super();
    }

    /**
     * This method is called when the user clicks on Accounting Module link in Accounting Module Page
     * @return
     */
    public String navigateToAccountingModule() {
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("navigation", "adfMenu_AccountingModule");
        //        if (!isSkipDirtyCheck() && checkAMDirty()) {
        //            return null;
        //        }
        return "adfMenu_AccountingMlOodule";
    }

    public void launchStockInformationTF(ActionEvent actionEvent) {
        _launchActivity("Stock Information", STOCK_INFORMATION_TASKFLOWID, false);
    }

    public void launchStockSetupTF(ActionEvent actionEvent) {
        _launchActivity("Stock Setup", STOCK_SETUP_TASKFLOWID, false);
    }

    public void launchUserManagementTF(ActionEvent actionEvent) {
        _launchActivity("User Management", USER_MANAGEMENT_TASKFLOWID, false);
    }

    public void launchUserLogTF(ActionEvent actionEvent) {
        _launchActivity("User Log", USER_LOG_TASKFLOWID, false);
    }

    public void launchDashboardTF(ActionEvent actionEvent) {
        _launchActivity("Dashboard", DASHBOARD_TASKFLOWID, false);
    }

    public void launchOrderManagementTF(ActionEvent actionEvent) {
        _launchActivity("Order Management", ORDER_MANAGEMENT_TASKFLOWID, false);
    }

    public void launchTradeBookTF(ActionEvent actionEvent) {
        _launchActivity("Trade Book", TRADE_BOOK_TASKFLOWID, false);
    }


    private void _launchActivity(String title, String taskflowId, boolean newTab) {
        try {
            if (newTab) {
                TabContext.getCurrentInstance().addTab(title, taskflowId);
            } else {
                TabContext.getCurrentInstance().addOrSelectTab(title, taskflowId);
            }
        } catch (TabContext.TabOverflowException toe) {
            // causes a dialog to be displayed to the user saying that there are
            // too many tabs open - the new tab will not be opened...
            toe.handleDefault();
        }
    }
}
