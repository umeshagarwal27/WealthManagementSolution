/*****************************************************************************************************
 ** Program Name            - iWMSUtil.java
 ** Program Description     - WMS Utility class
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.wms.framework.utils.common;

import com.ciobera.wms.framework.logger.LoggingUtil;

import java.io.IOException;

import java.sql.Timestamp;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.security.TaskFlowPermission;
import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.share.security.SecurityContext;
import oracle.adf.share.security.authorization.RegionPermission;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.ApplicationModule;
import oracle.jbo.JboException;
import oracle.jbo.domain.Date;
import oracle.jbo.uicli.binding.JUCtrlRangeBinding;
import oracle.jbo.uicli.binding.JUIteratorBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class iWMSUtil {

    private static final String CAPTURE_AM = "CaptureAM";
    private static final String ROOT_AM = "IAPRootAMDataControl";
    private static final String SETUP_AM = "SetupAM";
    public static final ADFLogger logObject =
        LoggingUtil.createLogger(iWMSUtil.class); //ADFLogger.createADFLogger(iAPUtil.class);


    /**Method to retreive the faces context
     * @return
     */
    public static FacesContext getFacesContext() {
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        return facesContext;
    }

    /**Utility method to get elcontext
     * @return
     */
    public static ELContext getELContext() {
        FacesContext facesContext;
        ELContext elContext;
        facesContext = getFacesContext();
        elContext = facesContext.getELContext();
        return elContext;
    }

    /**Method to retreive expression factory
     * @return
     */
    public static ExpressionFactory getExpressionFactory() {
        FacesContext fc;
        ELContext elc;
        ExpressionFactory ef;
        fc = getFacesContext();
        elc = getELContext();
        ef = fc.getApplication().getExpressionFactory();
        return ef;
    }


    /**Method to get el value as string
     * @param expr
     * @param defaultExpr
     * @return
     */
    public static String getELAsStringWithDefault(String expr,
                                                  String defaultExpr) {
        return (String)getWithDefault(expr, defaultExpr);
    }

    /**
     * @param expr
     * @return
     */
    public static String getELAsString(String expr) {
        return (String)getEL(expr);
    }

    /**Get integer attribute value from el
     * @param expr
     * @return
     */
    public static Integer getELAsInteger(String expr) {
        return (Integer)getEL(expr);
    }

    /**Get  attribute value from el
     * @param expr
     * @return
     */
    public static Object getEL(String expr) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getApplication().evaluateExpressionGet(fc, expr,
                                                         Object.class);
    }

    /**
     * @param expr
     * @param defaultExpr
     * @return
     */
    public static Object getWithDefault(String expr, String defaultExpr) {
        Object exprVal = getEL(expr);
        return exprVal != null ? exprVal : getEL(defaultExpr);
    }

    /**Get to set value to attribute bindings by EL
     * @param expr
     * @param value
     */
    public static void setEL(String expr, String value) {
        Object valToSet = value;
        if (isELExpr(value)) {
            valToSet = getEL(value);
        }
        setEL(expr, valToSet);
    }

    /**j
     * @param expr
     * @param value
     */
    public static void setEL(String expr, Object value) {
        ELContext elc;
        ExpressionFactory ef;
        elc = getELContext();
        ef = getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(elc, expr, Object.class);
        ve.setValue(elc, value);
    }

    /**Check EL expression
     * @param
     * @return
     */
    private static boolean isELExpr(Object o) {
        if (o instanceof String) {
            String str = (String)o;
            str.trim();
            return str.startsWith("#{") && str.endsWith("}");
        }
        return false;
    }

    /**Method to invoke operation binding
     * @param expr
     * @param paramTypes
     * @param params
     * @return
     */
    public static Object invokeMethod(String expr, Class[] paramTypes,
                                      Object[] params) {

        ELContext elc = getELContext();
        ExpressionFactory ef = getExpressionFactory();
        MethodExpression me =
            ef.createMethodExpression(elc, expr, Object.class, paramTypes);
        return me.invoke(elc, params);
    }

    public static Object invokeMethod(String expr, Class paramType,
                                      Object param) {
        return invokeMethod(expr, new Class[] { paramType },
                            new Object[] { param });
    }

    /**
     * Execute an AM method exposed as an operation and  bound to a page
     * definition that has two or more parameters.
     *
     * @param expr - e.g. #{bindings.amMehtod}
     * @return value if any.
     */

    public static Object invokeOperationBinding(String expr,
                                                String[] paramNames,
                                                Object[] params) {

        OperationBinding ob = (OperationBinding)iWMSUtil.getEL(expr);
        for (int i = 0; i < paramNames.length; i++) {

            ob.getParamsMap().put(paramNames[i], params[i]);
        }

        Object result = ob.execute();
        return result;
    }

    /**
     * Execute an AM method exposed as an operation and  bound to a page
     * definition that has a single parameter.
     *
     * @param expr
     * @param paramName
     * @param param
     * @return
     */
    public static Object invokeOperationBinding(String expr, String paramName,
                                                Object param) {

        Object result;

        if (!paramName.equals("")) {
            result =
                invokeOperationBinding(expr, new String[] { paramName },
                                       new Object[] { param });
        } else {
            result =
                invokeOperationBinding(expr, new String[] { },
                                       new Object[] { });

        }

        return result;
    }

    /**
     * Execute an AM method exposed as an operation and  bound to a page
     * definition that has no parameters.
     *
     * @param expr
     * @return
     */
    public static Object invokeOperationBinding(String expr) {

        Object result;

        result = invokeOperationBinding(expr, "", null);

        return result;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request =
            (HttpServletRequest)getFacesContext().getExternalContext().getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response =
            (HttpServletResponse)getFacesContext().getExternalContext().getResponse();
        return response;
    }

    public static HttpSession getSession() {
        FacesContext context = getFacesContext();
        HttpSession session =
            (HttpSession)context.getExternalContext().getSession(false);
        return session;
    }

    /** Method to access session attributes */
    public static Object getSessionAttribute(String attributeName) {
        Object obj = getSession().getAttribute(attributeName);
        return obj;
    }

    public static void setSessionAttribute(String attributeName, Object value) {
        getSession().setAttribute(attributeName, value);
    }

    public static void removeSessionAttribute(String attributeName) {
        getSession().removeAttribute(attributeName);
    }

    public static void addAlertMessege(String alertMsg) {
        FacesContext fc = FacesContext.getCurrentInstance();
        StringBuffer alertMsgStr = new StringBuffer();
        alertMsgStr.append("alert('");
        alertMsgStr.append(alertMsg);
        alertMsgStr.append("');");
        ExtendedRenderKitService erks =
            Service.getService(fc.getRenderKit(),
                               ExtendedRenderKitService.class);
        erks.addScript(fc, alertMsgStr.toString());
    }

    public static void logInfo(ADFLogger logObject, Object info) {
        if (logObject.isInfo()) {
            logObject.info((String)info);
        }
    }

    public static void logDebug(ADFLogger logObject, Object debug) {
        if (logObject.isFine()) {
            logObject.fine((String)debug);
        }
    }

    /**
     * Get application module for an application module data control by name.
     * @param name application module data control name
     * @return ApplicationModule
     */
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        return (ApplicationModule)getEL("#{data." + name + ".dataProvider}");
    }

    /**Get ERP RootAM instance
     * @return
     */
    public static ApplicationModule getErpRootAM() {
        return getApplicationModuleForDataControl(ROOT_AM);
    }

    public static ApplicationModule getNestedAMFromErpRootAM(String amName) {
        ApplicationModule erpRoot = null;
        erpRoot = iWMSUtil.getApplicationModuleForDataControl(ROOT_AM);
        return erpRoot.findApplicationModule(amName);
    }


    /**Method to get common AM
     * @return
     */
    public static ApplicationModule getSetupAM() {
        return getErpRootAM().findApplicationModule(SETUP_AM);
    }

    /**Method to get Admin AM
     * @return
     */
    public static ApplicationModule getCaptureAM() {
        return getErpRootAM().findApplicationModule(CAPTURE_AM);
    }


    public static void addMessageFromCode(FacesMessage.Severity type,
                                          String code) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String message = ADFUtil.getUIBundleMsg(code);
        FacesMessage fm = new FacesMessage(type, message, null);
        fctx.addMessage(null, fm);
    }

    public static Date getOracleDomainDate() {
        java.util.Date lDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(lDate.getTime());
        return (new Date(sqlDate));
    }

    public static Date getOracleDomainDateTime() {
        java.util.Date lDate = new java.util.Date();
        Timestamp ts = new java.sql.Timestamp(lDate.getTime());
        return (new Date(ts));
    }

    public static void addMessageWithCode(FacesMessage.Severity type,
                                          String code) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String message = ADFUtil.getUIBundleMsg(code);
        FacesMessage fm = new FacesMessage(type, code, message);
        fctx.addMessage(fctx.getViewRoot().getId(), fm);
    }

    public static void addMessageWithCode(FacesMessage.Severity type,
                                          String code, String message) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(type, code, message);
        fctx.addMessage(fctx.getViewRoot().getId(), fm);
    }

    public static void addComponentMessageWithCode(String clientCompId,
                                                   FacesMessage.Severity type,
                                                   String code,
                                                   String detailMsg) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String message = ADFUtil.getUIBundleMsg(code);
        FacesMessage fm = new FacesMessage(type, "", message);
        fctx.addMessage(clientCompId, fm);
    }

    public static void addMessage(String clientCompId,
                                  FacesMessage.Severity type, String message,
                                  String detailMsg) {

        FacesContext fctx = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(type, message, detailMsg);
        fctx.addMessage(clientCompId, fm);
    }


    /**
     * This method returns an instance of GlobalInfo sessionBean
     * @return GlobalInfo
     */
    public static Object getGlobalInfo() {
        Object globalInfo = (Object)getEL("#{GlobalInfo}");
        return globalInfo;
    }

    public static Map<String, Object> getPageFlowScopeMap() {
        AdfFacesContext context = AdfFacesContext.getCurrentInstance();
        return context.getPageFlowScope();
    }

    public static Map<String, Object> getViewScopeMap() {
        AdfFacesContext context = AdfFacesContext.getCurrentInstance();
        return context.getViewScope();
    }

    public static Map<String, Object> getRequestScopeMap() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getRequestMap();
    }

    public static void openPopup(RichPopup popup) {

        FacesContext context = FacesContext.getCurrentInstance();
        StringBuilder script = new StringBuilder();
        script.append("var popup = AdfPage.PAGE.findComponent('").append(popup.getClientId(context)).append("'); ").append("if (!popup.isPopupVisible()) { ").append("var hints = {}; ").append("popup.show(hints);}");
        ExtendedRenderKitService erks =
            Service.getService(context.getRenderKit(),
                               ExtendedRenderKitService.class);
        erks.addScript(context, script.toString());
    }

    public static void closePopup(RichPopup popup) {
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuilder script = new StringBuilder();
        script.append("var popup = AdfPage.PAGE.findComponent('").append(popup.getClientId(context)).append("'); ").append("if (popup.isPopupVisible()) { ").append("var hints = {}; ").append("popup.hide(hints);}");

        ExtendedRenderKitService erks =
            Service.getService(context.getRenderKit(),
                               ExtendedRenderKitService.class);
        erks.addScript(context, script.toString());
    }

    public static boolean isEmptyString(String value) {
        return ((value == null || value.length() == 0 ||
                 value.trim().length() == 0) ? true : false);
    }


    public static JUIteratorBinding getIteratorBinding(String itrName) {
        BindingContext bindingCtx = BindingContext.getCurrent();
        BindingContainer bindings = bindingCtx.getCurrentBindingsEntry();
        JUCtrlRangeBinding ctrlRangeBinding =
            (JUCtrlRangeBinding)bindings.getControlBinding(itrName);
        return ctrlRangeBinding.getIteratorBinding();
    }

    public static Integer getCurrentUserId() {
        return Integer.parseInt((String)iWMSUtil.getEL("#{GlobalInfo.userId}"));
    }

    /**Method to commit all the data changes
     */
    public static void commitRootAM() {
        try {
            iWMSUtil.getErpRootAM().getTransaction().commit();
        } catch (NullPointerException exception) {
            throw exception;
        }
    }

    /**Method to rollback the changes
     */
    public static void rollbackRootAM() {
        try {
            iWMSUtil.getErpRootAM().getTransaction().rollback();
        } catch (NullPointerException exception) {
            throw exception;
        }
    }

    public static String trimString(String input) {
        if (input != null)
            return input.trim();
        else
            return input;
    }

    public static void doPartialRefresh(UIComponent uic) {
        AdfFacesContext adfctx = AdfFacesContext.getCurrentInstance();
        if (adfctx != null) {
            adfctx.addPartialTarget(uic);
            adfctx.partialUpdateNotify(uic);
        }
    }


    /**
     * This method performs the validation of the emailids which are passed as Strings separated by delimiter
     * @return Boolean
     */
    public static Boolean doEmailIdValidation(String mailId, String delimiter) {


        //Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-])+(\\.([a-zA-Z0-9_\\-])+)*@((\\[(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5])))\\.(((([0-1])?([0-9])?[0-9])|(2[0-4][0-9])|(2[0-5][0-5]))\\]))|((([a-zA-Z0-9])+(([\\-])+([a-zA-Z0-9])+)*\\.)+([a-zA-Z])+(([\\-])+([a-zA-Z0-9])+)*))$");


        Pattern p =
            Pattern.compile("[A-Za-z0-9_]+([-+.'][A-Za-z0-9_]+)*@[A-Za-z0-9_]+([-.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+([-.][A-Za-z0-9_]+)*");


        if (!isEmptyString(mailId)) {
            String[] ccEmail = mailId.split(delimiter);
            if (!(ccEmail.length > 0)) {

                return false;
            } else {
                for (String email : ccEmail) {
                    if (email.length() > 0) {
                        Matcher m = p.matcher(email);
                        if (!m.find()) {

                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**Method to redirect to another application url
     * @param destnURL
     */
    public static void redirectToAppUrl(String destnURL) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ectx = context.getExternalContext();
            ectx.redirect(destnURL);
            context.responseComplete();
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(logObject,
                                         "Error redirecting to destination - " +
                                         destnURL);
        }
    }

    /**Method to handle navigation
     * @param outcome
     */
    public static void handleNavigation(String outcome) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler nh = context.getApplication().getNavigationHandler();
        nh.handleNavigation(context, null, outcome);
    }

    /**Mehtod to refresh any UI component
     * @param component
     */
    public static void refreshComponent(UIComponent component) {
        AdfFacesContext.getCurrentInstance().addPartialTarget(component);
    }

    /**Method to call Java Script
     * @param script
     */
    public static void callJsScript(String script) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExtendedRenderKitService extendedRenderKitService =
            Service.getRenderKitService(facesContext,
                                        ExtendedRenderKitService.class);
        extendedRenderKitService.addScript(facesContext, script);
    }

    /**
     * Pending changes exist.
     *
     * @return true, if successful
     */
    public static boolean pendingChangesExist() {
        try {
            ApplicationModule rootAM = iWMSUtil.getErpRootAM();
            //            boolean dataDirty = ControllerContext.getInstance().getCurrentViewPort().getTaskFlowContext().isDataDirty();
            //            boolean commitEnabled = JSFUtils.resolveExpressionAsBoolean("#{bindings.Commit.enabled}");
            boolean trxDirty = rootAM.getTransaction().isDirty();

            if (trxDirty) {
                return true;
            }
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(logObject,
                                         "Error in checking for pending changes : " +
                                         e.toString());
        }
        return false;
    }

    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }


    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId()))
            return base;

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent)childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * Method to logout from application
     *
     */
    public static String onLogout() {
        try {

            String loginURL = ADFUtil.getApplicationProperty("LOGIN_URL");
            FacesContext fctx = FacesContext.getCurrentInstance();
            String logoutUrl =
                fctx.getExternalContext().getRequestContextPath() +
                "/adfAuthentication?logout=true&end_url=" + loginURL;
            LoggingUtil.logDebugMessages(logObject, logoutUrl);
            fctx.getExternalContext().redirect(logoutUrl);
            fctx.responseComplete();
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(logObject, e.getMessage());
        }
        return null;
    }

    public void checkTransactionComplete() {
        Map pageFlow = this.getPageFlowScopeMap();
        String txnCompleteFlag = null;
        if (pageFlow != null)
            txnCompleteFlag = (String)pageFlow.get("TXN_COMPLETE");
        if ("Y".equalsIgnoreCase(txnCompleteFlag))
            throw new JboException("TXN_NOT_COMPLETE");
    }

    public static Boolean doPhoneNumberValidation(String phoneNumber) {


        Pattern p = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");

        if (!isEmptyString(phoneNumber)) {
            Matcher m = p.matcher(phoneNumber);
            if (!m.find()) {

                return false;
            }
        }
        return true;
    }

    public static boolean isRegionViewable(String pageDef) {
        if (pageDef == null) {
            return false;
        }
        RegionPermission permission =
            new RegionPermission(pageDef, RegionPermission.VIEW_ACTION);
        SecurityContext ctx = ADFContext.getCurrent().getSecurityContext();
        return ctx.hasPermission(permission);
    }

    public static boolean isTaskFlowViewable(String taskflowId) {
        if (taskflowId == null) {
            return false;
        }
        TaskFlowPermission permission =
            new TaskFlowPermission(taskflowId, TaskFlowPermission.VIEW_ACTION);
        SecurityContext ctx = ADFContext.getCurrent().getSecurityContext();
        return ctx.hasPermission(permission);
    }

    public static void navigateToHomePage() {
        handleNavigation("homePage");
    }

    public static void navigateToCaptureHomePage() {
        String captureHomeURL = "/iAPUIPortal/faces/CaptureHome";
        try {
            redirect(captureHomeURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void navigateToSetupHomePage() {
        String captureHomeURL = "/iAPUIPortal/faces/SetupHome";
        try {
            redirect(captureHomeURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void navigateToCaptureHistory() {
        handleNavigation("backToCaptureHistory");
    }

    public static void redirect(String path) throws IOException {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext exctx = fctx.getExternalContext();
        exctx.redirect(path);
    }

    public static String refreshCurrentPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        String currentView = context.getViewRoot().getViewId();
        ViewHandler vh = context.getApplication().getViewHandler();
        UIViewRoot viewRoot = vh.createView(context, currentView);
        context.setViewRoot(viewRoot);
        return null;
    }
}
