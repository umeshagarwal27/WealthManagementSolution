package com.ciobera.fwms.system.model.vo.readonly;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 26 16:31:03 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProductIdSeqRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        MaxProductId;
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
    public static final int MAXPRODUCTID = AttributesEnum.MaxProductId.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ProductIdSeqRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute MaxProductId.
     * @return the MaxProductId
     */
    public Long getMaxProductId() {
        return (Long) getAttributeInternal(MAXPRODUCTID);
    }
}

