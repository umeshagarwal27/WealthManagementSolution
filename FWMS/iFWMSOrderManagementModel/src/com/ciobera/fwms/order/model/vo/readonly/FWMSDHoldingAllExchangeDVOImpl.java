package com.ciobera.fwms.order.model.vo.readonly;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 07 19:38:37 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSDHoldingAllExchangeDVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public FWMSDHoldingAllExchangeDVOImpl() {
    }

    /**
     * Returns the bind variable value for loggedInUserID.
     * @return bind variable value for loggedInUserID
     */
    public String getloggedInUserID() {
        return (String) getNamedWhereClauseParam("loggedInUserID");
    }

    /**
     * Sets <code>value</code> for bind variable loggedInUserID.
     * @param value value to bind as loggedInUserID
     */
    public void setloggedInUserID(String value) {
        setNamedWhereClauseParam("loggedInUserID", value);
    }
}

