/*****************************************************************************************************
 ** Program Name            - GlobalBean.java
 ** Program Description     - This is a session scope bean, containing all session scope variables.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.bean;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;


public class GlobalBean implements Serializable {
    @SuppressWarnings("compatibility:26178217511229558")
    private static final long serialVersionUID = -4881290547054895170L;
    private String userName;
    private String userId;
    private Boolean isUserBlocked;
    private String lastLogin;
    private String lastPasswordChange;
    private String status;
    private String emailAddress;
    private Integer expiryDays;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(GlobalBean.class);

    public GlobalBean() {
        super();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setIsUserBlocked(Boolean isUserBlocked) {
        this.isUserBlocked = isUserBlocked;
    }

    public Boolean getIsUserBlocked() {
        return isUserBlocked;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setExpiryDays(Integer expiryDays) {
        this.expiryDays = expiryDays;
    }

    public Integer getExpiryDays() {
        return expiryDays;
    }
}
