/*****************************************************************************************************
 ** Program Name            - iWMSUIErrorHandler.java
 ** Program Description     - UI Error Handler class.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.handlers;


import com.ciobera.fwms.common.util.security.CustomUIException;
import com.ciobera.fwms.common.util.security.iWMSJboException;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.InvalidTaskFlowReentryException;
import oracle.adf.controller.ViewPortContext;
import oracle.adf.controller.security.AuthorizationException;
import oracle.adf.share.logging.ADFLogger;


public class iWMSUIErrorHandler {
    public static final ADFLogger logObject =
        ADFLogger.createADFLogger(iWMSUIErrorHandler.class);

    public iWMSUIErrorHandler() {
        super();
    }

    public String getMessage() {
        ControllerContext cctx = ControllerContext.getInstance();
        Exception exception = cctx.getCurrentViewPort().getExceptionData();
        System.out.println(exception.getClass().toString());

        if (exception instanceof iWMSJboException) {
            String errorCode = ((iWMSJboException)exception).getErrorCode();
            String errorMsg = ((iWMSJboException)exception).getMessage();

            if ("ERR_CODE_1".equals(errorCode)) {
                //handle needed functionality

            } else {

                //handle needed functionality
            }
            return errorMsg;

        } else if (exception != null && exception.getCause() != null &&
                   exception.getCause() instanceof CustomUIException) {
            return exception.getCause().getMessage(); // can return specific from bundle
        } else if (exception instanceof AuthorizationException) {
            return ADFUtil.getUIBundleMsg("ERR_AUTH");
        } else if (exception instanceof InvalidTaskFlowReentryException) {
            return ADFUtil.getUIBundleMsg("ERR_INVALID_TASKFLOW");
        } else if (exception.toString().indexOf("TXN_NOT_COMPLETE") > -1) {
            return ADFUtil.getUIBundleMsg("ERR_DUP_TRANS"); //throw new JboException("TXN_NOT_COMPLETE");
        } else if (exception != null && exception.getCause() != null &&
                   exception.getCause() instanceof iWMSJboException) {
            return null; // can return specific from bundle
        } else if (exception instanceof AuthorizationException) {
            return ADFUtil.getUIBundleMsg("ERR_AUTH");
        } else if (exception instanceof InvalidTaskFlowReentryException) {
            return ADFUtil.getUIBundleMsg("ERR_INVALID_TASKFLOW");
        } else if (exception.toString().indexOf("Sample_TXN_NOT_COMPLETE") >
                   -1) {
            return ADFUtil.getUIBundleMsg("ERR_INCOMPLETE");
        }
        return ADFUtil.getUIBundleMsg("ERR_GENERIC");
    }

    public String getGenericMessage() {
        return ADFUtil.getUIBundleMsg("ERR_GENERIC");
    }

    public String getStackTrace() {
        ControllerContext ccontext = ControllerContext.getInstance();
        ViewPortContext viewPortCtx = ccontext.getCurrentViewPort();
        Exception exception = ccontext.getCurrentViewPort().getExceptionData();
        if (viewPortCtx.getExceptionData() != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            viewPortCtx.getExceptionData().printStackTrace(printWriter);
            return stringWriter.toString();
        }
        return "";
    }

    public void goToHome(ActionEvent actionEvent) {
        logObject.info("Enter goToHome method");
        String Home = ADFUtil.getUIBundleMsg("APP_HOME");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            externalContext.redirect(Home);
            context.responseComplete();
        } catch (Exception ex) {
            logObject.fine("Error in redirecting to  Home Page");
        }
        logObject.info("Exit goToHome method");
    }
}


