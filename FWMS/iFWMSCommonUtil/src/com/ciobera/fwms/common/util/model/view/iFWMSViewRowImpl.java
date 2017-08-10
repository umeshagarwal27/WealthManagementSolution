package com.ciobera.fwms.common.util.model.view;


import oracle.jbo.server.ViewRowImpl;

public class iFWMSViewRowImpl extends ViewRowImpl {
    public iFWMSViewRowImpl() {
        super();
    }


    public String setBooleanTranslation(Boolean value, String booleanTrue,
                                        String booleanFalse) {
        boolean bln = value;
        String setValue;
        if (value != null && value == true)
            setValue = booleanTrue;

        else
            setValue = booleanFalse;

        return setValue;
    }

    public Boolean getBooleanTranslation(String sEnabled,
                                         String sEnabledValue) {

        Boolean ret = false;
        if (sEnabled != null && sEnabledValue != null &&
            sEnabled.equalsIgnoreCase(sEnabledValue))
            ret = true;

        return ret;
    }

    public boolean isEmptyString(String value) {
        return ((value == null || value.length() == 0 ||
                 value.trim().length() == 0) ? true : false);
    }
}
