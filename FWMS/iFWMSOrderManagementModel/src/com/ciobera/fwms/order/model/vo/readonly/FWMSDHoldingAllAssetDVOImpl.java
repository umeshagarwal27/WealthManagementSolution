package com.ciobera.fwms.order.model.vo.readonly;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 07 19:36:34 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSDHoldingAllAssetDVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public FWMSDHoldingAllAssetDVOImpl() {
    }

    /**
     * Returns the bind variable value for loggedInUserId.
     * @return bind variable value for loggedInUserId
     */
    public String getloggedInUserId() {
        return (String) getNamedWhereClauseParam("loggedInUserId");
    }

    /**
     * Sets <code>value</code> for bind variable loggedInUserId.
     * @param value value to bind as loggedInUserId
     */
    public void setloggedInUserId(String value) {
        setNamedWhereClauseParam("loggedInUserId", value);
    }
}

