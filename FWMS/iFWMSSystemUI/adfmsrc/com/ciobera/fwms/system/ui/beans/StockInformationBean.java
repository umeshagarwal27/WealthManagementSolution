/*****************************************************************************************************
 ** Program Name            - StockInformationBean.java
 ** Program Description     - This class contains the logic of Stock Information Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.system.ui.beans;

import com.ciobera.fwms.common.util.bean.GlobalBean;
import com.ciobera.fwms.common.util.logger.LoggingUtil;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.util.ComponentReference;


public class StockInformationBean implements Serializable {
    @SuppressWarnings("compatibility:2440747257736181913")
    private static final long serialVersionUID = 2L;
    private String mode;
    private String errorMessage;
    private String confirmationMessage;
    private String userId =
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")) != null ?
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")).getUserId() : "SRINI";
    private String fileName;
    private ComponentReference confirmationPopupBinding;
    private ComponentReference addUpdateStockPopupBinding;
    private ComponentReference errorMessagePopupBinding;
    private ComponentReference confirmMessagePopupBinding;
    private static final String EXCEL_EXTENSION = ".xls";
    private static final String EXCEL_EXPORT_FILE_NAME = "Export_Stocks_";
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(StockInformationBean.class);

    /**
     *  Default constructor.
     */
    public StockInformationBean() {
        super();
    }

    /**
     * Helper method which returns current Date in YYYY-MM-dd format.
     * @return
     */
    public String getMindate() {
        try {
            Calendar cal = Calendar.getInstance();
            java.util.Date date = cal.getTime();
            DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                                                 "An unexpected error occured inside StockInformationBean.executeMethod() : " +
                                                 methodName + " . Please contact your system administrator.");
                } else {
                    resultMap = (Map) executeMethodOP.getResult();
                }
            }
        } catch (Exception e) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Exception inside StockInformationBean.executeMethod() : " + methodName + e.toString());
        }
        return resultMap;
    }

    /**
     * Helper method to execute Method.
     * @param methodName
     * @param operationFlag
     */
    private Boolean executeMethod(String methodName, Boolean operationFlag) {
        Boolean resultFlag = Boolean.FALSE;
        try {
            OperationBinding executeMethodOP = ADFUtil.findOperationBinding(methodName);
            if (executeMethodOP != null) {
                executeMethodOP.execute();
                if (executeMethodOP.getErrors().size() != 0) {
                    LoggingUtil.logErrorMessages(LOGGER,
                                                 "An unexpected error occured inside StockInformationBean.executeMethod() : " +
                                                 methodName + " . Please contact your system administrator.");
                } else {
                    resultFlag = Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Exception inside StockInformationBean.executeMethod() : " + methodName +
                                         e.toString());
        }
        return resultFlag;
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

    /**
     * Helper method to launch Confirmation Popup with confirmation Message passed to it.
     * @param errorMessage
     */
    private void displayConfirmationPopup(String confirmationMessage) {
        if (getConfirmMessagePopupBinding() != null) {
            setConfirmationMessage(confirmationMessage);
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getConfirmMessagePopupBinding().show(hints);
        }
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

    /**
     * This method is called when the user clicks on OK button in Confirm Message popup.
     * This method closes the Confirm Message Dialog
     * @param dialogEvent
     */
    public void confirmMessageDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if (getConfirmMessagePopupBinding() != null) {
                getConfirmMessagePopupBinding().hide();
            }
        }
    }

    /**
     * This method is called when the user clicks on Add button in Stock Information Screen.
     * This method launches Add/Edit popup for entering a new Stock.
     * @param actionEvent
     */
    public void onAddStock(ActionEvent actionEvent) {
        if (!executeMethod("CreateInsert", true)) {
            displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        } else {
            setMode("CREATE");
            if (getAddUpdateStockPopupBinding() != null) {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getAddUpdateStockPopupBinding().show(hints);
            }
        }
    }

    /**
     * This method is called when the user clicks on Delete button from Stock Information popup.
     * This method launches confirmation Dialog which prompts user for delete confirmation
     * @param actionEvent
     */
    public void onDeleteStock(ActionEvent actionEvent) {
        if (getConfirmationPopupBinding() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            setConfirmationMessage(ADFUtil.getUIBundleMsg("ARE_YOU_SURE_TO_DELETE_STOCK"));
            getConfirmationPopupBinding().show(hints);
        }
    }

    public void deleteConfirmationDialogListener(DialogEvent dialogEvent) {
        if (getConfirmationPopupBinding() != null) {
            getConfirmationPopupBinding().hide();
        }
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.yes) {
            if (!executeMethod("Delete", true)) {
                displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
                return;
            } else {
                if (!executeMethod("Commit", true)) {
                    displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
                    return;
                } else {
                    if (getAddUpdateStockPopupBinding() != null) {
                        getAddUpdateStockPopupBinding().hide();
                    }
                    displayConfirmationPopup(ADFUtil.getUIBundleMsg("STOCK_DELETED_SUCCESSFULLY"));
                }
            }
        }
    }

    /**
     * This method is called when the user clicks on Edit button in Stock Information Screen.
     * This method launches Add/Edit popup for updating an existing stock.
     * @param actionEvent
     */
    public void onEditStock(ActionEvent actionEvent) {
        setMode("EDIT");
        if (!executeMethod("executeEmptyRowSetBondCoupen", true)) {
            displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (getAddUpdateStockPopupBinding() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getAddUpdateStockPopupBinding().show(hints);
        }
    }

    /**
     * This method is called to save all the changes made on Stock Information Popup.
     * This method calls Commit operation to save all changes to DB.
     * @param actionEvent
     */
    public void onSave(ActionEvent actionEvent) {
        Map resultMap = executeMethod("updateProductRecord");
        if (resultMap != null && "SUCCESS".equalsIgnoreCase((String) resultMap.get("RESP_CODE"))) {
            if ("CREATE".equalsIgnoreCase(mode)) {
                displayConfirmationPopup(ADFUtil.getUIBundleMsg("STOCK_ADDED_SUCCESSFULLY"));
            } else {
                displayConfirmationPopup(ADFUtil.getUIBundleMsg("STOCK_UPDATE_SUCCESSFULLY"));
            }
            setMode("EDIT");
        } else {
            displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
        }
    }

    /**
     * This method is called when the user clicks on approve button.
     * This method approves the selected stock in DB.
     * @param actionEvent
     */
    public void onApproveStock(ActionEvent actionEvent) {
        AttributeBinding enteredByAttrBind = ADFUtil.findControlBinding("WmsEnterUid");
        if (enteredByAttrBind != null) {
            String enteredUid = (String) enteredByAttrBind.getInputValue();
            if (userId.equalsIgnoreCase(enteredUid)) {
                displayErrorPopup(ADFUtil.getUIBundleMsg("APPROVE_ACCOUNT_NOT_POSSIBLE"));
                return;
            }
        }
        setMode("APPROVE");
        Map resultMap = executeMethod("updateProductRecord");
        if (resultMap != null && "SUCCESS".equalsIgnoreCase((String) resultMap.get("RESP_CODE"))) {
            displayConfirmationPopup(ADFUtil.getUIBundleMsg("STOCK_APPROVED_SUCCESSFULLY"));
        } else {
            displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
        }
    }

    /**
     * This method is called when the user clicks on Exit button.
     * This method closes Add/Update Stock Popup.
     * @param actionEvent
     */
    public void onExit(ActionEvent actionEvent) {
        if (getAddUpdateStockPopupBinding() != null) {
            getAddUpdateStockPopupBinding().hide();
        }
    }

    public void setConfirmationPopupBinding(RichPopup confirmationPopupBinding) {
        this.confirmationPopupBinding = ComponentReference.newUIComponentReference(confirmationPopupBinding);
    }

    public RichPopup getConfirmationPopupBinding() {
        if (confirmationPopupBinding != null) {
            return (RichPopup) confirmationPopupBinding.getComponent();
        }
        return null;
    }

    public void setAddUpdateStockPopupBinding(RichPopup addUpdateStockPopupBinding) {
        this.addUpdateStockPopupBinding = ComponentReference.newUIComponentReference(addUpdateStockPopupBinding);
    }

    public RichPopup getAddUpdateStockPopupBinding() {
        if (addUpdateStockPopupBinding != null) {
            return (RichPopup) addUpdateStockPopupBinding.getComponent();
        }
        return null;
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

    public void setConfirmMessagePopupBinding(RichPopup confirmMessagePopupBinding) {
        this.confirmMessagePopupBinding = ComponentReference.newUIComponentReference(confirmMessagePopupBinding);
    }

    public RichPopup getConfirmMessagePopupBinding() {
        if (confirmMessagePopupBinding != null) {
            return (RichPopup) confirmMessagePopupBinding.getComponent();
        }
        return null;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }

    public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        if (getMindate() != null) {
            fileName = EXCEL_EXPORT_FILE_NAME + userId + "_" + getMindate() + EXCEL_EXTENSION;
        } else {
            fileName = EXCEL_EXPORT_FILE_NAME + userId + EXCEL_EXTENSION;
        }
        return fileName;
    }
}
