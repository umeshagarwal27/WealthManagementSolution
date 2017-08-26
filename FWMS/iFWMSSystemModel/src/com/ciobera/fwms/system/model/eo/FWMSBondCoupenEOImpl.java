package com.ciobera.fwms.system.model.eo;

import java.math.BigDecimal;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 26 16:08:53 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSBondCoupenEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        WmsSno,
        WmsBond,
        WmsDate,
        WmsCoupen,
        FWMSProductEO;
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
    public static final int FWMSPRODUCTEO = AttributesEnum.FWMSProductEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSBondCoupenEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.ciobera.fwms.system.model.eo.FWMSBondCoupenEO");
    }


    /**
     * Gets the attribute value for WmsSno, using the alias name WmsSno.
     * @return the value of WmsSno
     */
    public Long getWmsSno() {
        return (Long) getAttributeInternal(WMSSNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for WmsSno.
     * @param value value to set the WmsSno
     */
    public void setWmsSno(Long value) {
        setAttributeInternal(WMSSNO, value);
    }

    /**
     * Gets the attribute value for WmsBond, using the alias name WmsBond.
     * @return the value of WmsBond
     */
    public Long getWmsBond() {
        return (Long) getAttributeInternal(WMSBOND);
    }

    /**
     * Sets <code>value</code> as the attribute value for WmsBond.
     * @param value value to set the WmsBond
     */
    public void setWmsBond(Long value) {
        setAttributeInternal(WMSBOND, value);
    }

    /**
     * Gets the attribute value for WmsDate, using the alias name WmsDate.
     * @return the value of WmsDate
     */
    public Date getWmsDate() {
        return (Date) getAttributeInternal(WMSDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for WmsDate.
     * @param value value to set the WmsDate
     */
    public void setWmsDate(Date value) {
        setAttributeInternal(WMSDATE, value);
    }

    /**
     * Gets the attribute value for WmsCoupen, using the alias name WmsCoupen.
     * @return the value of WmsCoupen
     */
    public BigDecimal getWmsCoupen() {
        return (BigDecimal) getAttributeInternal(WMSCOUPEN);
    }

    /**
     * Sets <code>value</code> as the attribute value for WmsCoupen.
     * @param value value to set the WmsCoupen
     */
    public void setWmsCoupen(BigDecimal value) {
        setAttributeInternal(WMSCOUPEN, value);
    }

    /**
     * @return the associated entity FWMSProductEOImpl.
     */
    public FWMSProductEOImpl getFWMSProductEO() {
        return (FWMSProductEOImpl) getAttributeInternal(FWMSPRODUCTEO);
    }

    /**
     * Sets <code>value</code> as the associated entity FWMSProductEOImpl.
     */
    public void setFWMSProductEO(FWMSProductEOImpl value) {
        setAttributeInternal(FWMSPRODUCTEO, value);
    }


    /**
     * @param wmsSno key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Long wmsSno) {
        return new Key(new Object[] { wmsSno });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}

