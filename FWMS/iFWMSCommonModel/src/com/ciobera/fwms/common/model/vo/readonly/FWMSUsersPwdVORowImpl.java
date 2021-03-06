package com.ciobera.fwms.common.model.vo.readonly;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 10 10:22:07 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSUsersPwdVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        WmsUserId,
        WmsDate,
        WmsPc;
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
    public static final int WMSUSERID = AttributesEnum.WmsUserId.index();
    public static final int WMSDATE = AttributesEnum.WmsDate.index();
    public static final int WMSPC = AttributesEnum.WmsPc.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSUsersPwdVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute WmsUserId.
     * @return the WmsUserId
     */
    public String getWmsUserId() {
        return (String) getAttributeInternal(WMSUSERID);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsDate.
     * @return the WmsDate
     */
    public Timestamp getWmsDate() {
        return (Timestamp) getAttributeInternal(WMSDATE);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsPc.
     * @return the WmsPc
     */
    public String getWmsPc() {
        return (String) getAttributeInternal(WMSPC);
    }
}

