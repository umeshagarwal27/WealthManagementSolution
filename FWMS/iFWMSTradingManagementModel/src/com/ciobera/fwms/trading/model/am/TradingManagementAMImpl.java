package com.ciobera.fwms.trading.model.am;

import com.ciobera.fwms.trading.model.vo.readonly.FWMSDHoldingAllVOImpl;

import oracle.jbo.server.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 08 21:39:21 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TradingManagementAMImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public TradingManagementAMImpl() {
    }

    /**
     * Container's getter for FWMSDHoldingAll.
     * @return FWMSDHoldingAll
     */
    public FWMSDHoldingAllVOImpl getFWMSDHoldingAll() {
        return (FWMSDHoldingAllVOImpl) findViewObject("FWMSDHoldingAll");
    }
}

