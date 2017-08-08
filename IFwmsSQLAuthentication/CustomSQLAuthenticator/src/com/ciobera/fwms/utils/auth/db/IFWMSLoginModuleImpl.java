package com.ciobera.fwms.utils.auth.db;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import weblogic.security.principal.WLSGroupImpl;
import weblogic.security.principal.WLSUserImpl;

public class IFWMSLoginModuleImpl implements LoginModule {

    private Subject subject;
    private CallbackHandler callbackHandler;
    private IFWMSDBAuthenticator dbAuthenticator;
    private boolean loginSucceeded;
    private boolean commitSucceeded;
    private Vector principalsBeforeCommit = new Vector();

    public void initialize(Subject paramSubject, CallbackHandler paramCallbackHandler, Map<String, ?> paramMap1,
                           Map<String, ?> paramMap2) {
        this.subject = paramSubject;
        this.callbackHandler = paramCallbackHandler;
        this.dbAuthenticator = ((IFWMSDBAuthenticator) paramMap2.get("dbAuthenticator"));
    }

    public boolean login() throws LoginException {
        System.out.println(" Calling login module inside IQSCDBLoginModuleImpl");
        this.loginSucceeded = false;

        if (this.callbackHandler == null) {
            System.out.println("Error: No call back handler");
            throw new LoginException("Error: No call back handler");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username: ");
        callbacks[1] = new PasswordCallback("password: ", false);
        try {
            System.out.println("-------Invoking CustomLoginModuleImpl login() method----");
            callbackHandler.handle(callbacks);
        } catch (IOException eio) {
            throw new LoginException(eio.toString());
        } catch (UnsupportedCallbackException eu) {
            throw new LoginException(eu.toString());
        }

        String username = ((NameCallback) callbacks[0]).getName();
        System.out.println("Username : " + username);
        char[] pw = ((PasswordCallback) callbacks[1]).getPassword();
        String password = new String(pw);
        System.out.println("Password : " + password);
        String str3 = null;

        IFWMSDBAuthenticator.IFWMSDBAuthenticatorResults localFWMSDBAuthenticatorResults = null;


        if (username.length() > 0) {
            try {
                localFWMSDBAuthenticatorResults = this.dbAuthenticator.authenticate(username, password, null);

                System.out.println("Auth Results successfully returned");
                System.out.println("isAuthenticated" + localFWMSDBAuthenticatorResults.isAuthenticated());
                if (!localFWMSDBAuthenticatorResults.isAuthenticated()) {
                    System.out.println("Authentication Failed: Try again");
                    throw new FailedLoginException("Authentication Failed: Try again");
                }
            } catch (FailedLoginException localFailedLoginException) {
                System.out.println("Rethrow - Authentication Failed: Try again");
                throw localFailedLoginException;
            } catch (Exception localException) {
                localException.printStackTrace();
                System.out.println("System Error - Contact System Admin");
                throw new FailedLoginException("System Error - Contact System Admin");
            }

            System.out.println("Adding principals to subject");
            System.out.println("First add user to principals");

            this.principalsBeforeCommit.add(new WLSUserImpl(localFWMSDBAuthenticatorResults.getUserName()));
            System.out.println("user added");
            System.out.println("Add roles");

            ArrayList localArrayList = localFWMSDBAuthenticatorResults.getAuthPrincipals();

            System.out.println("Principals" + localArrayList);
            System.out.println("Roles length = " + localArrayList.size());
            for (int i = 0; i < localArrayList.size(); i++) {
                String str4 = (String) localArrayList.get(i);
                System.out.println("\tgroupName\t= " + str4);
                this.principalsBeforeCommit.add(new WLSGroupImpl(str4));
            }
            this.loginSucceeded = true;
        } else {
            throw new FailedLoginException("System Error - Contact System Admin");
        }
        this.loginSucceeded = true;
        return true;
    }

    public boolean commit() throws LoginException {
        this.commitSucceeded = false;
        System.out.println("Subject contains " + this.subject
                                                     .getPrincipals()
                                                     .size() + " Principals before auth");

        if (!this.loginSucceeded) {
            System.out.println("Local LM commit failed because authentication failed");
            return this.commitSucceeded;
        }

        if (this.subject.isReadOnly()) {
            System.out.println("Subject is read only! ");
            System.out.println("Local LM commit failed because Subject is read only");
            throw new LoginException("Subject is read only!");
        }

        if ((this.principalsBeforeCommit != null) && (this.principalsBeforeCommit.size() > 0)) {
            this.subject
                .getPrincipals()
                .addAll(this.principalsBeforeCommit);
            this.commitSucceeded = true;

            System.out.println("Local LM commit succeeded");
            System.out.println("Subject contains " + this.subject
                                                         .getPrincipals()
                                                         .size() + " Principals after auth");

            return this.commitSucceeded;
        }

        return this.commitSucceeded;
    }

    public boolean abort() throws LoginException {
        System.out.println("Abort called on LoginModule");

        if (!this.loginSucceeded) {
            return false;
        }
        if ((this.loginSucceeded) && (!this.commitSucceeded)) {
            this.loginSucceeded = false;
        } else {
            removePrincipalsFromSubjectAndCleanUp();
            this.commitSucceeded = false;
            this.loginSucceeded = false;
        }
        return true;
    }

    public boolean logout() throws LoginException {
        this.loginSucceeded = false;
        this.commitSucceeded = false;
        removePrincipalsFromSubjectAndCleanUp();

        return true;
    }

    protected void removePrincipalsFromSubjectAndCleanUp() {
        this.subject
            .getPrincipals()
            .removeAll(this.principalsBeforeCommit);
    }
}
