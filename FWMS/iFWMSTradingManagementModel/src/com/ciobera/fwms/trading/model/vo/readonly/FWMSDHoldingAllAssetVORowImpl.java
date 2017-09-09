package com.ciobera.fwms.trading.model.vo.readonly;

import java.math.BigDecimal;

import oracle.jbo.RowIterator;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 07 19:37:39 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSDHoldingAllAssetVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        FhaAname,
        FhaMvB,
        FhaAsset,
        FWMSDHoldingAllAssetDVO;
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
    public static final int FHAANAME = AttributesEnum.FhaAname.index();
    public static final int FHAMVB = AttributesEnum.FhaMvB.index();
    public static final int FHAASSET = AttributesEnum.FhaAsset.index();
    public static final int FWMSDHOLDINGALLASSETDVO = AttributesEnum.FWMSDHoldingAllAssetDVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSDHoldingAllAssetVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute FhaAname.
     * @return the FhaAname
     */
    public String getFhaAname() {
        return (String) getAttributeInternal(FHAANAME);
    }

    /**
     * Gets the attribute value for the calculated attribute FhaMvB.
     * @return the FhaMvB
     */
    public BigDecimal getFhaMvB() {
        return (BigDecimal) getAttributeInternal(FHAMVB);
    }

    /**
     * Gets the attribute value for the calculated attribute FhaAsset.
     * @return the FhaAsset
     */
    public Long getFhaAsset() {
        return (Long) getAttributeInternal(FHAASSET);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link FWMSDHoldingAllAssetDVO.
     */
    public RowIterator getFWMSDHoldingAllAssetDVO() {
        return (RowIterator) getAttributeInternal(FWMSDHOLDINGALLASSETDVO);
    }
}

