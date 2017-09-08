package com.ciobera.fwms.order.model.vo.readonly;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 07 19:38:26 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSDHoldingAllCountryVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        FhaCname,
        FhaMvB,
        FhaCountry,
        FWMSHoldingAllCountryDVO;
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
    public static final int FHACNAME = AttributesEnum.FhaCname.index();
    public static final int FHAMVB = AttributesEnum.FhaMvB.index();
    public static final int FHACOUNTRY = AttributesEnum.FhaCountry.index();
    public static final int FWMSHOLDINGALLCOUNTRYDVO = AttributesEnum.FWMSHoldingAllCountryDVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSDHoldingAllCountryVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute FhaCname.
     * @return the FhaCname
     */
    public String getFhaCname() {
        return (String) getAttributeInternal(FHACNAME);
    }

    /**
     * Gets the attribute value for the calculated attribute FhaMvB.
     * @return the FhaMvB
     */
    public Number getFhaMvB() {
        return (Number) getAttributeInternal(FHAMVB);
    }

    /**
     * Gets the attribute value for the calculated attribute FhaCountry.
     * @return the FhaCountry
     */
    public Long getFhaCountry() {
        return (Long) getAttributeInternal(FHACOUNTRY);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link FWMSHoldingAllCountryDVO.
     */
    public RowIterator getFWMSHoldingAllCountryDVO() {
        return (RowIterator) getAttributeInternal(FWMSHOLDINGALLCOUNTRYDVO);
    }
}
