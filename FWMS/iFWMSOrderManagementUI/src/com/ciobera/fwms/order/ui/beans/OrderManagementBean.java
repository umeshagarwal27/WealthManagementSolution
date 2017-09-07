/*****************************************************************************************************
 ** Program Name            - OrderManagementBean.java
 ** Program Description     - This class contains the logic of Order Management Screen.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.order.ui.beans;

import com.ciobera.fwms.common.util.bean.GlobalBean;
import com.ciobera.fwms.common.util.logger.LoggingUtil;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.faces.bi.event.chart.ChartDrillEvent;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class OrderManagementBean implements Serializable {
    @SuppressWarnings("compatibility:-5064459763820671720")
    private static final long serialVersionUID = -3351246699426832772L;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(OrderManagementBean.class);
    private boolean showGraph = false;
    private String userId =
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")) != null ?
        ((GlobalBean) ADFUtil.evaluateEL("#{GlobalBean}")).getUserId() : "SRINI";
    private Date asOfdate;
    private RichPopup countryPopup;
    private RichPopup exchangePopup;
    private RichPopup assetPopup;

    /**
     * Default Constructor
     */
    public OrderManagementBean() {
    }

    public void processAction(ActionEvent ae) {
        if (!executeMethod("processAsOfDateRecord", true)) {
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParams", true)) {
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsCountry", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsCountryD", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsAsset", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsAssetD", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsExchange", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
        if (!executeMethod("ExecuteWithParamsExchangeD", true)) {
            setShowGraph(Boolean.TRUE);
            //displayErrorPopup(ADFUtil.getUIBundleMsg("UNEXPECTED_ERROR"));
            return;
        }
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
                                         "Exception inside LoginBean.executeMethod() : " + methodName + e.toString());
        }
        return resultMap;
    }


    public void onCountrySelect(ChartDrillEvent chartDrillEvent) {
        executeMethod("ExecuteWithParamsCountryD", true);
        if (getCountryPopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getCountryPopup().show(hints);
        }
    }

    public void onExchangeSelect(ChartDrillEvent chartDrillEvent) {
        executeMethod("ExecuteWithParamsExchangeD", true);
        if (getExchangePopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getExchangePopup().show(hints);
        }
    }

    public void onAssetSelect(ChartDrillEvent chartDrillEvent) {
        executeMethod("ExecuteWithParamsAssetD", true);
        if (getAssetPopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getAssetPopup().show(hints);
        }
    }

    /**
     * Programmatic invocation of a method that an EL evaluates to.
     *
     * @param el EL of the method to invoke
     * @param paramTypes Array of Class defining the types of the parameters
     * @param params Array of Object defining the values of the parametrs
     * @return Object that the method returns
     */
    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);
        return exp.invoke(elContext, params);
    }

    /**
     * Programmatic evaluation of EL.
     *
     * @param el EL to evaluate
     * @return Result of the evaluation
     */
    public static Object evaluateEL(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);

        return exp.getValue(elContext);
    }

    /**Custom Selection Listener for dvt:pieChart
     * @param selectionEvent
     */
    public void pieChartSelectionListener(SelectionEvent selectionEvent, String elmakeCurrent) {
        // Makes selected slice as current row
        //"#{bindings.Employees1.collectionModel.makeCurrent}"
        invokeEL(elmakeCurrent, new Class[] { SelectionEvent.class }, new Object[] { selectionEvent });
        // Get the selected row (Use pie chart iterator name)

    }

    public void countrySelectionListener(SelectionEvent selectionEvent) {
        pieChartSelectionListener(selectionEvent, "#{bindings.FWMSDHoldingAllCountry.collectionModel.makeCurrent}");
        if (getCountryPopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getCountryPopup().show(hints);
        }
    }

    public void exchangeSelectionListener(SelectionEvent selectionEvent) {
        pieChartSelectionListener(selectionEvent, "#{bindings.FWMSDHoldingAllExchange.collectionModel.makeCurrent}");
        if (getExchangePopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getExchangePopup().show(hints);
        }
    }

    public void assetSelectionListener(SelectionEvent selectionEvent) {
        pieChartSelectionListener(selectionEvent, "#{bindings.FWMSDHoldingAllAsset.collectionModel.makeCurrent}");
        if (getAssetPopup() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getAssetPopup().show(hints);
        }
    }

    public void setShowGraph(boolean showGraph) {
        this.showGraph = showGraph;
    }

    public boolean isShowGraph() {
        return showGraph;
    }

    public void setCountryPopup(RichPopup countryPopup) {
        this.countryPopup = countryPopup;
    }

    public RichPopup getCountryPopup() {
        return countryPopup;
    }

    public void setExchangePopup(RichPopup exchangePopup) {
        this.exchangePopup = exchangePopup;
    }

    public RichPopup getExchangePopup() {
        return exchangePopup;
    }

    public void setAssetPopup(RichPopup assetPopup) {
        this.assetPopup = assetPopup;
    }

    public RichPopup getAssetPopup() {
        return assetPopup;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAsOfdate(Date asOfdate) {
        this.asOfdate = asOfdate;
    }

    public Date getAsOfdate() {
        return asOfdate;
    }
}
