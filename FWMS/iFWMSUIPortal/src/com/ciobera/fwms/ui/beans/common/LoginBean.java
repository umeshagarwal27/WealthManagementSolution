/*****************************************************************************************************
 ** Program Name            - LoginBean.java
 ** Program Description     - This class contains the logic to authenticate the user.
 ** Date written            - 01-05-2015
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.ui.beans.common;


import com.ciobera.fwms.common.util.logger.LoggingUtil;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

import weblogic.servlet.security.ServletAuthentication;


public class LoginBean {
    private String _userName;
    private String _password;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(LoginBean.class);

    /**
     * Default Constructor.
     */
    public LoginBean() {
        super();
    }

    /**
     * Setter method for _userName.
     * @param _userName
     */
    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    /**
     * Getter method for _userName.
     * @return
     */
    public String getUserName() {
        return _userName;
    }

    /**
     * Setter method for _password.
     * @param _password
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * Getter method for _password.
     * @return
     */
    public String getPassword() {
        return _password;
    }

    /**
     * This method is called when the clicks on Login button in Login Page.
     * This method will authenticate the user credentials against Identity store in weblogic server.
     * @return
     */
    public String doLogin() {
        LoggingUtil.logDebugMessages(LOGGER, "Start of LoginBean.doLogin()" + _userName);
        String user = _userName;
        byte[] password = null;
        if ((user == null || user.trim().length() == 0) || (this._password == null || this._password
                                                                                          .trim()
                                                                                          .length() == 0)) {
            addMessage("Please enter User Name and Password to Login.");
            return null;
        }
        password = this._password.getBytes();
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        CallbackHandler handler = new URLCallbackHandler(user, password);
        try {
            // Get the authenticator Subject which has all the user login information
            Subject mySubject = Authentication.login(handler);
            ServletAuthentication.runAs(mySubject, request);
            ServletAuthentication.generateNewSessionID(request);
            String loginUrl = "/adfAuthentication?success_url=/faces/com/redacumen/iap/ui/pages/global/homePage.jspx";
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            //On successful authentication navigate to Home page
            sendForward(request, response, loginUrl);
        } catch (FailedLoginException fle) {
            addMessage("Incorrect UserName or Password");
            setPassword(null);
        } catch (LoginException le) {
            reportUnexpectedLoginError("LoginException", le);
        }
        LoggingUtil.logDebugMessages(LOGGER, "End of LoginBean.doLogin()");
        return null;
    }

    /**
     * Helper method to redirect user to Home page upon successful authentication.
     * @param request
     * @param response
     * @param forwardUrl
     */
    private void sendForward(HttpServletRequest request, HttpServletResponse response, String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException se) {
            reportUnexpectedLoginError("ServletException", se);
        } catch (IOException ie) {
            reportUnexpectedLoginError("IOException", ie);
        }
        ctx.responseComplete();
    }

    /**
     * Helper method to report Unexpected Error while Login.
     * @param errType
     * @param e
     */
    private void reportUnexpectedLoginError(String errType, Exception e) {
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unexpected error during login",
                             "Unexpected error during login (" + errType + "), please consult logs for detail");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Helper method to display message in Error Popup.
     * @param message
     */
    private void addMessage(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
