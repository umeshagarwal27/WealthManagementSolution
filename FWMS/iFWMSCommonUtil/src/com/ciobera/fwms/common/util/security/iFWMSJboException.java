/*****************************************************************************************************
 ** Program Name            - iWMSJboException.java
 ** Program Description     - JBO Exception class
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.security;

import oracle.jbo.JboException;
import oracle.jbo.common.ResourceBundleDef;


public class iFWMSJboException extends JboException {
    public iFWMSJboException(Class class1, String string, Object[] objects,
                            Exception[] exceptions) {
        super(class1, string, objects, exceptions);
        this.setAppendCodes(false);
    }

    public iFWMSJboException(Class class1, String string, Object[] objects,
                            JboException[] jboExceptions) {
        super(class1, string, objects, jboExceptions);
        this.setAppendCodes(false);
    }

    public iFWMSJboException(ResourceBundleDef resourceBundleDef, String string,
                            Object[] objects) {
        super(resourceBundleDef, string, objects);
        this.setAppendCodes(false);
    }

    public iFWMSJboException(Class class1, String string, Object[] objects) {
        super(class1, string, objects);
        this.setAppendCodes(false);
    }

    public iFWMSJboException(Throwable throwable) {
        super(throwable);
        this.setAppendCodes(false);

    }

    public iFWMSJboException(String string) {
        super(string);
        this.setAppendCodes(false);
    }

    public iFWMSJboException(String string, String string1, Object[] objects) {
        super(string, string1, objects);
        this.setAppendCodes(false);
    }

    //    sample code for throwing this
    //    throw new iAPJboException(“User Already exists”)
    //    throw new iAPJboException("User Exists" , "DUP_USER_CODE", null);


}
