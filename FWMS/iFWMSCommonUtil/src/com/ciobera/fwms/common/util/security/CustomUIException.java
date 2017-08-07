/*****************************************************************************************************
 ** Program Name            - CustomUIException.java
 ** Program Description     - Custom UI Exception class
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.security;


public class CustomUIException extends Exception {
    @SuppressWarnings("compatibility:-6180003338116130086")
    private static final long serialVersionUID = 1L;

    public CustomUIException(Throwable throwable) {
        super(throwable);
    }

    public CustomUIException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public CustomUIException(String string) {
        super(string);
    }

    public CustomUIException() {
        super();
    }

    public String getMessage() {
        return super.getMessage();
    }

    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    public String toString() {
        return "This is a sample custom exception defined for demonstration purpose";
    }
}
