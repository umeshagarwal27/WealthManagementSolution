package com.ciobera.fwms.common.model.vo.readonly;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 14 14:43:48 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSCurrencyVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public FWMSCurrencyVOImpl() {
    }

    /**
     * Returns the variable value for pCurrency.
     * @return variable value for pCurrency
     */
    public String getpCurrency() {
        return (String) ensureVariableManager().getVariableValue("pCurrency");
    }

    /**
     * Sets <code>value</code> for variable pCurrency.
     * @param value value to bind as pCurrency
     */
    public void setpCurrency(String value) {
        ensureVariableManager().setVariableValue("pCurrency", value);
    }
}

