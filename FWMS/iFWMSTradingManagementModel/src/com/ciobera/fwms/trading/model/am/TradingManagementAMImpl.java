package com.ciobera.fwms.trading.model.am;


import com.ciobera.fwms.trading.model.am.common.TradingManagementAM;
import com.ciobera.fwms.trading.model.vo.readonly.FWMSDHoldingAllVOImpl;
import com.ciobera.fwms.trading.model.vo.readonly.FWMSDHoldingSelectedVOImpl;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransactionImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 08 21:39:21 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TradingManagementAMImpl extends ApplicationModuleImpl implements TradingManagementAM {
    /**
     * This is the default constructor (do not remove).
     */
    public TradingManagementAMImpl() {
    }

    private oracle.jbo.domain.Date getCurrentDate() {
        return new oracle.jbo.domain.Date(oracle.jbo
                                                .domain
                                                .Date
                                                .getCurrentDate());
    }

    /**
     * This method is called to populate the Trading Management data for the input date using the stored procedure .
     * @param userId
     * @param asOfDate
     */
    public void processAsOfDateRecord(String userId, Date asOfDate) {
        //TODO call Stored Procedure to fill the data in the table forthe selected user and asof date
        DBTransactionImpl dbTransaction = (DBTransactionImpl) getDBTransaction();
        CallableStatement custCtxStmt = null;
        String procedure = "begin  FWMSD_HOLDING_G(?,?); end;";
        if (userId != null && asOfDate != null) {
            custCtxStmt = dbTransaction.createCallableStatement(procedure, 0);
            try {
                custCtxStmt.setString(1, userId);
                custCtxStmt.setDate(2, asOfDate.dateValue());
                custCtxStmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (custCtxStmt != null) {
                    try {
                        custCtxStmt.close();
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                    }
                }
            }
        }

    }

    public void getFWMSHoldingRecordsForUserId(String userId) {
        if (userId != null) {
            ViewObjectImpl holdingAllVOImpl = getFWMSDHoldingAll();
            if (holdingAllVOImpl != null) {
                holdingAllVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
                holdingAllVOImpl.executeQuery();
            }

            ViewObjectImpl holdingAllAssetVOImpl = getFWMSDHoldingAllAsset();
            if (holdingAllAssetVOImpl != null) {
                holdingAllAssetVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
                holdingAllAssetVOImpl.executeQuery();
            }

            ViewObjectImpl holdingAllCountryVOImpl = getFWMSDHoldingAllCountry();
            if (holdingAllCountryVOImpl != null) {
                holdingAllCountryVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
                holdingAllCountryVOImpl.executeQuery();
            }

            ViewObjectImpl holdingAllExchangeVOImpl = getFWMSDHoldingAllExchange();
            if (holdingAllExchangeVOImpl != null) {
                holdingAllExchangeVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
                holdingAllExchangeVOImpl.executeQuery();
            }

            //            ViewObjectImpl holdingAllExchangeDVOImpl = getFWMSDHoldingAllExchangeD();
            //            if (holdingAllExchangeDVOImpl != null) {
            //                holdingAllExchangeDVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
            //                holdingAllExchangeDVOImpl.executeQuery();
            //            }
            //
            //            ViewObjectImpl holdingAllCountryDVOImpl = getFWMSDHoldingAllCountryD();
            //            if (holdingAllCountryDVOImpl != null) {
            //                holdingAllCountryDVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
            //                holdingAllCountryDVOImpl.executeQuery();
            //            }
            //
            //            ViewObjectImpl holdingAllAssetDVOImpl = getFWMSDHoldingAllAssetD();
            //            if (holdingAllAssetDVOImpl != null) {
            //                holdingAllAssetDVOImpl.setNamedWhereClauseParam("pLoggedInUserId", userId);
            //                holdingAllAssetDVOImpl.executeQuery();
            //            }
        }
    }

    /**
     * This method is called to update logged in user and date for various scenarios.
     * In Create Mode, WMSENTERUID, WMSENTERDATE is updated.
     * In Edit Mode, WMSLASTUPDATEID, WMSLASTUPDATEDATE is updated.
     * In case of Approve, WMSAPPROVEUID, WMSAPPROVEDATE is updated.
     * @param mode
     * @param updatedBy
     */
    public Map updateOrderRecord(String mode, String updatedBy) {
        Map resultMap = new HashMap();
        ViewObjectImpl orderVO = getFWMSOrderBook();
        if (orderVO != null) {
            Row orderRow = orderVO.getCurrentRow();
            if (orderRow != null) {
                if ("EDIT".equalsIgnoreCase(mode)) {
                    orderRow.setAttribute("WmsLastUpdateUid", updatedBy);
                    orderRow.setAttribute("WmsLastUpdateDate", getCurrentDate());
                }
                if ("CREATE".equalsIgnoreCase(mode)) {
                    ViewObjectImpl seqImpl = getOrderBookNumberSeq();
                    if (seqImpl != null) {
                        seqImpl.executeQuery();
                        Row orderSeqRow = seqImpl.first();
                        if (orderSeqRow != null) {
                            orderRow.setAttribute("FobNo", orderSeqRow.getAttribute("MaxFobNo"));
                            orderRow.setAttribute("WmsEnterUid", updatedBy);
                            orderRow.setAttribute("WmsEnterDate", getCurrentDate());
                            orderRow.setAttribute("WmsLastUpdateUid", updatedBy);
                            orderRow.setAttribute("WmsLastUpdateDate", getCurrentDate());
                        }
                    }
                }
                if ("APPROVE".equalsIgnoreCase(mode)) {
                    orderRow.setAttribute("WmsApproveUid", updatedBy);
                    orderRow.setAttribute("WmsApproveDate", getCurrentDate());
                    orderRow.setAttribute("FobOstatus", "APPROVED");
                }
                this.getDBTransaction().commit();
                resultMap.put("RESP_CODE", "SUCCESS");
            }
        }
        return resultMap;
    }

    /**
     * Container's getter for FWMSDHoldingAllVO.
     * @return FWMSDHoldingAllVO
     */
    public FWMSDHoldingAllVOImpl getFWMSDHoldingAll() {
        return (FWMSDHoldingAllVOImpl) findViewObject("FWMSDHoldingAll");
    }

    /**
     * Container's getter for FWMSDHoldingAllAssetVO.
     * @return FWMSDHoldingAllAssetVO
     */
    public ViewObjectImpl getFWMSDHoldingAllAsset() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllAsset");
    }

    /**
     * Container's getter for FWMSDHoldingAllAssetDVO.
     * @return FWMSDHoldingAllAssetDVO
     */
    public ViewObjectImpl getFWMSDHoldingAllAssetD() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllAssetD");
    }

    /**
     * Container's getter for FWMSDHoldingAllAssetVOToFWMSDHoldingAllAssetDVO.
     * @return FWMSDHoldingAllAssetVOToFWMSDHoldingAllAssetDVO
     */
    public ViewLinkImpl getFWMSDHoldingAllAssetVOToFWMSDHoldingAllAssetDVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllAssetVOToFWMSDHoldingAllAssetDVO");
    }

    /**
     * Container's getter for FWMSDHoldingAllCountryVO.
     * @return FWMSDHoldingAllCountryVO
     */
    public ViewObjectImpl getFWMSDHoldingAllCountry() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllCountry");
    }

    /**
     * Container's getter for FWMSDHoldingAllCountryDVO.
     * @return FWMSDHoldingAllCountryDVO
     */
    public ViewObjectImpl getFWMSDHoldingAllCountryD() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllCountryD");
    }

    /**
     * Container's getter for FWMSHoldingAllCountryVOToFWMSHoldingAllCountryDVO.
     * @return FWMSHoldingAllCountryVOToFWMSHoldingAllCountryDVO
     */
    public ViewLinkImpl getFWMSHoldingAllCountryVOToFWMSHoldingAllCountryDVO() {
        return (ViewLinkImpl) findViewLink("FWMSHoldingAllCountryVOToFWMSHoldingAllCountryDVO");
    }

    /**
     * Container's getter for FWMSDHoldingAllExchangeVO.
     * @return FWMSDHoldingAllExchangeVO
     */
    public ViewObjectImpl getFWMSDHoldingAllExchange() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllExchange");
    }

    /**
     * Container's getter for FWMSDHoldingAllExchangeDVO.
     * @return FWMSDHoldingAllExchangeDVO
     */
    public ViewObjectImpl getFWMSDHoldingAllExchangeD() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllExchangeD");
    }

    /**
     * Container's getter for FWMSDHoldingAllExchangeVOToFWMSDHoldingAllExchangeDVO.
     * @return FWMSDHoldingAllExchangeVOToFWMSDHoldingAllExchangeDVO
     */
    public ViewLinkImpl getFWMSDHoldingAllExchangeVOToFWMSDHoldingAllExchangeDVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllExchangeVOToFWMSDHoldingAllExchangeDVO");
    }

    /**
     * Container's getter for FWMSDHoldingSelectedVO.
     * @return FWMSDHoldingSelectedVO
     */
    public FWMSDHoldingSelectedVOImpl getFWMSDHoldingSelected() {
        return (FWMSDHoldingSelectedVOImpl) findViewObject("FWMSDHoldingSelected");
    }

    /**
     * Container's getter for FWMSDHoldingAllVOToFWMSDHoldingSelectedSVO.
     * @return FWMSDHoldingAllVOToFWMSDHoldingSelectedSVO
     */
    public ViewLinkImpl getFWMSDHoldingAllVOToFWMSDHoldingSelectedVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllVOToFWMSDHoldingSelectedVO");
    }

    /**
     * Container's getter for FWMSDHoldingAllAssetSVO1.
     * @return FWMSDHoldingAllAssetSVO1
     */
    public ViewObjectImpl getFWMSDHoldingAllAssetS() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllAssetS");
    }

    /**
     * Container's getter for FWMSDHoldingAllVOToFWMSDHoldingAllAssetSVO1.
     * @return FWMSDHoldingAllVOToFWMSDHoldingAllAssetSVO1
     */
    public ViewLinkImpl getFWMSDHoldingAllVOToFWMSDHoldingAllAssetSVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllVOToFWMSDHoldingAllAssetSVO");
    }

    /**
     * Container's getter for FWMSDHoldingAllCountrySVO1.
     * @return FWMSDHoldingAllCountrySVO1
     */
    public ViewObjectImpl getFWMSDHoldingAllCountryS() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllCountryS");
    }

    /**
     * Container's getter for FWMSDHoldingAllVOToFWMSDHoldingAllCountrySVO1.
     * @return FWMSDHoldingAllVOToFWMSDHoldingAllCountrySVO1
     */
    public ViewLinkImpl getFWMSDHoldingAllVOToFWMSDHoldingAllCountrySVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllVOToFWMSDHoldingAllCountrySVO");
    }

    /**
     * Container's getter for FWMSDHoldingAllExchangeSVO1.
     * @return FWMSDHoldingAllExchangeSVO1
     */
    public ViewObjectImpl getFWMSDHoldingAllExchangeS() {
        return (ViewObjectImpl) findViewObject("FWMSDHoldingAllExchangeS");
    }

    /**
     * Container's getter for FWMSDHoldingAllVOToFWMSDHoldingAllExchangeSVO1.
     * @return FWMSDHoldingAllVOToFWMSDHoldingAllExchangeSVO1
     */
    public ViewLinkImpl getFWMSDHoldingAllVOToFWMSDHoldingAllExchangeSVO() {
        return (ViewLinkImpl) findViewLink("FWMSDHoldingAllVOToFWMSDHoldingAllExchangeSVO");
    }

    /**
     * Container's getter for FWMSOrderBookVO1.
     * @return FWMSOrderBookVO1
     */
    public ViewObjectImpl getFWMSOrderBook() {
        return (ViewObjectImpl) findViewObject("FWMSOrderBook");
    }

    /**
     * Container's getter for OrderBookNumberSeq1.
     * @return OrderBookNumberSeq1
     */
    public ViewObjectImpl getOrderBookNumberSeq() {
        return (ViewObjectImpl) findViewObject("OrderBookNumberSeq");
    }
}

