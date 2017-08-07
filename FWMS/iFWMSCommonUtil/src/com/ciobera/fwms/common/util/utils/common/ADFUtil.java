/*****************************************************************************************************
 ** Program Name            - ADFUtil.java
 ** Program Description     - ADF Utility Class
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.utils.common;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.ControlBinding;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;
import oracle.javatools.resourcebundle.NamedMessageFormat;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlAttrsBinding;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;
import oracle.jbo.uicli.binding.JUCtrlValueBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class ADFUtil {
    public static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ADFUtil.class);
    private static String BUNDLE_NAME = "com.redacumen.iap.bundle.ui.UIBundle";
    private static String APP_BUNDLE_NAME =
        "com.redacumen.iap.bundle.configurations.Configurations";
    private static String MODEL_BUNDLE_NAME =
        "com.redacumen.iap.bundle.model.ModelBundle";


    public static int AUTO_SAVE_INTERVAL = 300000;

    /**
     * Get application module for an application module data control by name.
     * @param name application module data control name
     * @return ApplicationModule
     */
    public static ApplicationModule getApplicationModuleForDataControl(String name) {
        return (ApplicationModule)JSFUtil.resolveExpression("#{data." + name +
                                                            ".dataProvider}");
    }

    /**
     * Convenience method to find a DCControlBinding as an AttributeBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param bindingContainer binding container
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(BindingContainer bindingContainer,
                                                      String attributeName) {
        if (attributeName != null) {
            if (bindingContainer != null) {
                ControlBinding ctrlBinding =
                    bindingContainer.getControlBinding(attributeName);
                if (ctrlBinding instanceof AttributeBinding) {
                    return (AttributeBinding)ctrlBinding;
                }
            }
        }
        return null;
    }

    /**
     * Convenience method to find a DCControlBinding as a JUCtrlValueBinding
     * to get able to then call getInputValue() or setInputValue() on it.
     * @param attributeName name of the attribute binding.
     * @return the control value binding with the name passed in.
     *
     */
    public static AttributeBinding findControlBinding(String attributeName) {
        return findControlBinding(getBindingContainer(), attributeName);
    }

    /**
     * Return the current page's binding container.
     * @return the current page's binding container
     */
    public static BindingContainer getBindingContainer() {
        return (BindingContainer)JSFUtil.resolveExpression("#{bindings}");
    }

    /**
     * Return the Binding Container as a DCBindingContainer.
     * @return current binding container as a DCBindingContainer
     */
    public static DCBindingContainer getDCBindingContainer() {
        return (DCBindingContainer)getBindingContainer();
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName,
                                                          String valueAttrName,
                                                          String displayAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName,
                                      displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName name of the value attribute to use
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute to use for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(String iteratorName,
                                                          String valueAttrName,
                                                          String displayAttrName,
                                                          String descriptionAttrName) {
        return selectItemsForIterator(findIterator(iteratorName), valueAttrName,
                                      displayAttrName, descriptionAttrName);
    }

    /**
     * Get List of attribute values for an iterator.
     * @param iteratorName ADF iterator binding name
     * @param valueAttrName value attribute to use
     * @return List of attribute values for an iterator
     */
    public static List attributeListForIterator(String iteratorName,
                                                String valueAttrName) {
        return attributeListForIterator(findIterator(iteratorName),
                                        valueAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iteratorName iterabot binding name
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(String iteratorName) {
        return keyListForIterator(findIterator(iteratorName));
    }

    /**
     * Get List of Key objects for rows in an iterator.
     * @param iter iterator binding
     * @return List of Key objects for rows
     */
    public static List<Key> keyListForIterator(DCIteratorBinding iter) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getKey());
        }
        return attributeList;
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     * @param iteratorName iterator binding name
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(String iteratorName,
                                                   String keyAttrName) {
        return keyAttrListForIterator(findIterator(iteratorName), keyAttrName);
    }

    /**
     * Get List of Key objects for rows in an iterator using key attribute.
     *
     * @param iter iterator binding
     * @param keyAttrName name of key attribute to use
     * @return List of Key objects for rows
     */
    public static List<Key> keyAttrListForIterator(DCIteratorBinding iter,
                                                   String keyAttrName) {
        List<Key> attributeList = new ArrayList<Key>();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(new Key(new Object[] { r.getAttribute(keyAttrName) }));
        }
        return attributeList;
    }

    /**
     * Get a List of attribute values for an iterator.
     *
     * @param iter iterator binding
     * @param valueAttrName name of value attribute to use
     * @return List of attribute values
     */
    public static List attributeListForIterator(DCIteratorBinding iter,
                                                String valueAttrName) {
        List attributeList = new ArrayList();
        for (Row r : iter.getAllRowsInRange()) {
            attributeList.add(r.getAttribute(valueAttrName));
        }
        return attributeList;
    }

    /**
     * Find an iterator binding in the current binding container by name.
     *
     * @param name iterator binding name
     * @return iterator binding
     */
    public static DCIteratorBinding findIterator(String name) {
        DCIteratorBinding iter =
            getDCBindingContainer().findIteratorBinding(name);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + name + "' not found");
        }
        return iter;
    }

    /**
     * @param bindingContainer
     * @param iterator
     * @return
     */
    public static DCIteratorBinding findIterator(String bindingContainer,
                                                 String iterator) {
        DCBindingContainer bindings =
            (DCBindingContainer)JSFUtil.resolveExpression("#{" +
                                                          bindingContainer +
                                                          "}");
        if (bindings == null) {
            throw new RuntimeException("Binding container '" +
                                       bindingContainer + "' not found");
        }
        DCIteratorBinding iter = bindings.findIteratorBinding(iterator);
        if (iter == null) {
            throw new RuntimeException("Iterator '" + iterator + "' not found");
        }
        return iter;
    }

    /**
     * @param name
     * @return
     */
    public static JUCtrlValueBinding findCtrlBinding(String name) {
        JUCtrlValueBinding rowBinding =
            (JUCtrlValueBinding)getDCBindingContainer().findCtrlBinding(name);
        if (rowBinding == null) {
            throw new RuntimeException("CtrlBinding " + name + "' not found");
        }
        return rowBinding;
    }

    /**
     * Find an operation binding in the current binding container by name.
     *
     * @param strBindingName operation binding name
     * @return operation binding
     */

    public static OperationBinding findOperationBinding(String strBindingName) {
        BindingContainer bindings =
            BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.getOperationBinding(strBindingName);
    }

    public static OperationBinding findOperationBindingFromTemplate(String strBindingName,
                                                                    String templateBinding) {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCBindingContainer tempBind =
            (DCBindingContainer)bindings.get(templateBinding);

        return tempBind.getOperationBinding(strBindingName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with description.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with description
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter,
                                                          String valueAttrName,
                                                          String displayAttrName,
                                                          String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName),
                                           (String)r.getAttribute(displayAttrName),
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the value of the 'valueAttrName' attribute as the key for
     * the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param valueAttrName name of value attribute to use for key
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsForIterator(DCIteratorBinding iter,
                                                          String valueAttrName,
                                                          String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getAttribute(valueAttrName),
                                           (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName,
                                                               String displayAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName),
                                           displayAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iteratorName ADF iterator binding name
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(String iteratorName,
                                                               String displayAttrName,
                                                               String descriptionAttrName) {
        return selectItemsByKeyForIterator(findIterator(iteratorName),
                                           displayAttrName,
                                           descriptionAttrName);
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding with discription.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @param descriptionAttrName name of the attribute for description
     * @return ADF Faces SelectItem for an iterator binding with discription
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter,
                                                               String displayAttrName,
                                                               String descriptionAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(),
                                           (String)r.getAttribute(displayAttrName),
                                           (String)r.getAttribute(descriptionAttrName)));
        }
        return selectItems;
    }

    /**
     * Get List of ADF Faces SelectItem for an iterator binding.
     *
     * Uses the rowKey of each row as the SelectItem key.
     *
     * @param iter ADF iterator binding
     * @param displayAttrName name of the attribute from iterator rows to display
     * @return List of ADF Faces SelectItem for an iterator binding
     */
    public static List<SelectItem> selectItemsByKeyForIterator(DCIteratorBinding iter,
                                                               String displayAttrName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (Row r : iter.getAllRowsInRange()) {
            selectItems.add(new SelectItem(r.getKey(),
                                           (String)r.getAttribute(displayAttrName)));
        }
        return selectItems;
    }

    /**
     * @param opList
     */
    public static void printOperationBindingExceptions(List opList) {
        if (opList != null && !opList.isEmpty()) {
            for (Object error : opList) {
                LOGGER.severe(error.toString());
            }
        }
    }

    /**
     *  Method to set a value using EL expression.
     *
     *  @param expr - Expression
     *  @param value - Value to be set
     */

    public static void setEL(String expr, Object value) {
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        ELContext elc = facesContext.getELContext();
        ;
        ExpressionFactory ef =
            facesContext.getApplication().getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(elc, expr, Object.class);
        ve.setValue(elc, value);
    }

    /**
     *  Method to obtain a value from EL expression.
     *
     *  @param expr - Expression to be evaluated
     *
     *  @return - Value from the expression
     */

    public static Object evaluateEL(String expr) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getApplication().evaluateExpressionGet(fc, expr,
                                                         Object.class);
    }

    /**
     *  Method to return the Page flow scope map.
     *
     *  @return - Map
     */

    public static Map<String, Object> getPageFlowMap() {
        return AdfFacesContext.getCurrentInstance().getPageFlowScope();
    }

    /**
     *  Method to return the Request scope map.
     *
     *  @return - Map
     */

    public static Map<String, Object> getRequestScopeMap() {
        return JSFUtil.getFacesContext().getExternalContext().getRequestMap();
    }

    /**
     *  Method to refresh the component.
     *
     *  @param uic - UI Component
     */

    public static void doPartialRefresh(UIComponent uic) {
        // Refresh the Location area to reflect changes
        AdfFacesContext adfctx = AdfFacesContext.getCurrentInstance();
        if (adfctx != null) {
            adfctx.addPartialTarget(uic);
            adfctx.partialUpdateNotify(uic);
        }
    }

    /**
     *  Method to find using a key.
     *
     *  @param uic - UIComponent
     *  @param strId - ID of the component to be found
     *  @return - Component if found
     */

    public static UIComponent findComponent(UIComponent uic, String strId) {
        if (strId.equals(uic.getId())) {
            return uic;
        }

        Iterator<UIComponent> kids = uic.getFacetsAndChildren();
        while (kids.hasNext()) {
            UIComponent found = findComponent(kids.next(), strId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     *  Method to find a data control from the page definition.
     *
     *  @param name - Name
     *  @param bContext - Binding Context
     *
     *  @return - Control
     */

    public static DCDataControl getDataControl(String name,
                                               BindingContext bContext) {
        return bContext.findDataControl(name);
    }

    /**
     *  Generic method used to throw error messages dynamically.
     *
     *  @param strClientId - Client Id of the component throwing the error
     *  @param strMessage - Message to be thrown
     */

    public static void throwErrorMsg(String strClientId, String strMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        //check if message already added
        if (!isMessageAdded(strClientId, strMessage,
                            FacesMessage.SEVERITY_ERROR)) {
            FacesMessage fm = new FacesMessage();
            fm.setDetail(strMessage);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(strClientId, fm);
        }
    }

    /**
     *  Generic method used to throw warning messages dynamically.
     *
     *  @param strClientId - Client Id of the component throwing the error
     *  @param strMessage - Message to be thrown
     */

    public static void throwInfo(String strClientId, String strMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        //check if message already added
        if (!isMessageAdded(strClientId, strMessage,
                            FacesMessage.SEVERITY_INFO)) {
            FacesMessage fm = new FacesMessage();
            fm.setDetail(strMessage);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fc.addMessage(strClientId, fm);
        }
    }

    /**
     * Method to display message on the screen.
     * @param severity
     * @param message
     */
    public static void addMessage(Severity severity, String message) {
        FacesMessage msg = new FacesMessage(severity, message, null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private static boolean isMessageAdded(String strClientId, String strMessage,
                                          Severity sev) {
        FacesContext fc = FacesContext.getCurrentInstance();
        //check if message already added
        Iterator<FacesMessage> msgs = fc.getMessages(strClientId);
        while (msgs.hasNext()) {
            FacesMessage msg = msgs.next();
            //compare detail & severity
            if (msg.getDetail() != null &&
                msg.getDetail().equalsIgnoreCase(strMessage) &&
                msg.getSeverity().equals(sev)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Generic method used to get error messages from bundles.
     *
     *  @param strMsgId - Message Id
     *  @return - Value from bundle
     */

    public static String getUIBundleMsg(String strMsgId) {
        ResourceBundle rsBundle =
            BundleFactory.getBundle(BUNDLE_NAME,
                                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return rsBundle.getString(strMsgId);
    }

    /**
     *  Generic method used to get error messages from bundles.
     *
     *  @param strMsgId - Message Id
     *  @return - Value from bundle
     */

    public static String getUIBundleMsg(String strMsgId,
                                        Map<String, Object> tokenMap) {
        ResourceBundle rsBundle =
            BundleFactory.getBundle(BUNDLE_NAME,
                                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
        String message = rsBundle.getString(strMsgId);
        if (tokenMap != null && !tokenMap.isEmpty()) {
            message = NamedMessageFormat.formatMsg(message, tokenMap);
        }
        return message;
    }

    public static String getApplicationProperty(String strMsgId) {
        ResourceBundle rsBundle = BundleFactory.getBundle(APP_BUNDLE_NAME);
        return rsBundle.getString(strMsgId);
    }

    public static String getModelBundleMsg(String strMsgId) {
        ResourceBundle rsBundle =
            BundleFactory.getBundle(MODEL_BUNDLE_NAME,
                                    FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return rsBundle.getString(strMsgId);
    }

    /**
     *  Method to find the control list binding.
     *
     *  @param listAttrName - Attribute Name
     *  @return - Binding
     */

    public static JUCtrlListBinding findControlListBinding(String listAttrName) {
        return findControlListBinding(getBindingContainer(), listAttrName);
    }

    /**
     *  Method to find the control list binding.
     *
     *  @param bindingContainer - Binding container
     *  @param listAttrName - Attribute Name
     *
     *  @return - Binding
     */

    public static JUCtrlListBinding findControlListBinding(BindingContainer bindingContainer,
                                                           String listAttrName) {
        return (JUCtrlListBinding)bindingContainer.getControlBinding(listAttrName);
    }

    /**
     *  Method to return a method expression.
     *
     *  @param strExpression - String to be converted to an expression.
     *
     *  @return - Return expression
     */

    public static MethodExpression methodExpression(String strExpression) {
        FacesContext fctx = JSFUtil.getFacesContext();
        ExpressionFactory expressionFactory = null;
        expressionFactory = fctx.getApplication().getExpressionFactory();
        ELContext elctx = fctx.getELContext();
        MethodExpression methodExpression =
            expressionFactory.createMethodExpression(elctx, strExpression,
                                                     String.class,
                                                     new Class[] { });
        return methodExpression;
    }

    /**
     *  Method to return client event as expression.
     *
     *  @param strExpression - Expression as string
     *  @return - Expression as client event
     */

    public static MethodExpression getClientEventAsExpr(String strExpression) {
        FacesContext fc = JSFUtil.getFacesContext();
        ELContext elctx = fc.getELContext();
        ExpressionFactory elFactory =
            fc.getApplication().getExpressionFactory();
        MethodExpression methodExpr =
            elFactory.createMethodExpression(elctx, strExpression, null,
                                             new Class[] { ClientEvent.class });
        return methodExpr;
    }

    /**
     *  Method to return the Root AM.
     *
     *  @return - Application Module
     */

    public static ApplicationModule getRootAM() {
        return ((ApplicationModule)(evaluateEL("#{data.KhameleonRootAMDataControl.dataProvider}")));
    }


    /**
     * @param userRoleList
     * @return boolean
     */
    public static boolean roleBasedRender(List<String> userRoleList) {
        Boolean render = false;

        return render;
    }


    /**
     * Shows the specified popup component and its contents
     * @param popupId is the clientId of the popup to be shown
     * clientId is derived from backing bean for the af:popup using getClientId method
     */
    public static void invokePopup(String popupId) {
        invokePopup(popupId, null, null);
    }

    /**
     * Shows the specified popup and uses the specified hints to align the popup.
     * @param popupId is the clientId of the popup to be shown - clientId is derived from backing bean for the af:popup using getClientId method
     * @param align is a hint for the popup display. Check AdfRichPopup js javadoc for valid values. Supported value includes: "AdfRichPopup.ALIGN_START_AFTER", "AdfRichPopup.ALIGN_BEFORE_START" and "AdfRichPopup.ALIGN_END_BEFORE"
     * @param alignId is the clientId of the component the popup should align to - clientId is derived from backing bean for the component using getClientId method
     * align and alignId need to be specified together - specifying null for either of them will have no effect.
     */
    public static void invokePopup(String popupId, String align,
                                   String alignId) {
        if (popupId != null) {
            ExtendedRenderKitService service =
                Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                            ExtendedRenderKitService.class);

            StringBuffer showPopup = new StringBuffer();
            showPopup.append("var hints = new Object();");
            //Add hints only if specified - see javadoc for AdfRichPopup js for details on valid values and behavior
            if (align != null && alignId != null) {
                showPopup.append("hints[AdfRichPopup.HINT_ALIGN] = " + align +
                                 ";");
                showPopup.append("hints[AdfRichPopup.HINT_ALIGN_ID] ='" +
                                 alignId + "';");
            }
            showPopup.append("var popupObj=AdfPage.PAGE.findComponent('" +
                             popupId + "'); popupObj.show(hints);");
            service.addScript(FacesContext.getCurrentInstance(),
                              showPopup.toString());
        }
    }

    /**
     * Hides the specified popup.
     * @param popupId is the clientId of the popup to be hidden
     * clientId is derived from backing bean for the af:popup using getClientId method
     */
    public static void hidePopup(String popupId) {
        if (popupId != null) {
            ExtendedRenderKitService service =
                Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                            ExtendedRenderKitService.class);

            String hidePopup =
                "var popupObj=AdfPage.PAGE.findComponent('" + popupId +
                "'); popupObj.hide();";
            service.addScript(FacesContext.getCurrentInstance(), hidePopup);
        }
    }

    /**
     *  Method to return true or false to identify whether employee self initiates the request or not.
     *  @return
     */
    public static boolean isSelfReqInitiator() {
        return Boolean.parseBoolean(ADFUtil.evaluateEL("#{securityContext.userInRole['SELF_REQUEST_INITIATOR']}").toString()) ?
               Boolean.TRUE : Boolean.FALSE;
    }

    /**
     *  Method to return the date converted to specified format.
     *
     *  @param objTxnDate - Transaction date
     *  @return - Date format in string converted to the required format
     */

    public static String returnDateConvertedString(Object objTxnDate,
                                                   String strFormat) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
            java.util.Date date = formatter.parse(objTxnDate.toString());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            oracle.jbo.domain.Date txnEffectiveDate =
                new oracle.jbo.domain.Date(sqlDate);

            java.text.SimpleDateFormat displayDateFormat =
                new java.text.SimpleDateFormat(strFormat);
            return displayDateFormat.format(txnEffectiveDate.dateValue());
        } catch (ParseException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.severe(e);
            }
        }
        return objTxnDate.toString();
    }

    /**
     *  Method to return the control value for the control binding.
     *
     *  @param strBindingName - Binding name to return value
     *
     *  @return - Value
     */

    public static Object returnControlAttributeValue(String strBindingName) {
        JUCtrlAttrsBinding cparsDataChange =
            (JUCtrlAttrsBinding)getBindingContainer().get(strBindingName);
        return cparsDataChange.getAttributeValue();
    }

    /**
     * This method is used to add where clause for LIKE case
     * @param whereClause
     * @param attr
     * @param value
     */

    public static void addWhereClauseforLike(List whereClause, String attr,
                                             String value, String isUpper) {

        if (!StringUtils.equalsIgnoreCase(isUpper, "Y")) {
            if (whereClause.size() > 0) {
                whereClause.add(" and " + attr + " like '" + value + "%'");
            } else {
                whereClause.add(" " + attr + " like '" + value + "%'");
            }
        } else {
            if (whereClause.size() > 0) {
                whereClause.add(" and upper(" + attr + ") like upper('" +
                                value + "%')");
            } else {
                whereClause.add("upper(" + attr + ") like upper('" + value +
                                "%')");
            }

        }

    }

    /**
     * This method is used to add where clause for LIKE case
     * @param whereClause
     * @param attr
     * @param value
     */

    public static void addWhereClauseforEquals(List whereClause, String attr,
                                               BigDecimal value) {

        if (whereClause.size() > 0) {
            whereClause.add(" and " + attr + " = " + value + " ");
        } else {
            whereClause.add(" " + attr + " =" + value + " ");
        }

    }

    /**
     * This method is used to add where clause for Date between case
     * @param whereClause
     * @param attr
     * @param value1
     * @param value2
     */

    public static void addWhereClauseforDate(List whereClause, String attr,
                                             java.util.Date value1,
                                             java.util.Date value2) {

        java.sql.Date fromDate = new java.sql.Date(value1.getTime());
        java.sql.Date toDate = new java.sql.Date(value2.getTime());
        if (whereClause.size() > 0) {
            whereClause.add(" and (" + attr + " >= TO_DATE('" + fromDate +
                            "','yyyy-MM-dd') and  " + attr + " <= TO_DATE('" +
                            toDate + "','yyyy-MM-dd') ) ");
        } else {
            whereClause.add("(" + attr + " >= TO_DATE('" + fromDate +
                            "','yyyy-MM-dd') and  " + attr + " <= TO_DATE('" +
                            toDate + "','yyyy-MM-dd') ) ");
        }

    }

    /**
     * This method is used to add where clause for IN case
     * @param whereClause
     * @param attr
     * @param value
     */

    public static void addWhereClauseforIn(List whereClause, String attr,
                                           List value) {

        String var = null;
        for (int i = 0; i < value.size(); i++) {
            String listValue = "'" + (String)value.get(i) + "'";
            if (i == 0) {
                var = listValue;
            } else {
                var = var + "," + listValue;
            }
        }
        if (whereClause.size() > 0) {
            whereClause.add(" and " + attr + " in (" + var + ")");
        } else {
            whereClause.add(" " + attr + " in (" + var + ")");
        }

    }

    public static void setSelectedLocale(String isoLang, String isoTerri) {
        Locale newLocale =
            new Locale(isoLang.toLowerCase(), isoTerri.toUpperCase());
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.getViewRoot().setLocale(newLocale);

    }

    //to add action event queueing and lov queue process updates


    public static Row getSelectedRow(String iteratorName) {

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding(iteratorName); // or getDciteratorFromTable()

        ViewObject voTableData = dcItteratorBindings.getViewObject();

        Row rowSelected = voTableData.getCurrentRow();

        return rowSelected;
    }

    public static List<Row> getSelectedRows(RichTable table,
                                            String iteratorName) {

        List<Row> rows = new ArrayList<Row>();


        RowKeySet rksSelectedRows = table.getSelectedRowKeys();

        // Iterator object provides the ability to use hasNext(), next() and remove() against the selected rows
        Iterator itrSelectedRows = rksSelectedRows.iterator();


        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding(iteratorName);

        RowSetIterator rsiSelectedRows = dcIteratorBindings.getRowSetIterator();

        // Loop through selected rows
        while (itrSelectedRows.hasNext()) {

            // Get key for selected row
            Key key = (Key)((List)itrSelectedRows.next()).get(0);

            // Use the key to get the data from the above binding that is related to the row
            Row myRow = rsiSelectedRows.getRow(key);
            rows.add(myRow);
        }


        return rows;
    }

    /**This method will retreive the respective itertor for the adf table
     * @param table
     * @return
     */
    public static DCIteratorBinding getDciteratorFromTable(RichTable table) {
        CollectionModel model = (CollectionModel)table.getValue();
        JUCtrlHierBinding treeBinding =
            (JUCtrlHierBinding)model.getWrappedData();
        return treeBinding.getDCIteratorBinding();
    }


    /**
     * When a bounded task flow manages a transaction (marked as requires-transaction,.
     * requires-new-transaction, or requires-existing-transaction), then the
     * task flow must issue any commits or rollbacks that are needed. This is
     * essentially to keep the state of the transaction that the task flow understands
     * in synch with the state of the transaction in the ADFbc layer.
     *
     * Use this method to issue a commit in the middle of a task flow while staying
     * in the task flow.
     */
    public static void saveAndContinue() {
        Map sessionMap =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        BindingContext context =
            (BindingContext)sessionMap.get(BindingContext.CONTEXT_ID);
        String currentFrameName = context.getCurrentDataControlFrame();
        DataControlFrame dcFrame =
            context.findDataControlFrame(currentFrameName);

        dcFrame.commit();
        dcFrame.beginTransaction(null);
    }


    /**
     * Programmatic invocation of a method that an EL evaluates to.
     * The method must not take any parameters.
     *
     * @param el EL of the method to invoke
     * @return Object that the method returns
     */
    public static Object invokeEL(String el) {
        return invokeEL(el, new Class[0], new Object[0]);
    }

    /**
     * Programmatic invocation of a method that an EL evaluates to.
     *
     * @param el EL of the method to invoke
     * @param paramTypes Array of Class defining the types of the parameters
     * @param params Array of Object defining the values of the parametrs
     * @return Object that the method returns
     */
    public static Object invokeEL(String el, Class[] paramTypes,
                                  Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
            facesContext.getApplication().getExpressionFactory();
        MethodExpression exp =
            expressionFactory.createMethodExpression(elContext, el,
                                                     Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }


}
