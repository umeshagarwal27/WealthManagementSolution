/*****************************************************************************************************
 ** Program Name            - HomeBean.java
 ** Program Description     - This class contains the logic for Home Page.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.ui.beans.common;

import com.ciobera.fwms.common.util.bean.GlobalBean;
import com.ciobera.fwms.common.util.handlers.iFWMSPhaseListener;
import com.ciobera.fwms.common.util.logger.LoggingUtil;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.IOException;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.ui.pattern.dynamicShell.TabContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.util.ComponentReference;


public class HomeBean extends iFWMSPhaseListener implements Serializable {
    @SuppressWarnings("compatibility:7482262142072887721")
    private static final long serialVersionUID = 1L;
    private String tabTitle;
    private String tabTaskFlowId;
    private ComponentReference errorMessagePopupBinding;
    private String errorMessage;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(HomeBean.class);
    
    /**
     *  Default Constructor
     */
    public HomeBean() {
        super();
    }

    /**
     * Helper method to execute Method.
     * @param methodName
     */
    private Map executeMethod(String methodName) {
        Map resultMap = new HashMap();
        try {
            OperationBinding executeMethodOP = ADFUtil.findOperationBinding(methodName);
            if (executeMethodOP != null) {
                executeMethodOP.execute();
                if (executeMethodOP.getErrors().size() != 0) {
                    LoggingUtil.logErrorMessages(LOGGER,
                                                 "An unexpected error occured inside LoginBean.executeMethod() : " +
                                                 methodName + " . Please contact your system administrator.");
                } else {
                    resultMap = (Map) executeMethodOP.getResult();
                }

            }
        } catch (Exception e) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Exception inside LoginBean.executeMethod() : " + methodName + e.toString());
        }
        return resultMap;
    }

    /**
     *  Helper method to navigate to Login page.
     */
    private void navigateToLoginPage() {
        String loginUrl = "http://localhost:7101/iWMSUIPortal/faces/com/ciobera/fwms/ui/pages/global/login.jspx";
        try {
            redirectPage(loginUrl);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Helper method to navigate to the urlPath provided.
     * @param urlPath
     * @throws IOException
     */
    private void redirectPage(String urlPath) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext exctx = facesContext.getExternalContext();
        exctx.redirect(urlPath);
    }

    /**
     * Helper method to launch Error Popup with errorMessage passed to it.
     * @param errorMessage
     */
    private void displayErrorPopup(String errorMessage) {
        if (getErrorMessagePopupBinding() != null) {
            setErrorMessage(errorMessage);
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getErrorMessagePopupBinding().show(hints);
        }
    }

    @Override
    public void onPageLoad() {
        super.onPageLoad();
        GlobalBean globalBean = (GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}");
        if (globalBean == null || globalBean.getUserId() == null) {
            navigateToLoginPage();
            LoggingUtil.logErrorMessages(LOGGER, "Global Info session Bean is null.");
            return;
        }
        Map resultMap = executeMethod("findMainMenuByUserId");
        if (resultMap != null) {
            if (!"SUCCESS".equalsIgnoreCase((String) resultMap.get("RESP_CODE"))) {
                displayErrorPopup((String) resultMap.get("RESP_MESSAGE"));
                LoggingUtil.logErrorMessages(LOGGER, (String) resultMap.get("RESP_MESSAGE"));
                return;
            }
        }
    }

    /**
     * This method is called when the user clicks on Screen Name in the Navigation window.
     * @param actionEvent
     */
    public void onScreenNameClick(ActionEvent actionEvent) {
        if (tabTitle != null && !"".equals(tabTitle) && tabTaskFlowId != null && !"".equals(tabTaskFlowId))
            _launchActivity(tabTitle, tabTaskFlowId, false);
    }

    /**
     * Helper method to launch individual taskflow in different tabs in Home page.
     * @param title
     * @param taskflowId
     * @param newTab
     */
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

    /**
     * Depending on the tree node selection show the corresponding attributes
     * @param selectionEvent
     */
    public void onTreeSelection(SelectionEvent selectionEvent) {
        ADFUtil.invokeEL("#{bindings.FWMSMainMenu.treeModel.makeCurrent}", new Class[] { SelectionEvent.class },
                         new Object[] { selectionEvent });
        RichTree tree = (RichTree) selectionEvent.getSource(); // get the tree component from the event
        TreeModel model = (TreeModel) tree.getValue();
        //get selected nodes
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator rksIterator = rowKeySet.iterator();
        //Validating for single select only. Need to check for multiselect
        while (rksIterator.hasNext()) {
            List key = (List) rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel) tree.getValue();
            treeBinding = (JUCtrlHierBinding) collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row row = nodeBinding.getRow();
            if (row != null) {
                tabTitle = (String) row.getAttribute("WmmDesc");
                tabTaskFlowId = (String) row.getAttribute("TaskFlowId");
            }
        }
    }

    /**
     * This method is called when the user clicks on Log out button.
     * This method invalidates the session and redirects the user to Login Page.
     * @param actionEvent
     */
    public void onLogout(ActionEvent actionEvent) {
        GlobalBean globalBean = (GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}");
        globalBean.setUserId(null);
        globalBean.setUserName(null);
        globalBean.setEmailAddress(null);
        globalBean.setExpiryDays(null);
        globalBean.setIsUserBlocked(null);
        globalBean.setLastLogin(null);
        globalBean.setLastPasswordChange(null);
        globalBean.setStatus(null);
        ADFUtil.setEL("#{GlobalBean}", null);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext exctx = facesContext.getExternalContext();
        exctx.invalidateSession();
        navigateToLoginPage();
    }

    /**
     * This method is called when the user clicks on OK button in Error Message popup.
     * This method closes the Error Message Dialog
     * @param dialogEvent
     */
    public void errorMessageDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if (getErrorMessagePopupBinding() != null) {
                getErrorMessagePopupBinding().hide();
            }
        }
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTaskFlowId(String tabTaskFlowId) {
        this.tabTaskFlowId = tabTaskFlowId;
    }

    public String getTabTaskFlowId() {
        return tabTaskFlowId;
    }

    public void setErrorMessagePopupBinding(RichPopup errorMessagePopupBinding) {
        this.errorMessagePopupBinding = ComponentReference.newUIComponentReference(errorMessagePopupBinding);
    }

    public RichPopup getErrorMessagePopupBinding() {
        if (errorMessagePopupBinding != null) {
            return (RichPopup) errorMessagePopupBinding.getComponent();
        }
        return null;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
