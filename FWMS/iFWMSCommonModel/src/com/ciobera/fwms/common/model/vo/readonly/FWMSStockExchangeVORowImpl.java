package com.ciobera.fwms.common.model.vo.readonly;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 14 14:45:12 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSStockExchangeVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        WmsStockExchangeId,
        WmsStockExchangeDesc;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return FWMSStockExchangeVORowImpl.AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return FWMSStockExchangeVORowImpl.AttributesEnum.firstIndex() + FWMSStockExchangeVORowImpl.AttributesEnum
                                                                                                      .staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = FWMSStockExchangeVORowImpl.AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int WMSSTOCKEXCHANGEID = AttributesEnum.WmsStockExchangeId.index();
    public static final int WMSSTOCKEXCHANGEDESC = AttributesEnum.WmsStockExchangeDesc.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSStockExchangeVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute WmsStockExchangeId.
     * @return the WmsStockExchangeId
     */
    public Integer getWmsStockExchangeId() {
        return (Integer) getAttributeInternal(WMSSTOCKEXCHANGEID);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsStockExchangeDesc.
     * @return the WmsStockExchangeDesc
     */
    public String getWmsStockExchangeDesc() {
        return (String) getAttributeInternal(WMSSTOCKEXCHANGEDESC);
    }
}
