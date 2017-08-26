package com.ciobera.fwms.common.model.am;

import com.ciobera.fwms.common.model.am.common.CommonAM;
import com.ciobera.fwms.common.model.vo.FWMSUsersUpdateVOImpl;
import com.ciobera.fwms.common.model.vo.readonly.FWMSMainMenuVOImpl;
import com.ciobera.fwms.common.model.vo.readonly.FWMSSeqLoginVOImpl;
import com.ciobera.fwms.common.model.vo.readonly.FWMSUsersPwdVOImpl;
import com.ciobera.fwms.common.model.vo.readonly.FWMSUsersVOImpl;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransactionImpl;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 10 10:23:12 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CommonAMImpl extends ApplicationModuleImpl implements CommonAM {
    /**
     * This is the default constructor (do not remove).
     */
    public CommonAMImpl() {
    }
    
    /**
     * Helper method to convert oracle.jbo.domain.Date to String
     * @param domainDate
     * @return
     */
    private String convertJbodateToString(oracle.jbo.domain.Date domainDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S"); 
        Date date = null;
        try {
            date = (Date) formatter.parse(domainDate.toString().substring(0, 21));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return FORMATTER.format(date);
    }

    /**
     * This method is called from the bean LoginBean.doLogin.
     * This method checks if the username and password exists in the system.
     * @param userId
     * @param password
     * @return
     */
    public Map findUserByLoginCredentials(String userId, String password) {
        Map resultMap = new HashMap();
        FWMSUsersVOImpl usersVO = this.getFWMSUsers();
        if (usersVO != null) {
            ViewCriteria findByUserIdVC = usersVO.getViewCriteria("findByUserId");
            if (findByUserIdVC != null) {
                usersVO.applyViewCriteria(null);
                usersVO.setNamedWhereClauseParam("pUserId", null);
                usersVO.setNamedWhereClauseParam("pUserId", userId);
                usersVO.applyViewCriteria(findByUserIdVC);
                usersVO.executeQuery();
                Row currentRow = usersVO.first();
                if (currentRow != null) {
                    if (password.equals(currentRow.getAttribute("WmsUserPassword"))) {
                        resultMap.put("RESP_CODE", "SUCCESS");
                        resultMap.put("EXPIRY_DAYS",
                                      Integer.toString((Integer) currentRow.getAttribute("WmsExpiryDays")));
                        resultMap.put("USER_BLOCKED", (String) currentRow.getAttribute("WmsBlock"));
                        resultMap.put("USER_NAME", currentRow.getAttribute("WmsName"));
                        resultMap.put("USER_ID", currentRow.getAttribute("WmsUserId"));
                        resultMap.put("STATUS", currentRow.getAttribute("WmsStatus"));
                        resultMap.put("LAST_LOGIN", convertJbodateToString((oracle.jbo.domain.Date)currentRow.getAttribute("WmsLastLogin")));
                        resultMap.put("LAST_PASSWORD_CHANGE", convertJbodateToString((oracle.jbo.domain.Date)currentRow.getAttribute("WmsLastPasswordChange")));
                        resultMap.put("EMAIL", currentRow.getAttribute("WmsEmail"));
                    } else {
                        resultMap.put("RESP_CODE", "INVALID");
                    }
                } else {
                    resultMap.put("RESP_CODE", "INVALID_USER");
                }
            } else {
                resultMap.put("RESP_CODE", "FAILURE");
                resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
            }
        } else {
            resultMap.put("RESP_CODE", "FAILURE");
            resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
        }
        return resultMap;
    }

    /**
     * This method is called from the bean LoginBean.onChangePassword.
     * This method updates login credentials in the DB.
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    public Map updateLoginCredentials(String userId, String password, String newPassword) {
        Map resultMap = new HashMap();
        FWMSUsersUpdateVOImpl usersVO = this.getFWMSUsersUpdate();
        if (usersVO != null) {
            ViewCriteria findByLoginCredentialsVC = usersVO.getViewCriteria("findByLoginCredentials");
            if (findByLoginCredentialsVC != null) {
                usersVO.applyViewCriteria(null);
                usersVO.setNamedWhereClauseParam("pUserId", null);
                usersVO.setNamedWhereClauseParam("pUserPassword", null);
                usersVO.setNamedWhereClauseParam("pUserId", userId);
                usersVO.setNamedWhereClauseParam("pUserPassword", password);
                usersVO.applyViewCriteria(findByLoginCredentialsVC);
                usersVO.executeQuery();
                Row currentRow = usersVO.first();
                if (currentRow != null) {
                    currentRow.setAttribute("WmsUserPassword", newPassword);
                    this.getDBTransaction().commit();
                    resultMap.put("RESP_CODE", "SUCCESS");
                } else {
                    resultMap.put("RESP_CODE", "FAILURE");
                    resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
                }
            } else {
                resultMap.put("RESP_CODE", "FAILURE");
                resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
            }
        } else {
            resultMap.put("RESP_CODE", "FAILURE");
            resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
        }
        return resultMap;
    }

    /**
     * This method is called from the bean LoginBean.onChangePassword.
     * This method blocks User
     * @param userId
     * @return
     */
    public Map blockUser(String userId) {
        Map resultMap = new HashMap();
        FWMSUsersUpdateVOImpl usersVO = this.getFWMSUsersUpdate();
        if (usersVO != null) {
            ViewCriteria findByUserIdVC = usersVO.getViewCriteria("findByUserId");
            if (findByUserIdVC != null) {
                usersVO.applyViewCriteria(null);
                usersVO.setNamedWhereClauseParam("pUserId", null);
                usersVO.setNamedWhereClauseParam("pUserId", userId);
                usersVO.applyViewCriteria(findByUserIdVC);
                usersVO.executeQuery();
                Row currentRow = usersVO.first();
                if (currentRow != null) {
                    currentRow.setAttribute("WmsBlock", "Y");
                    this.getDBTransaction().commit();
                    resultMap.put("RESP_CODE", "SUCCESS");
                } else {
                    resultMap.put("RESP_CODE", "FAILURE");
                    resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
                }
            } else {
                resultMap.put("RESP_CODE", "FAILURE");
                resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
            }
        } else {
            resultMap.put("RESP_CODE", "FAILURE");
            resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
        }
        return resultMap;
    }

    /**
     * This method is called from the bean LoginBean.onChangePassword.
     * This method returns the number of records in UsersPwdVO for a userId.
     * @param userId
     * @return
     */
    public Map getUserWrongPasswordCount(String userId) {
        Map resultMap = new HashMap();
        FWMSUsersPwdVOImpl userPwdVO = this.getFWMSUsersPwd();
        if (userPwdVO != null) {
            ViewCriteria findUserPwdCountVC = userPwdVO.getViewCriteria("findUserPwdCount");
            if (findUserPwdCountVC != null) {
                userPwdVO.applyViewCriteria(null);
                userPwdVO.setNamedWhereClauseParam("pUserId", null);
                userPwdVO.setNamedWhereClauseParam("pUserId", userId);
                userPwdVO.applyViewCriteria(findUserPwdCountVC);
                userPwdVO.executeQuery();
                resultMap.put("COUNT", (Long) userPwdVO.getEstimatedRowCount());
            }
        }
        return resultMap;
    }

    /**
     * This method is called from the bean method LoginBean.doLogin.
     * This procedure will insert new record for every wrong password attempt in FWMS_USERS_WPWD table and if count>5 it will block the user.
     * @param userId
     * @param pc
     * @return
     */
    public Map logWrongAttempt(String userId, String pc) {
        Map resultMap = new HashMap();
        DBTransactionImpl dbTransaction = (DBTransactionImpl) getDBTransaction();
        CallableStatement custCtxStmt = null;
        String procedure = "begin " + " FWMS_WPWD_PROC_01('" + userId + "', '" + pc + "'); " + "end;";
        if (userId != null && pc != null) {
            custCtxStmt = dbTransaction.createCallableStatement(procedure, 0);
            try {
                custCtxStmt.execute();
                resultMap.put("RESP_CODE", "SUCCESS");
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
        return resultMap;
    }

    /**
     * This method is called from the bean method LoginBean.doLogin.
     * This procedure will clear wrong attempts data where user logged correctly into the system.
     * @param userId
     * @param pc
     * @return
     */
    public Map clearWrongAttempt(String userId, String pc) {
        Map resultMap = new HashMap();
        DBTransactionImpl dbTransaction = (DBTransactionImpl) getDBTransaction();
        CallableStatement custCtxStmt = null;
        String procedure = "begin " + " FWMS_WPWD_PROC_02('" + userId + "', '" + pc + "'); " + "end;";
        if (userId != null && pc != null) {
            custCtxStmt = dbTransaction.createCallableStatement(procedure, 0);
            try {
                custCtxStmt.execute();
                resultMap.put("RESP_CODE", "SUCCESS");
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
        return resultMap;
    }

    /**
     * This method is called from the bean method LoginBean.doLogin.
     * This procedure will log user activity in Log Table like logging into the system etc.
     * @param userId
     * @param pc
     * @return
     */
    public Map logUserActivity(String userId, String pc, String screen, String type, String logMessage) {
        Map resultMap = new HashMap();
        DBTransactionImpl dbTransaction = (DBTransactionImpl) getDBTransaction();
        CallableStatement custCtxStmt = null;
        String procedure =
            "begin " + " FWMS_ULOG_PROC_01('" + userId + "', '" + pc + "', '" + screen + "', '" + type + "', '" +
            logMessage + "'); " + "end;";
        if (userId != null && pc != null) {
            custCtxStmt = dbTransaction.createCallableStatement(procedure, 0);
            try {
                custCtxStmt.execute();
                resultMap.put("RESP_CODE", "SUCCESS");
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
        return resultMap;
    }
    
    /**
     * This method is called from the bean HomeBean.getUserDetails.
     * This method fetches logged in user main menu details from FWMSMainMenuVO
     * @param userId
     * @return
     */
    public Map findMainMenuByUserId(String userId){
        Map resultMap = new HashMap();
        FWMSMainMenuVOImpl mainMenuVO = this.getFWMSMainMenu();
        if (mainMenuVO != null) {
            ViewCriteria findByUserIdVC = mainMenuVO.getViewCriteria("findByUserId");
            ViewCriteria findBySNoVC = mainMenuVO.getViewCriteria("findBySNo");
            if (findByUserIdVC != null) {
                mainMenuVO.applyViewCriteria(null);
                mainMenuVO.setNamedWhereClauseParam("pUserId", null);
                mainMenuVO.setNamedWhereClauseParam("pSNo", null);
                mainMenuVO.setNamedWhereClauseParam("pUserId", userId);
                mainMenuVO.setNamedWhereClauseParam("pSNo", 1);
                mainMenuVO.applyViewCriteria(findBySNoVC,true);
                mainMenuVO.applyViewCriteria(findByUserIdVC,true);
                mainMenuVO.executeQuery();
                Row rows[] = mainMenuVO.getAllRowsInRange();
                if(rows != null && rows.length > 0){
                    resultMap.put("RESP_CODE", "SUCCESS");
                } else {
                    resultMap.put("RESP_CODE", "INVALID");
                    resultMap.put("RESP_MESSAGE", "There are no records set for the logged in user in DB. Please contact system administrator.");
                }
            } else {
                resultMap.put("RESP_CODE", "FAILURE");
                resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
            }
        } else {
            resultMap.put("RESP_CODE", "FAILURE");
            resultMap.put("RESP_MESSAGE", "An error has occurred. Please contact your system administrator.");
        }
        return resultMap;
    }
    
    /**
     * Container's getter for FWMSUsers.
     * @return FWMSUsers
     */
    public FWMSUsersVOImpl getFWMSUsers() {
        return (FWMSUsersVOImpl) findViewObject("FWMSUsers");
    }

    /**
     * Container's getter for FWMSUsersPwd.
     * @return FWMSUsersPwd
     */
    public FWMSUsersPwdVOImpl getFWMSUsersPwd() {
        return (FWMSUsersPwdVOImpl) findViewObject("FWMSUsersPwd");
    }

    /**
     * Container's getter for FWMSSeqLogin.
     * @return FWMSSeqLogin
     */
    public FWMSSeqLoginVOImpl getFWMSSeqLogin() {
        return (FWMSSeqLoginVOImpl) findViewObject("FWMSSeqLogin");
    }

    /**
     * Container's getter for FWMSUsersUpdateVO1.
     * @return FWMSUsersUpdateVO1
     */
    public FWMSUsersUpdateVOImpl getFWMSUsersUpdate() {
        return (FWMSUsersUpdateVOImpl) findViewObject("FWMSUsersUpdate");
    }

    /**
     * Container's getter for FWMSMainMenuVO1.
     * @return FWMSMainMenuVO1
     */
    public FWMSMainMenuVOImpl getFWMSMainMenu() {
        return (FWMSMainMenuVOImpl) findViewObject("FWMSMainMenu");
    }

    /**
     * Container's getter for FWMSMainMenuVO1.
     * @return FWMSMainMenuVO1
     */
    public FWMSMainMenuVOImpl getFWMSMainMenuChildNodes() {
        return (FWMSMainMenuVOImpl) findViewObject("FWMSMainMenuChildNodes");
    }

    /**
     * Container's getter for FWMSMainMenuFKViewLink1.
     * @return FWMSMainMenuFKViewLink1
     */
    public ViewLinkImpl getFWMSMainMenuFKViewLink() {
        return (ViewLinkImpl) findViewLink("FWMSMainMenuFKViewLink");
    }
}

