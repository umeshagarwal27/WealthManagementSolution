package com.ciobera.fwms.system.model.am;

import com.ciobera.fwms.system.model.am.common.SystemAM;
import com.ciobera.fwms.system.model.vo.FWMSBondCoupenVOImpl;
import com.ciobera.fwms.system.model.vo.FWMSProductVOImpl;
import com.ciobera.fwms.system.model.vo.FWMSProductVORowImpl;

import java.util.Calendar;

import oracle.jbo.Row;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 15 18:06:02 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SystemAMImpl extends ApplicationModuleImpl implements SystemAM {
    /**
     * This is the default constructor (do not remove).
     */
    public SystemAMImpl() {
    }

    /**
     * Container's getter for FWMSProduct.
     * @return FWMSProduct
     */
    public FWMSProductVOImpl getFWMSProduct() {
        return (FWMSProductVOImpl) findViewObject("FWMSProduct");
    }

    /**
     * Container's getter for FWMSBondCoupen.
     * @return FWMSBondCoupen
     */
    public FWMSBondCoupenVOImpl getFWMSBondCoupen() {
        return (FWMSBondCoupenVOImpl) findViewObject("FWMSBondCoupen");
    }

    /**
     * Container's getter for FWMSProductVOToFWMSBondCoupen.
     * @return FWMSProductVOToFWMSBondCoupen
     */
    public ViewLinkImpl getFWMSProductVOToFWMSBondCoupen() {
        return (ViewLinkImpl) findViewLink("FWMSProductVOToFWMSBondCoupen");
    }

    public void updateProductRecord(String mode, String updatedBy) {
        Calendar cal = Calendar.getInstance();
        FWMSProductVOImpl productVO = getFWMSProduct();
        if (productVO != null) {
            Row row = productVO.getCurrentRow();
            if (row != null) {
                if ("EDIT".equalsIgnoreCase(mode)) {
                    row.setAttribute(FWMSProductVORowImpl.WMSLASTUPDATEUID, updatedBy);
                    row.setAttribute(FWMSProductVORowImpl.WMSLASTUPDATEDATE, cal.getTime());
                }
                if ("CREATE".equalsIgnoreCase(mode)) {
                    row.setAttribute(FWMSProductVORowImpl.WMSENTERUID, updatedBy);
                    row.setAttribute(FWMSProductVORowImpl.WMSENTERDATE, cal.getTime());
                }
                if ("APPROVE".equalsIgnoreCase(mode)) {
                    row.setAttribute(FWMSProductVORowImpl.WMSAPPROVEUID, updatedBy);
                    row.setAttribute(FWMSProductVORowImpl.WMSAPPROVEDATE, cal.getTime());
                    this.getDBTransaction().commit();
                }
            }
        }
    }
    
    public void executeEmptyRowSetBondCoupen(){
        FWMSBondCoupenVOImpl bondCoupenVO = getFWMSBondCoupen();
        if(bondCoupenVO != null){
            bondCoupenVO.executeEmptyRowSet();
        }
    }
}
