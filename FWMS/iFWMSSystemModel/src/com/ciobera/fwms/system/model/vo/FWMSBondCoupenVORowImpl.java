package com.ciobera.fwms.system.model.vo;

import com.ciobera.fwms.system.model.eo.FWMSBondCoupenEOImpl;

import java.math.BigDecimal;

import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 26 16:09:47 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSBondCoupenVORowImpl extends ViewRowImpl {
    public static final int ENTITY_FWMSBONDCOUPENEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        WmsSno,
        WmsBond,
        WmsDate,
        WmsCoupen;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int WMSSNO = AttributesEnum.WmsSno.index();
    public static final int WMSBOND = AttributesEnum.WmsBond.index();
    public static final int WMSDATE = AttributesEnum.WmsDate.index();
    public static final int WMSCOUPEN = AttributesEnum.WmsCoupen.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSBondCoupenVORowImpl() {
    }

    /**
     * Gets FWMSBondCoupenEO entity object.
     * @return the FWMSBondCoupenEO
     */
    public FWMSBondCoupenEOImpl getFWMSBondCoupenEO() {
        return (FWMSBondCoupenEOImpl) getEntity(ENTITY_FWMSBONDCOUPENEO);
    }

    /**
     * Gets the attribute value for WMS_SNO using the alias name WmsSno.
     * @return the WMS_SNO
     */
    public Long getWmsSno() {
        return (Long) getAttributeInternal(WMSSNO);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_SNO using the alias name WmsSno.
     * @param value value to set the WMS_SNO
     */
    public void setWmsSno(Long value) {
        setAttributeInternal(WMSSNO, value);
    }

    /**
     * Gets the attribute value for WMS_BOND using the alias name WmsBond.
     * @return the WMS_BOND
     */
    public Long getWmsBond() {
        return (Long) getAttributeInternal(WMSBOND);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND using the alias name WmsBond.
     * @param value value to set the WMS_BOND
     */
    public void setWmsBond(Long value) {
        setAttributeInternal(WMSBOND, value);
    }

    /**
     * Gets the attribute value for WMS_DATE using the alias name WmsDate.
     * @return the WMS_DATE
     */
    public Date getWmsDate() {
        return (Date) getAttributeInternal(WMSDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_DATE using the alias name WmsDate.
     * @param value value to set the WMS_DATE
     */
    public void setWmsDate(Date value) {
        setAttributeInternal(WMSDATE, value);
    }

    /**
     * Gets the attribute value for WMS_COUPEN using the alias name WmsCoupen.
     * @return the WMS_COUPEN
     */
    public BigDecimal getWmsCoupen() {
        return (BigDecimal) getAttributeInternal(WMSCOUPEN);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_COUPEN using the alias name WmsCoupen.
     * @param value value to set the WMS_COUPEN
     */
    public void setWmsCoupen(BigDecimal value) {
        setAttributeInternal(WMSCOUPEN, value);
    }
}

