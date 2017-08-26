package com.ciobera.fwms.system.model.vo;

import com.ciobera.fwms.system.model.eo.FWMSProductEOImpl;

import java.math.BigDecimal;

import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Aug 26 16:10:15 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FWMSProductVORowImpl extends ViewRowImpl {
    public static final int ENTITY_FWMSPRODUCTEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        WmsProductType,
        WmsProductId,
        WmsProductName,
        WmsTicker,
        WmsRetuerCode,
        WmsBloombergCode,
        WmsIsinCode,
        WmsOtherCode,
        WmsOtherDesc,
        WmsPriceUpdate,
        WmsCountry,
        WmsCurrency,
        WmsStockExchange,
        WmsSector,
        WmsAsset,
        WmsBondIssueNo,
        WmsBondIssueDate,
        WmsBondMaturityDt,
        WmsBondUnitPrice,
        WmsBondCouponRate,
        WmsBondCouponFrequency,
        WmsBondDivisorDaysMonth,
        WmsBondDivisorDaysYear,
        WmsBondCoupon1stIntdate,
        WmsBondCoupon2ndIntdate,
        WmsBondCoupon3rdIntdate,
        WmsBondCoupon4thIntdate,
        WmsPutCallFlag,
        WmsPutCallExpiryDate,
        WmsPutCallLotsize,
        WmsPutCallSprice,
        WmsPutCallStatus,
        WmsMutualFundsIssue,
        WmsMutualInhouse3rdFlag,
        WmsMutualFundsClosdedOpen,
        WmsMutualFundsNavFlag,
        WmsComments,
        WmsStatus,
        WmsEnterUid,
        WmsEnterFpc,
        WmsEnterDate,
        WmsLastUpdateUid,
        WmsLastFpc,
        WmsLastUpdateDate,
        WmsApproveUid,
        WmsApproveFpc,
        WmsApproveDate,
        WmsMutualFundsValue,
        WmsMutualIbType,
        WmsSedol,
        WmsAssetTrans,
        WmsCountryTrans,
        WmsCurrencyTrans,
        WmsSectorTrans,
        WmsStockExchangeTrans,
        FWMSBondCoupenVO;
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
    public static final int WMSPRODUCTTYPE = AttributesEnum.WmsProductType.index();
    public static final int WMSPRODUCTID = AttributesEnum.WmsProductId.index();
    public static final int WMSPRODUCTNAME = AttributesEnum.WmsProductName.index();
    public static final int WMSTICKER = AttributesEnum.WmsTicker.index();
    public static final int WMSRETUERCODE = AttributesEnum.WmsRetuerCode.index();
    public static final int WMSBLOOMBERGCODE = AttributesEnum.WmsBloombergCode.index();
    public static final int WMSISINCODE = AttributesEnum.WmsIsinCode.index();
    public static final int WMSOTHERCODE = AttributesEnum.WmsOtherCode.index();
    public static final int WMSOTHERDESC = AttributesEnum.WmsOtherDesc.index();
    public static final int WMSPRICEUPDATE = AttributesEnum.WmsPriceUpdate.index();
    public static final int WMSCOUNTRY = AttributesEnum.WmsCountry.index();
    public static final int WMSCURRENCY = AttributesEnum.WmsCurrency.index();
    public static final int WMSSTOCKEXCHANGE = AttributesEnum.WmsStockExchange.index();
    public static final int WMSSECTOR = AttributesEnum.WmsSector.index();
    public static final int WMSASSET = AttributesEnum.WmsAsset.index();
    public static final int WMSBONDISSUENO = AttributesEnum.WmsBondIssueNo.index();
    public static final int WMSBONDISSUEDATE = AttributesEnum.WmsBondIssueDate.index();
    public static final int WMSBONDMATURITYDT = AttributesEnum.WmsBondMaturityDt.index();
    public static final int WMSBONDUNITPRICE = AttributesEnum.WmsBondUnitPrice.index();
    public static final int WMSBONDCOUPONRATE = AttributesEnum.WmsBondCouponRate.index();
    public static final int WMSBONDCOUPONFREQUENCY = AttributesEnum.WmsBondCouponFrequency.index();
    public static final int WMSBONDDIVISORDAYSMONTH = AttributesEnum.WmsBondDivisorDaysMonth.index();
    public static final int WMSBONDDIVISORDAYSYEAR = AttributesEnum.WmsBondDivisorDaysYear.index();
    public static final int WMSBONDCOUPON1STINTDATE = AttributesEnum.WmsBondCoupon1stIntdate.index();
    public static final int WMSBONDCOUPON2NDINTDATE = AttributesEnum.WmsBondCoupon2ndIntdate.index();
    public static final int WMSBONDCOUPON3RDINTDATE = AttributesEnum.WmsBondCoupon3rdIntdate.index();
    public static final int WMSBONDCOUPON4THINTDATE = AttributesEnum.WmsBondCoupon4thIntdate.index();
    public static final int WMSPUTCALLFLAG = AttributesEnum.WmsPutCallFlag.index();
    public static final int WMSPUTCALLEXPIRYDATE = AttributesEnum.WmsPutCallExpiryDate.index();
    public static final int WMSPUTCALLLOTSIZE = AttributesEnum.WmsPutCallLotsize.index();
    public static final int WMSPUTCALLSPRICE = AttributesEnum.WmsPutCallSprice.index();
    public static final int WMSPUTCALLSTATUS = AttributesEnum.WmsPutCallStatus.index();
    public static final int WMSMUTUALFUNDSISSUE = AttributesEnum.WmsMutualFundsIssue.index();
    public static final int WMSMUTUALINHOUSE3RDFLAG = AttributesEnum.WmsMutualInhouse3rdFlag.index();
    public static final int WMSMUTUALFUNDSCLOSDEDOPEN = AttributesEnum.WmsMutualFundsClosdedOpen.index();
    public static final int WMSMUTUALFUNDSNAVFLAG = AttributesEnum.WmsMutualFundsNavFlag.index();
    public static final int WMSCOMMENTS = AttributesEnum.WmsComments.index();
    public static final int WMSSTATUS = AttributesEnum.WmsStatus.index();
    public static final int WMSENTERUID = AttributesEnum.WmsEnterUid.index();
    public static final int WMSENTERFPC = AttributesEnum.WmsEnterFpc.index();
    public static final int WMSENTERDATE = AttributesEnum.WmsEnterDate.index();
    public static final int WMSLASTUPDATEUID = AttributesEnum.WmsLastUpdateUid.index();
    public static final int WMSLASTFPC = AttributesEnum.WmsLastFpc.index();
    public static final int WMSLASTUPDATEDATE = AttributesEnum.WmsLastUpdateDate.index();
    public static final int WMSAPPROVEUID = AttributesEnum.WmsApproveUid.index();
    public static final int WMSAPPROVEFPC = AttributesEnum.WmsApproveFpc.index();
    public static final int WMSAPPROVEDATE = AttributesEnum.WmsApproveDate.index();
    public static final int WMSMUTUALFUNDSVALUE = AttributesEnum.WmsMutualFundsValue.index();
    public static final int WMSMUTUALIBTYPE = AttributesEnum.WmsMutualIbType.index();
    public static final int WMSSEDOL = AttributesEnum.WmsSedol.index();
    public static final int WMSASSETTRANS = AttributesEnum.WmsAssetTrans.index();
    public static final int WMSCOUNTRYTRANS = AttributesEnum.WmsCountryTrans.index();
    public static final int WMSCURRENCYTRANS = AttributesEnum.WmsCurrencyTrans.index();
    public static final int WMSSECTORTRANS = AttributesEnum.WmsSectorTrans.index();
    public static final int WMSSTOCKEXCHANGETRANS = AttributesEnum.WmsStockExchangeTrans.index();
    public static final int FWMSBONDCOUPENVO = AttributesEnum.FWMSBondCoupenVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FWMSProductVORowImpl() {
    }

    /**
     * Gets FWMSProductEO entity object.
     * @return the FWMSProductEO
     */
    public FWMSProductEOImpl getFWMSProductEO() {
        return (FWMSProductEOImpl) getEntity(ENTITY_FWMSPRODUCTEO);
    }

    /**
     * Gets the attribute value for WMS_PRODUCT_TYPE using the alias name WmsProductType.
     * @return the WMS_PRODUCT_TYPE
     */
    public String getWmsProductType() {
        return (String) getAttributeInternal(WMSPRODUCTTYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PRODUCT_TYPE using the alias name WmsProductType.
     * @param value value to set the WMS_PRODUCT_TYPE
     */
    public void setWmsProductType(String value) {
        setAttributeInternal(WMSPRODUCTTYPE, value);
    }

    /**
     * Gets the attribute value for WMS_PRODUCT_ID using the alias name WmsProductId.
     * @return the WMS_PRODUCT_ID
     */
    public Long getWmsProductId() {
        return (Long) getAttributeInternal(WMSPRODUCTID);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PRODUCT_ID using the alias name WmsProductId.
     * @param value value to set the WMS_PRODUCT_ID
     */
    public void setWmsProductId(Long value) {
        setAttributeInternal(WMSPRODUCTID, value);
    }

    /**
     * Gets the attribute value for WMS_PRODUCT_NAME using the alias name WmsProductName.
     * @return the WMS_PRODUCT_NAME
     */
    public String getWmsProductName() {
        return (String) getAttributeInternal(WMSPRODUCTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PRODUCT_NAME using the alias name WmsProductName.
     * @param value value to set the WMS_PRODUCT_NAME
     */
    public void setWmsProductName(String value) {
        setAttributeInternal(WMSPRODUCTNAME, value);
    }

    /**
     * Gets the attribute value for WMS_TICKER using the alias name WmsTicker.
     * @return the WMS_TICKER
     */
    public String getWmsTicker() {
        return (String) getAttributeInternal(WMSTICKER);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_TICKER using the alias name WmsTicker.
     * @param value value to set the WMS_TICKER
     */
    public void setWmsTicker(String value) {
        setAttributeInternal(WMSTICKER, value);
    }

    /**
     * Gets the attribute value for WMS_RETUER_CODE using the alias name WmsRetuerCode.
     * @return the WMS_RETUER_CODE
     */
    public String getWmsRetuerCode() {
        return (String) getAttributeInternal(WMSRETUERCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_RETUER_CODE using the alias name WmsRetuerCode.
     * @param value value to set the WMS_RETUER_CODE
     */
    public void setWmsRetuerCode(String value) {
        setAttributeInternal(WMSRETUERCODE, value);
    }

    /**
     * Gets the attribute value for WMS_BLOOMBERG_CODE using the alias name WmsBloombergCode.
     * @return the WMS_BLOOMBERG_CODE
     */
    public String getWmsBloombergCode() {
        return (String) getAttributeInternal(WMSBLOOMBERGCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BLOOMBERG_CODE using the alias name WmsBloombergCode.
     * @param value value to set the WMS_BLOOMBERG_CODE
     */
    public void setWmsBloombergCode(String value) {
        setAttributeInternal(WMSBLOOMBERGCODE, value);
    }

    /**
     * Gets the attribute value for WMS_ISIN_CODE using the alias name WmsIsinCode.
     * @return the WMS_ISIN_CODE
     */
    public String getWmsIsinCode() {
        return (String) getAttributeInternal(WMSISINCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_ISIN_CODE using the alias name WmsIsinCode.
     * @param value value to set the WMS_ISIN_CODE
     */
    public void setWmsIsinCode(String value) {
        setAttributeInternal(WMSISINCODE, value);
    }

    /**
     * Gets the attribute value for WMS_OTHER_CODE using the alias name WmsOtherCode.
     * @return the WMS_OTHER_CODE
     */
    public String getWmsOtherCode() {
        return (String) getAttributeInternal(WMSOTHERCODE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_OTHER_CODE using the alias name WmsOtherCode.
     * @param value value to set the WMS_OTHER_CODE
     */
    public void setWmsOtherCode(String value) {
        setAttributeInternal(WMSOTHERCODE, value);
    }

    /**
     * Gets the attribute value for WMS_OTHER_DESC using the alias name WmsOtherDesc.
     * @return the WMS_OTHER_DESC
     */
    public String getWmsOtherDesc() {
        return (String) getAttributeInternal(WMSOTHERDESC);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_OTHER_DESC using the alias name WmsOtherDesc.
     * @param value value to set the WMS_OTHER_DESC
     */
    public void setWmsOtherDesc(String value) {
        setAttributeInternal(WMSOTHERDESC, value);
    }

    /**
     * Gets the attribute value for WMS_PRICE_UPDATE using the alias name WmsPriceUpdate.
     * @return the WMS_PRICE_UPDATE
     */
    public String getWmsPriceUpdate() {
        return (String) getAttributeInternal(WMSPRICEUPDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PRICE_UPDATE using the alias name WmsPriceUpdate.
     * @param value value to set the WMS_PRICE_UPDATE
     */
    public void setWmsPriceUpdate(String value) {
        setAttributeInternal(WMSPRICEUPDATE, value);
    }

    /**
     * Gets the attribute value for WMS_COUNTRY using the alias name WmsCountry.
     * @return the WMS_COUNTRY
     */
    public Integer getWmsCountry() {
        return (Integer) getAttributeInternal(WMSCOUNTRY);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_COUNTRY using the alias name WmsCountry.
     * @param value value to set the WMS_COUNTRY
     */
    public void setWmsCountry(Integer value) {
        setAttributeInternal(WMSCOUNTRY, value);
    }

    /**
     * Gets the attribute value for WMS_CURRENCY using the alias name WmsCurrency.
     * @return the WMS_CURRENCY
     */
    public Integer getWmsCurrency() {
        return (Integer) getAttributeInternal(WMSCURRENCY);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_CURRENCY using the alias name WmsCurrency.
     * @param value value to set the WMS_CURRENCY
     */
    public void setWmsCurrency(Integer value) {
        setAttributeInternal(WMSCURRENCY, value);
    }

    /**
     * Gets the attribute value for WMS_STOCK_EXCHANGE using the alias name WmsStockExchange.
     * @return the WMS_STOCK_EXCHANGE
     */
    public Integer getWmsStockExchange() {
        return (Integer) getAttributeInternal(WMSSTOCKEXCHANGE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_STOCK_EXCHANGE using the alias name WmsStockExchange.
     * @param value value to set the WMS_STOCK_EXCHANGE
     */
    public void setWmsStockExchange(Integer value) {
        setAttributeInternal(WMSSTOCKEXCHANGE, value);
    }

    /**
     * Gets the attribute value for WMS_SECTOR using the alias name WmsSector.
     * @return the WMS_SECTOR
     */
    public Integer getWmsSector() {
        return (Integer) getAttributeInternal(WMSSECTOR);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_SECTOR using the alias name WmsSector.
     * @param value value to set the WMS_SECTOR
     */
    public void setWmsSector(Integer value) {
        setAttributeInternal(WMSSECTOR, value);
    }

    /**
     * Gets the attribute value for WMS_ASSET using the alias name WmsAsset.
     * @return the WMS_ASSET
     */
    public Integer getWmsAsset() {
        return (Integer) getAttributeInternal(WMSASSET);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_ASSET using the alias name WmsAsset.
     * @param value value to set the WMS_ASSET
     */
    public void setWmsAsset(Integer value) {
        setAttributeInternal(WMSASSET, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_ISSUE_NO using the alias name WmsBondIssueNo.
     * @return the WMS_BOND_ISSUE_NO
     */
    public String getWmsBondIssueNo() {
        return (String) getAttributeInternal(WMSBONDISSUENO);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_ISSUE_NO using the alias name WmsBondIssueNo.
     * @param value value to set the WMS_BOND_ISSUE_NO
     */
    public void setWmsBondIssueNo(String value) {
        setAttributeInternal(WMSBONDISSUENO, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_ISSUE_DATE using the alias name WmsBondIssueDate.
     * @return the WMS_BOND_ISSUE_DATE
     */
    public Date getWmsBondIssueDate() {
        return (Date) getAttributeInternal(WMSBONDISSUEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_ISSUE_DATE using the alias name WmsBondIssueDate.
     * @param value value to set the WMS_BOND_ISSUE_DATE
     */
    public void setWmsBondIssueDate(Date value) {
        setAttributeInternal(WMSBONDISSUEDATE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_MATURITY_DT using the alias name WmsBondMaturityDt.
     * @return the WMS_BOND_MATURITY_DT
     */
    public Date getWmsBondMaturityDt() {
        return (Date) getAttributeInternal(WMSBONDMATURITYDT);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_MATURITY_DT using the alias name WmsBondMaturityDt.
     * @param value value to set the WMS_BOND_MATURITY_DT
     */
    public void setWmsBondMaturityDt(Date value) {
        setAttributeInternal(WMSBONDMATURITYDT, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_UNIT_PRICE using the alias name WmsBondUnitPrice.
     * @return the WMS_BOND_UNIT_PRICE
     */
    public BigDecimal getWmsBondUnitPrice() {
        return (BigDecimal) getAttributeInternal(WMSBONDUNITPRICE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_UNIT_PRICE using the alias name WmsBondUnitPrice.
     * @param value value to set the WMS_BOND_UNIT_PRICE
     */
    public void setWmsBondUnitPrice(BigDecimal value) {
        setAttributeInternal(WMSBONDUNITPRICE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_RATE using the alias name WmsBondCouponRate.
     * @return the WMS_BOND_COUPON_RATE
     */
    public BigDecimal getWmsBondCouponRate() {
        return (BigDecimal) getAttributeInternal(WMSBONDCOUPONRATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_RATE using the alias name WmsBondCouponRate.
     * @param value value to set the WMS_BOND_COUPON_RATE
     */
    public void setWmsBondCouponRate(BigDecimal value) {
        setAttributeInternal(WMSBONDCOUPONRATE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_FREQUENCY using the alias name WmsBondCouponFrequency.
     * @return the WMS_BOND_COUPON_FREQUENCY
     */
    public String getWmsBondCouponFrequency() {
        return (String) getAttributeInternal(WMSBONDCOUPONFREQUENCY);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_FREQUENCY using the alias name WmsBondCouponFrequency.
     * @param value value to set the WMS_BOND_COUPON_FREQUENCY
     */
    public void setWmsBondCouponFrequency(String value) {
        setAttributeInternal(WMSBONDCOUPONFREQUENCY, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_DIVISOR_DAYS_MONTH using the alias name WmsBondDivisorDaysMonth.
     * @return the WMS_BOND_DIVISOR_DAYS_MONTH
     */
    public String getWmsBondDivisorDaysMonth() {
        return (String) getAttributeInternal(WMSBONDDIVISORDAYSMONTH);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_DIVISOR_DAYS_MONTH using the alias name WmsBondDivisorDaysMonth.
     * @param value value to set the WMS_BOND_DIVISOR_DAYS_MONTH
     */
    public void setWmsBondDivisorDaysMonth(String value) {
        setAttributeInternal(WMSBONDDIVISORDAYSMONTH, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_DIVISOR_DAYS_YEAR using the alias name WmsBondDivisorDaysYear.
     * @return the WMS_BOND_DIVISOR_DAYS_YEAR
     */
    public Integer getWmsBondDivisorDaysYear() {
        return (Integer) getAttributeInternal(WMSBONDDIVISORDAYSYEAR);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_DIVISOR_DAYS_YEAR using the alias name WmsBondDivisorDaysYear.
     * @param value value to set the WMS_BOND_DIVISOR_DAYS_YEAR
     */
    public void setWmsBondDivisorDaysYear(Integer value) {
        setAttributeInternal(WMSBONDDIVISORDAYSYEAR, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_1ST_INTDATE using the alias name WmsBondCoupon1stIntdate.
     * @return the WMS_BOND_COUPON_1ST_INTDATE
     */
    public String getWmsBondCoupon1stIntdate() {
        return (String) getAttributeInternal(WMSBONDCOUPON1STINTDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_1ST_INTDATE using the alias name WmsBondCoupon1stIntdate.
     * @param value value to set the WMS_BOND_COUPON_1ST_INTDATE
     */
    public void setWmsBondCoupon1stIntdate(String value) {
        setAttributeInternal(WMSBONDCOUPON1STINTDATE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_2ND_INTDATE using the alias name WmsBondCoupon2ndIntdate.
     * @return the WMS_BOND_COUPON_2ND_INTDATE
     */
    public String getWmsBondCoupon2ndIntdate() {
        return (String) getAttributeInternal(WMSBONDCOUPON2NDINTDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_2ND_INTDATE using the alias name WmsBondCoupon2ndIntdate.
     * @param value value to set the WMS_BOND_COUPON_2ND_INTDATE
     */
    public void setWmsBondCoupon2ndIntdate(String value) {
        setAttributeInternal(WMSBONDCOUPON2NDINTDATE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_3RD_INTDATE using the alias name WmsBondCoupon3rdIntdate.
     * @return the WMS_BOND_COUPON_3RD_INTDATE
     */
    public String getWmsBondCoupon3rdIntdate() {
        return (String) getAttributeInternal(WMSBONDCOUPON3RDINTDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_3RD_INTDATE using the alias name WmsBondCoupon3rdIntdate.
     * @param value value to set the WMS_BOND_COUPON_3RD_INTDATE
     */
    public void setWmsBondCoupon3rdIntdate(String value) {
        setAttributeInternal(WMSBONDCOUPON3RDINTDATE, value);
    }

    /**
     * Gets the attribute value for WMS_BOND_COUPON_4TH_INTDATE using the alias name WmsBondCoupon4thIntdate.
     * @return the WMS_BOND_COUPON_4TH_INTDATE
     */
    public String getWmsBondCoupon4thIntdate() {
        return (String) getAttributeInternal(WMSBONDCOUPON4THINTDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_BOND_COUPON_4TH_INTDATE using the alias name WmsBondCoupon4thIntdate.
     * @param value value to set the WMS_BOND_COUPON_4TH_INTDATE
     */
    public void setWmsBondCoupon4thIntdate(String value) {
        setAttributeInternal(WMSBONDCOUPON4THINTDATE, value);
    }

    /**
     * Gets the attribute value for WMS_PUT_CALL_FLAG using the alias name WmsPutCallFlag.
     * @return the WMS_PUT_CALL_FLAG
     */
    public String getWmsPutCallFlag() {
        return (String) getAttributeInternal(WMSPUTCALLFLAG);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PUT_CALL_FLAG using the alias name WmsPutCallFlag.
     * @param value value to set the WMS_PUT_CALL_FLAG
     */
    public void setWmsPutCallFlag(String value) {
        setAttributeInternal(WMSPUTCALLFLAG, value);
    }

    /**
     * Gets the attribute value for WMS_PUT_CALL_EXPIRY_DATE using the alias name WmsPutCallExpiryDate.
     * @return the WMS_PUT_CALL_EXPIRY_DATE
     */
    public Date getWmsPutCallExpiryDate() {
        return (Date) getAttributeInternal(WMSPUTCALLEXPIRYDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PUT_CALL_EXPIRY_DATE using the alias name WmsPutCallExpiryDate.
     * @param value value to set the WMS_PUT_CALL_EXPIRY_DATE
     */
    public void setWmsPutCallExpiryDate(Date value) {
        setAttributeInternal(WMSPUTCALLEXPIRYDATE, value);
    }

    /**
     * Gets the attribute value for WMS_PUT_CALL_LOTSIZE using the alias name WmsPutCallLotsize.
     * @return the WMS_PUT_CALL_LOTSIZE
     */
    public Long getWmsPutCallLotsize() {
        return (Long) getAttributeInternal(WMSPUTCALLLOTSIZE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PUT_CALL_LOTSIZE using the alias name WmsPutCallLotsize.
     * @param value value to set the WMS_PUT_CALL_LOTSIZE
     */
    public void setWmsPutCallLotsize(Long value) {
        setAttributeInternal(WMSPUTCALLLOTSIZE, value);
    }

    /**
     * Gets the attribute value for WMS_PUT_CALL_SPRICE using the alias name WmsPutCallSprice.
     * @return the WMS_PUT_CALL_SPRICE
     */
    public BigDecimal getWmsPutCallSprice() {
        return (BigDecimal) getAttributeInternal(WMSPUTCALLSPRICE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PUT_CALL_SPRICE using the alias name WmsPutCallSprice.
     * @param value value to set the WMS_PUT_CALL_SPRICE
     */
    public void setWmsPutCallSprice(BigDecimal value) {
        setAttributeInternal(WMSPUTCALLSPRICE, value);
    }

    /**
     * Gets the attribute value for WMS_PUT_CALL_STATUS using the alias name WmsPutCallStatus.
     * @return the WMS_PUT_CALL_STATUS
     */
    public BigDecimal getWmsPutCallStatus() {
        return (BigDecimal) getAttributeInternal(WMSPUTCALLSTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_PUT_CALL_STATUS using the alias name WmsPutCallStatus.
     * @param value value to set the WMS_PUT_CALL_STATUS
     */
    public void setWmsPutCallStatus(BigDecimal value) {
        setAttributeInternal(WMSPUTCALLSTATUS, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_FUNDS_ISSUE using the alias name WmsMutualFundsIssue.
     * @return the WMS_MUTUAL_FUNDS_ISSUE
     */
    public String getWmsMutualFundsIssue() {
        return (String) getAttributeInternal(WMSMUTUALFUNDSISSUE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_FUNDS_ISSUE using the alias name WmsMutualFundsIssue.
     * @param value value to set the WMS_MUTUAL_FUNDS_ISSUE
     */
    public void setWmsMutualFundsIssue(String value) {
        setAttributeInternal(WMSMUTUALFUNDSISSUE, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_INHOUSE_3RD_FLAG using the alias name WmsMutualInhouse3rdFlag.
     * @return the WMS_MUTUAL_INHOUSE_3RD_FLAG
     */
    public String getWmsMutualInhouse3rdFlag() {
        return (String) getAttributeInternal(WMSMUTUALINHOUSE3RDFLAG);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_INHOUSE_3RD_FLAG using the alias name WmsMutualInhouse3rdFlag.
     * @param value value to set the WMS_MUTUAL_INHOUSE_3RD_FLAG
     */
    public void setWmsMutualInhouse3rdFlag(String value) {
        setAttributeInternal(WMSMUTUALINHOUSE3RDFLAG, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_FUNDS_CLOSDED_OPEN using the alias name WmsMutualFundsClosdedOpen.
     * @return the WMS_MUTUAL_FUNDS_CLOSDED_OPEN
     */
    public String getWmsMutualFundsClosdedOpen() {
        return (String) getAttributeInternal(WMSMUTUALFUNDSCLOSDEDOPEN);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_FUNDS_CLOSDED_OPEN using the alias name WmsMutualFundsClosdedOpen.
     * @param value value to set the WMS_MUTUAL_FUNDS_CLOSDED_OPEN
     */
    public void setWmsMutualFundsClosdedOpen(String value) {
        setAttributeInternal(WMSMUTUALFUNDSCLOSDEDOPEN, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_FUNDS_NAV_FLAG using the alias name WmsMutualFundsNavFlag.
     * @return the WMS_MUTUAL_FUNDS_NAV_FLAG
     */
    public String getWmsMutualFundsNavFlag() {
        return (String) getAttributeInternal(WMSMUTUALFUNDSNAVFLAG);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_FUNDS_NAV_FLAG using the alias name WmsMutualFundsNavFlag.
     * @param value value to set the WMS_MUTUAL_FUNDS_NAV_FLAG
     */
    public void setWmsMutualFundsNavFlag(String value) {
        setAttributeInternal(WMSMUTUALFUNDSNAVFLAG, value);
    }

    /**
     * Gets the attribute value for WMS_COMMENTS using the alias name WmsComments.
     * @return the WMS_COMMENTS
     */
    public String getWmsComments() {
        return (String) getAttributeInternal(WMSCOMMENTS);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_COMMENTS using the alias name WmsComments.
     * @param value value to set the WMS_COMMENTS
     */
    public void setWmsComments(String value) {
        setAttributeInternal(WMSCOMMENTS, value);
    }

    /**
     * Gets the attribute value for WMS_STATUS using the alias name WmsStatus.
     * @return the WMS_STATUS
     */
    public String getWmsStatus() {
        return (String) getAttributeInternal(WMSSTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_STATUS using the alias name WmsStatus.
     * @param value value to set the WMS_STATUS
     */
    public void setWmsStatus(String value) {
        setAttributeInternal(WMSSTATUS, value);
    }

    /**
     * Gets the attribute value for WMS_ENTER_UID using the alias name WmsEnterUid.
     * @return the WMS_ENTER_UID
     */
    public String getWmsEnterUid() {
        return (String) getAttributeInternal(WMSENTERUID);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_ENTER_UID using the alias name WmsEnterUid.
     * @param value value to set the WMS_ENTER_UID
     */
    public void setWmsEnterUid(String value) {
        setAttributeInternal(WMSENTERUID, value);
    }

    /**
     * Gets the attribute value for WMS_ENTER_FPC using the alias name WmsEnterFpc.
     * @return the WMS_ENTER_FPC
     */
    public String getWmsEnterFpc() {
        return (String) getAttributeInternal(WMSENTERFPC);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_ENTER_FPC using the alias name WmsEnterFpc.
     * @param value value to set the WMS_ENTER_FPC
     */
    public void setWmsEnterFpc(String value) {
        setAttributeInternal(WMSENTERFPC, value);
    }

    /**
     * Gets the attribute value for WMS_ENTER_DATE using the alias name WmsEnterDate.
     * @return the WMS_ENTER_DATE
     */
    public Date getWmsEnterDate() {
        return (Date) getAttributeInternal(WMSENTERDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_ENTER_DATE using the alias name WmsEnterDate.
     * @param value value to set the WMS_ENTER_DATE
     */
    public void setWmsEnterDate(Date value) {
        setAttributeInternal(WMSENTERDATE, value);
    }

    /**
     * Gets the attribute value for WMS_LAST_UPDATE_UID using the alias name WmsLastUpdateUid.
     * @return the WMS_LAST_UPDATE_UID
     */
    public String getWmsLastUpdateUid() {
        return (String) getAttributeInternal(WMSLASTUPDATEUID);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_LAST_UPDATE_UID using the alias name WmsLastUpdateUid.
     * @param value value to set the WMS_LAST_UPDATE_UID
     */
    public void setWmsLastUpdateUid(String value) {
        setAttributeInternal(WMSLASTUPDATEUID, value);
    }

    /**
     * Gets the attribute value for WMS_LAST_FPC using the alias name WmsLastFpc.
     * @return the WMS_LAST_FPC
     */
    public String getWmsLastFpc() {
        return (String) getAttributeInternal(WMSLASTFPC);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_LAST_FPC using the alias name WmsLastFpc.
     * @param value value to set the WMS_LAST_FPC
     */
    public void setWmsLastFpc(String value) {
        setAttributeInternal(WMSLASTFPC, value);
    }

    /**
     * Gets the attribute value for WMS_LAST_UPDATE_DATE using the alias name WmsLastUpdateDate.
     * @return the WMS_LAST_UPDATE_DATE
     */
    public Date getWmsLastUpdateDate() {
        return (Date) getAttributeInternal(WMSLASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_LAST_UPDATE_DATE using the alias name WmsLastUpdateDate.
     * @param value value to set the WMS_LAST_UPDATE_DATE
     */
    public void setWmsLastUpdateDate(Date value) {
        setAttributeInternal(WMSLASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for WMS_APPROVE_UID using the alias name WmsApproveUid.
     * @return the WMS_APPROVE_UID
     */
    public String getWmsApproveUid() {
        return (String) getAttributeInternal(WMSAPPROVEUID);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_APPROVE_UID using the alias name WmsApproveUid.
     * @param value value to set the WMS_APPROVE_UID
     */
    public void setWmsApproveUid(String value) {
        setAttributeInternal(WMSAPPROVEUID, value);
    }

    /**
     * Gets the attribute value for WMS_APPROVE_FPC using the alias name WmsApproveFpc.
     * @return the WMS_APPROVE_FPC
     */
    public String getWmsApproveFpc() {
        return (String) getAttributeInternal(WMSAPPROVEFPC);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_APPROVE_FPC using the alias name WmsApproveFpc.
     * @param value value to set the WMS_APPROVE_FPC
     */
    public void setWmsApproveFpc(String value) {
        setAttributeInternal(WMSAPPROVEFPC, value);
    }

    /**
     * Gets the attribute value for WMS_APPROVE_DATE using the alias name WmsApproveDate.
     * @return the WMS_APPROVE_DATE
     */
    public Date getWmsApproveDate() {
        return (Date) getAttributeInternal(WMSAPPROVEDATE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_APPROVE_DATE using the alias name WmsApproveDate.
     * @param value value to set the WMS_APPROVE_DATE
     */
    public void setWmsApproveDate(Date value) {
        setAttributeInternal(WMSAPPROVEDATE, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_FUNDS_VALUE using the alias name WmsMutualFundsValue.
     * @return the WMS_MUTUAL_FUNDS_VALUE
     */
    public BigDecimal getWmsMutualFundsValue() {
        return (BigDecimal) getAttributeInternal(WMSMUTUALFUNDSVALUE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_FUNDS_VALUE using the alias name WmsMutualFundsValue.
     * @param value value to set the WMS_MUTUAL_FUNDS_VALUE
     */
    public void setWmsMutualFundsValue(BigDecimal value) {
        setAttributeInternal(WMSMUTUALFUNDSVALUE, value);
    }

    /**
     * Gets the attribute value for WMS_MUTUAL_IB_TYPE using the alias name WmsMutualIbType.
     * @return the WMS_MUTUAL_IB_TYPE
     */
    public String getWmsMutualIbType() {
        return (String) getAttributeInternal(WMSMUTUALIBTYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_MUTUAL_IB_TYPE using the alias name WmsMutualIbType.
     * @param value value to set the WMS_MUTUAL_IB_TYPE
     */
    public void setWmsMutualIbType(String value) {
        setAttributeInternal(WMSMUTUALIBTYPE, value);
    }

    /**
     * Gets the attribute value for WMS_SEDOL using the alias name WmsSedol.
     * @return the WMS_SEDOL
     */
    public String getWmsSedol() {
        return (String) getAttributeInternal(WMSSEDOL);
    }

    /**
     * Sets <code>value</code> as attribute value for WMS_SEDOL using the alias name WmsSedol.
     * @param value value to set the WMS_SEDOL
     */
    public void setWmsSedol(String value) {
        setAttributeInternal(WMSSEDOL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsAssetTrans.
     * @return the WmsAssetTrans
     */
    public String getWmsAssetTrans() {
        return (String) getAttributeInternal(WMSASSETTRANS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WmsAssetTrans.
     * @param value value to set the  WmsAssetTrans
     */
    public void setWmsAssetTrans(String value) {
        setAttributeInternal(WMSASSETTRANS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsCountryTrans.
     * @return the WmsCountryTrans
     */
    public String getWmsCountryTrans() {
        return (String) getAttributeInternal(WMSCOUNTRYTRANS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WmsCountryTrans.
     * @param value value to set the  WmsCountryTrans
     */
    public void setWmsCountryTrans(String value) {
        setAttributeInternal(WMSCOUNTRYTRANS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsCurrencyTrans.
     * @return the WmsCurrencyTrans
     */
    public String getWmsCurrencyTrans() {
        return (String) getAttributeInternal(WMSCURRENCYTRANS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WmsCurrencyTrans.
     * @param value value to set the  WmsCurrencyTrans
     */
    public void setWmsCurrencyTrans(String value) {
        setAttributeInternal(WMSCURRENCYTRANS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsSectorTrans.
     * @return the WmsSectorTrans
     */
    public String getWmsSectorTrans() {
        return (String) getAttributeInternal(WMSSECTORTRANS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WmsSectorTrans.
     * @param value value to set the  WmsSectorTrans
     */
    public void setWmsSectorTrans(String value) {
        setAttributeInternal(WMSSECTORTRANS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute WmsStockExchangeTrans.
     * @return the WmsStockExchangeTrans
     */
    public String getWmsStockExchangeTrans() {
        return (String) getAttributeInternal(WMSSTOCKEXCHANGETRANS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute WmsStockExchangeTrans.
     * @param value value to set the  WmsStockExchangeTrans
     */
    public void setWmsStockExchangeTrans(String value) {
        setAttributeInternal(WMSSTOCKEXCHANGETRANS, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link FWMSBondCoupenVO.
     */
    public RowIterator getFWMSBondCoupenVO() {
        return (RowIterator) getAttributeInternal(FWMSBONDCOUPENVO);
    }
}

