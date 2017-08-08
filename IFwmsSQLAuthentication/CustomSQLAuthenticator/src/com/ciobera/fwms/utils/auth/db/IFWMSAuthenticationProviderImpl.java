package com.ciobera.fwms.utils.auth.db;

import com.ciobera.fwms.utils.auth.IFWMSAuthenticationMBean;

import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;

import weblogic.management.security.ProviderMBean;

import weblogic.security.spi.AuthenticationProvider;
import weblogic.security.spi.IdentityAsserter;
import weblogic.security.spi.PrincipalValidator;
import weblogic.security.spi.SecurityServices;

public final class IFWMSAuthenticationProviderImpl implements AuthenticationProvider {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String description;
    private AppConfigurationEntry.LoginModuleControlFlag controlFlag;

    @SuppressWarnings("unchecked")
    public AppConfigurationEntry getLoginModuleConfiguration() {
        HashMap<String, IFWMSDBAuthenticator> localHashMap1 = new HashMap<>();
        HashMap localHashMap2 = new HashMap();
        localHashMap1.put("dbAuthenticator",
                          new IFWMSDBAuthenticator(this.dbUrl, this.dbUsername, this.dbPassword, localHashMap2));
        return new AppConfigurationEntry("com.qualcomm.iqsc.utils.auth.db.IQSCDBLoginModuleImpl", this.controlFlag,
                                         localHashMap1);
    }

    public AppConfigurationEntry getAssertionModuleConfiguration() {
        return null;
    }

    public PrincipalValidator getPrincipalValidator() {
        return null;
    }

    public IdentityAsserter getIdentityAsserter() {
        return null;
    }

    public void initialize(ProviderMBean paramProviderMBean, SecurityServices paramSecurityServices) {
        try {
            IFWMSAuthenticationMBean localIQSCAuthenticatorMBean = (IFWMSAuthenticationMBean) paramProviderMBean;

            this.description =
                (localIQSCAuthenticatorMBean.getDescription() + "\n" + localIQSCAuthenticatorMBean.getVersion());
            this.dbUrl = localIQSCAuthenticatorMBean.getDbAuthUrl();
            this.dbUsername = localIQSCAuthenticatorMBean.getDbAuthUsername();
            this.dbPassword = localIQSCAuthenticatorMBean.getDbAuthPassword();

            String str = localIQSCAuthenticatorMBean.getControlFlag();
            if (str.equalsIgnoreCase("REQUIRED"))
                this.controlFlag = AppConfigurationEntry.LoginModuleControlFlag.REQUIRED;
            else if (str.equalsIgnoreCase("OPTIONAL"))
                this.controlFlag = AppConfigurationEntry.LoginModuleControlFlag.OPTIONAL;
            else if (str.equalsIgnoreCase("REQUISITE"))
                this.controlFlag = AppConfigurationEntry.LoginModuleControlFlag.REQUISITE;
            else if (str.equalsIgnoreCase("SUFFICIENT"))
                this.controlFlag = AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT;
            else {
                throw new IllegalArgumentException("Invalid control flag " + str);
            }
            System.out.println("control flag:" + this.controlFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return null;
    }

    public void shutdown() {
    }
}
