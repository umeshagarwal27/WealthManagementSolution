/*****************************************************************************************************
 ** Program Name            - LoginBean.java
 ** Program Description     - This class contains the logic to authenticate the user.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.ui.beans.common;


import com.ciobera.fwms.common.util.logger.LoggingUtil;
import com.ciobera.fwms.common.util.utils.common.ADFUtil;

import java.io.IOException;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.util.ComponentReference;


public class LoginBean {
    private String _userName;
    private String _password;
    private ComponentReference forgotPasswordPopupBinding;
    private ComponentReference passwordExpiredPopupBinding;
    private ComponentReference errorMessagePopupBinding;
    private ComponentReference confirmMessagePopupBinding;
    private String peUserId;
    private String pePassword;
    private String peNewPassword;
    private String peConfirmNewPassword;
    private String customErrorMessage;
    private String customMessage;
    private String fpUserId;
    private String fpOTP;
    private String pc;
    private long failureAttempts = 0;
    private boolean loginFailed = Boolean.FALSE;
    private String wlsHome;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(LoginBean.class);

    /**
     * Default Constructor.
     */
    public LoginBean() {
        super();
    }

    /**
     * Helper method to execute Method.
     * @param methodName
     */
    private OperationBinding executeMethod(String methodName) {
        try {
            OperationBinding executeMethodOP = ADFUtil.findOperationBinding(methodName);
            if (executeMethodOP != null) {
                executeMethodOP.execute();
                if (executeMethodOP.getErrors().size() != 0) {
                    LoggingUtil.logErrorMessages(LOGGER,
                                                 "An unexpected error occured inside LoginBean.executeMethod() : " +
                                                 methodName + " . Please contact your system administrator.");
                }
            }
            return executeMethodOP;
        } catch (Exception e) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Exception inside LoginBean.executeMethod() : " + methodName + e.toString());
        }
        return null;
    }

    /**
     * This method is called when the clicks on Login button in Login Page.
     * @return
     */
    public String doLogin() {
        LoggingUtil.logDebugMessages(LOGGER, "Start of LoginBean.doLogin()" + _userName);
        String user = _userName;
        if ((user == null || user.trim().length() == 0) || (this._password == null || this._password
                                                                                          .trim()
                                                                                          .length() == 0)) {
            addMessage("Please enter User ID and Password to Login.");
            return null;
        }
        OperationBinding executeMethodOP = ADFUtil.findOperationBinding("findUserByLoginCredentials");
        if (executeMethodOP != null) {
            Map paramsMap = executeMethodOP.getParamsMap();
            paramsMap.put("userId", _userName);
            paramsMap.put("password", _password);
            executeMethodOP.execute();
            if (executeMethodOP.getErrors().size() != 0) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "An unexpected error occured inside LoginBean.executeMethod(). Please contact your system administrator.");
                return null;
            } else {
                Map resultMap = (Map) executeMethodOP.getResult();
                if (resultMap != null) {
                    String respCode = (String) resultMap.get("RESP_CODE");
                    if ("INVALID".equalsIgnoreCase(respCode)) {
                        if (getErrorMessagePopupBinding() != null) {
                            setCustomErrorMessage(ADFUtil.getUIBundleMsg("INVALID_CREDENTIALS"));
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            getErrorMessagePopupBinding().show(hints);
                        }
                        executeMethod("logWrongAttempt");
                        
                        return null;
                    } else if ("SUCCESS".equalsIgnoreCase(respCode)) {

                    } else {

                    }
                }
            }
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

    public String onExit() {
        // Add event code here...
        return null;
    }

    /**
     * This method is called when the user clicks on Forgot Password link in Login Page.
     * This method launches Forgot Password popup.
     * @return
     */
    public String onForgotPasswordClick() {
        if (getForgotPasswordPopupBinding() != null) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getForgotPasswordPopupBinding().show(hints);
        }
        return null;
    }

    /**
     * Forgot Password Methods.
     */

    /**
     * This method is called when the user clicks on Get OTP button in Forgot Password popup.
     * @param actionEvent
     */
    public void onGetOTP(ActionEvent actionEvent) {
        // Add event code here...
    }

    /**
     * This method is called when the user clicks on ResetPassword in Forgot Password Popup.
     * @param actionEvent
     */
    public void onResetPassword(ActionEvent actionEvent) {
        // Add event code here...
    }

    /**
     * This method is called when the user clicks on Back button in Forgot Password popup.
     * This method closes Forgot Password Dialog
     * @param actionEvent
     */
    public void onCloseForgotPasswordPopup(ActionEvent actionEvent) {
        if (getForgotPasswordPopupBinding() != null) {
            getForgotPasswordPopupBinding().hide();
        }
    }

    /**
     * Password Expiry Methods.
     */

    /**
     * Helper method to validate Password Regex
     * @param password
     */
    private boolean validatePasswordRegex(String password) {
        String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}";
        if (password != null) {
            return password.matches(pattern);
        }
        return Boolean.FALSE;
    }

    /**
     * Helper method to validate New and Confirm Password
     */
    private boolean validateNewPassword() {
        if (peNewPassword == null || peConfirmNewPassword == null || "".equalsIgnoreCase(peNewPassword) ||
            "".equalsIgnoreCase(peConfirmNewPassword)) {
            if (getErrorMessagePopupBinding() != null) {
                setCustomErrorMessage(ADFUtil.getUIBundleMsg("NEW_CONFIRM_NEW_PASSWORD_NULL"));
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getErrorMessagePopupBinding().show(hints);
            }
            return Boolean.FALSE;
        }
        if (!peNewPassword.equals(peConfirmNewPassword)) {
            if (getErrorMessagePopupBinding() != null) {
                setCustomErrorMessage(ADFUtil.getUIBundleMsg("PASSWORD_MISMATCH"));
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getErrorMessagePopupBinding().show(hints);
            }
            return Boolean.FALSE;
        }
        if (!validatePasswordRegex(peNewPassword)) {
            if (getErrorMessagePopupBinding() != null) {
                setCustomErrorMessage(ADFUtil.getUIBundleMsg("PASSWORD_VALIDATION_FAILURE"));
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getErrorMessagePopupBinding().show(hints);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * This method is called from the Forgot Password pop up.
     * This method checks if the user id/password combination exists in the system
     * Verifies the password syntax and then updates the new password in the DB.
     * @param actionEvent
     */
    public void onChangePassword(ActionEvent actionEvent) {
        if (peUserId == null || pePassword == null || "".equalsIgnoreCase(peUserId) ||
            "".equalsIgnoreCase(pePassword)) {
            if (getErrorMessagePopupBinding() != null) {
                setCustomErrorMessage(ADFUtil.getUIBundleMsg("USERID_PASSWORD_NULL"));
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getErrorMessagePopupBinding().show(hints);
            }
            return;
        }
        //Check if the credentials exists in the system or not.
        OperationBinding executeMethodOP = ADFUtil.findOperationBinding("findUserByLoginCredentials");
        if (executeMethodOP != null) {
            Map paramsMap = executeMethodOP.getParamsMap();
            paramsMap.put("userId", peUserId);
            paramsMap.put("password", pePassword);
            executeMethodOP.execute();
            if (executeMethodOP.getErrors().size() != 0) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "An unexpected error occured inside LoginBean.executeMethod(). Please contact your system administrator.");
                return;
            } else {
                Map resultMap = (Map) executeMethodOP.getResult();
                if (resultMap != null) {
                    String respCode = (String) resultMap.get("RESP_CODE");
                    if ("INVALID".equalsIgnoreCase(respCode)) {
                        if (getErrorMessagePopupBinding() != null) {
                            setCustomErrorMessage(ADFUtil.getUIBundleMsg("INVALID_CREDENTIALS"));
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            getErrorMessagePopupBinding().show(hints);
                        }
                        return;
                    } else if ("SUCCESS".equalsIgnoreCase(respCode)) {
                        if (!validateNewPassword()) {
                            return;
                        } else {
                            OperationBinding updateMethodOP = ADFUtil.findOperationBinding("updateLoginCredentials");
                            if (updateMethodOP != null) {
                                Map updateMethodParamsMap = updateMethodOP.getParamsMap();
                                updateMethodParamsMap.put("userId", peUserId);
                                updateMethodParamsMap.put("password", pePassword);
                                updateMethodParamsMap.put("newPassword", peNewPassword);
                                updateMethodOP.execute();
                                if (updateMethodOP.getErrors().size() != 0) {
                                    LoggingUtil.logErrorMessages(LOGGER,
                                                                 "An unexpected error occured inside LoginBean.executeMethod(). Please contact your system administrator.");
                                    return;
                                } else {
                                    Map updateMethodResultMap = (Map) updateMethodOP.getResult();
                                    if (resultMap != null) {
                                        String updateMethodRespCode = (String) updateMethodResultMap.get("RESP_CODE");
                                        if ("SUCCESS".equalsIgnoreCase(updateMethodRespCode)) {
                                            if (getPasswordExpiredPopupBinding() != null) {
                                                getPasswordExpiredPopupBinding().hide();
                                            }
                                            if (getConfirmMessagePopupBinding() != null) {
                                                setCustomMessage(ADFUtil.getUIBundleMsg("PASSWORD_UPDATE_SUCCESSFULLY"));
                                                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                                                getConfirmMessagePopupBinding().show(hints);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is called when the user clicks on Back button in Change Password popup.
     * This method closes Change Password Popup
     * @param actionEvent
     */
    public void onCloseChangePasswordPopup(ActionEvent actionEvent) {
        if (getPasswordExpiredPopupBinding() != null) {
            getPasswordExpiredPopupBinding().hide();
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

    public void setForgotPasswordPopupBinding(RichPopup forgotPasswordPopupBinding) {
        this.forgotPasswordPopupBinding = ComponentReference.newUIComponentReference(forgotPasswordPopupBinding);
    }

    public RichPopup getForgotPasswordPopupBinding() {
        if (forgotPasswordPopupBinding != null) {
            return (RichPopup) forgotPasswordPopupBinding.getComponent();
        }
        return null;
    }

    public void setPasswordExpiredPopupBinding(RichPopup passwordExpiredPopupBinding) {
        this.passwordExpiredPopupBinding = ComponentReference.newUIComponentReference(passwordExpiredPopupBinding);
    }

    public RichPopup getPasswordExpiredPopupBinding() {
        if (passwordExpiredPopupBinding != null) {
            return (RichPopup) passwordExpiredPopupBinding.getComponent();
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

    public void setPeUserId(String peUserId) {
        this.peUserId = peUserId;
    }

    public String getPeUserId() {
        return peUserId;
    }

    public void setPePassword(String pePassword) {
        this.pePassword = pePassword;
    }

    public String getPePassword() {
        return pePassword;
    }

    public void setPeNewPassword(String peNewPassword) {
        this.peNewPassword = peNewPassword;
    }

    public String getPeNewPassword() {
        return peNewPassword;
    }

    public void setPeConfirmNewPassword(String peConfirmNewPassword) {
        this.peConfirmNewPassword = peConfirmNewPassword;
    }

    public String getPeConfirmNewPassword() {
        return peConfirmNewPassword;
    }

    public void setCustomErrorMessage(String customErrorMessage) {
        this.customErrorMessage = customErrorMessage;
    }

    public String getCustomErrorMessage() {
        return customErrorMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setFpUserId(String fpUserId) {
        this.fpUserId = fpUserId;
    }

    public String getFpUserId() {
        return fpUserId;
    }

    public void setFpOTP(String fpOTP) {
        this.fpOTP = fpOTP;
    }

    public String getFpOTP() {
        return fpOTP;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getPc() {
        return pc;
    }

    public void setWlsHome(String wlsHome) {
        this.wlsHome = wlsHome;
    }

    public String getWlsHome() {
        if(wlsHome == null){
            wlsHome= System.getProperty("wls.home");
            setWlsHome(wlsHome);
        }
        return wlsHome;
    }

    public void setFailureAttempts(long failureAttempts) {
        this.failureAttempts = failureAttempts;
    }

    public long getFailureAttempts() {
        return failureAttempts;
    }

    public void setLoginFailed(boolean loginFailed) {
        this.loginFailed = loginFailed;
    }

    public boolean isLoginFailed() {
        return loginFailed;
    }

    public static void main(String args[]) {
        LoginBean lb = new LoginBean();
        System.out.println(lb.validatePasswordRegex("Welcome123@"));
    }
}
