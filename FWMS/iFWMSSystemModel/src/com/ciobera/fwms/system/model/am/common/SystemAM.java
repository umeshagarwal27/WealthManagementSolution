package com.ciobera.fwms.system.model.am.common;

import java.util.Map;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 15 19:31:01 IST 2017
// ---------------------------------------------------------------------
public interface SystemAM extends ApplicationModule {


    void executeEmptyRowSetBondCoupen();

    Map updateProductRecord(String mode, String updatedBy);
}

