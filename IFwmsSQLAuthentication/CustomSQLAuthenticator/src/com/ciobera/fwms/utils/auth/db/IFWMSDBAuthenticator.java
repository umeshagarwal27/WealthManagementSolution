package com.ciobera.fwms.utils.auth.db;

import java.security.Principal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Map;

import oracle.jdbc.driver.OracleDriver;


public class IFWMSDBAuthenticator {
    private String dbUserName;
    private String dbPassword;
    private String dbUrl;
    private String pwEncodingClass = "";
    private String pwKey = "";
//    private DBLoginModuleEncodingInterface pwEncoder;

    public IFWMSDBAuthenticator(String paramString1, String paramString2, String paramString3,
                                Map<String, ?> paramMap) {
        this.dbUrl = paramString1;
        this.dbUserName = paramString2;
        this.dbPassword = paramString3;
        this.pwEncodingClass = "oracle.sample.dbloginmodule.util.DBLoginModuleSHA1Encoder";
        this.pwKey = (paramMap.get("pwKey") != null ? (String) paramMap.get("pwKey") : null);
    }

    public IFWMSDBAuthenticatorResults authenticate(String paramString1, String paramString2,
                                                    String paramString3) throws Exception {
        IFWMSDBAuthenticatorResults localIFWMSDBAuthenticatorResults =
            executeAuthPackage(this.dbUrl, this.dbUserName, this.dbPassword, paramString1, paramString2, paramString3);

        return localIFWMSDBAuthenticatorResults;
    }

    private IFWMSDBAuthenticatorResults executeAuthPackage(String paramString1, String paramString2,
                                                           String paramString3, String paramString4,
                                                           String paramString5, String paramString6) throws Exception {
        CallableStatement localCallableStatement = null;
        Connection localConnection = null;
        ResultSet localResultSet = null;
        ArrayList localArrayList = new ArrayList();
        IFWMSDBAuthenticatorResults localIFWMSDBAuthenticatorResults1 = new IFWMSDBAuthenticatorResults();
        localIFWMSDBAuthenticatorResults1.setAuthenticated(Boolean.FALSE);
        System.out.println("begin IFWMSDBAuthenticatorResults.executeAuthPackage method");
        try {
            System.out.println("Getting connection");

            DriverManager.registerDriver(new OracleDriver());
            localConnection = DriverManager.getConnection(paramString1, paramString2, paramString3);
            String authenticateQuery =
                "SELECT WMS_USER_ID FROM FWMS_USERS WHERE UPPER(WMS_USER_ID) = UPPER(?) AND UPPER(WMS_USER_PASSWORD) = UPPER(?)";
            PreparedStatement preparedStmt = null;
            ResultSet resultSet = null;

            if (localConnection != null) {
                preparedStmt = localConnection.prepareStatement(authenticateQuery);
                preparedStmt.setString(1, paramString4);
                preparedStmt.setString(2, paramString5);
                resultSet = preparedStmt.executeQuery();
                if (resultSet != null) {
                    String userName = null;
                    while (localResultSet.next()) {
                        userName = localResultSet.getString(1);
                        System.out.println("User Name" + userName);

                    }
                    if (userName.equalsIgnoreCase(paramString4)) {
                        localIFWMSDBAuthenticatorResults1.setAuthenticated(true);
                        localIFWMSDBAuthenticatorResults1.setUserName(paramString4);
//                        localIFWMSDBAuthenticatorResults1.setAuthPrincipals(localArrayList);
                    }
                }
            }
            System.out.println("Successful Connection");

            //            Pattern localPattern =
            //                Pattern.compile("(?i)\\bselect\\b|\\bcreate\\b|\\binsert\\b|\\bdelete\\b|\\bdrop\\b|\\bupdate\\b|\\bor\\b|%.\\d");
            //
            //            Object localObject1 = localPattern.matcher(paramString4);
            //
            //            if (((Matcher) localObject1).find()) {
            //                System.out.println("SQL Injection attempt detected: username was altered to include SQL keyword:" +
            //                                   ((Matcher) localObject1).group());
            //
            //                localIFWMSDBAuthenticatorResults1.setAuthenticated(false);
            //                return localIFWMSDBAuthenticatorResults1;
            //            }

            System.out.println("Encoding password");
            //            try {
            //                this.pwEncoder = ((DBLoginModuleEncodingInterface) Class.forName(this.pwEncodingClass).newInstance());
            //            } catch (Exception localException) {
            //                System.out.println("Exception in encoding:" + localException);
            //                localIFWMSDBAuthenticatorResults1.setAuthenticated(false);
            //                return localIFWMSDBAuthenticatorResults1;
            //            }
            //
            //            String str1 = this.pwEncoder.getKeyDigestString(paramString5, this.pwKey);
            //
            //            System.out.println("encPassword" + str1);

            //            Object localObject2 = "{call ? := SEC_JAAS_UTILS.get_user_authentication(?,?,?)}";
            //
            //            localCallableStatement = localConnection.prepareCall((String) localObject2);
            //
            //            localCallableStatement.registerOutParameter(1, -10);
            //            localCallableStatement.setString(2, paramString4);
            //            localCallableStatement.setString(3, str1);
            //
            //            localCallableStatement.setString(4, paramString6);
            //            localCallableStatement.execute();
            //            localResultSet = (ResultSet) localCallableStatement.getObject(1);
            //
            //            while (localResultSet.next()) {
            //                String str2 = localResultSet.getString(1);
            //                System.out.println("Role Name" + str2);
            //                localArrayList.add(str2);
            //            }


        } catch (SQLException localSQLException2) {
            Object localObject1;
            System.out.println("User " + paramString4 + " not authenticated: username or password mismatch");

            if (localSQLException2.getMessage().indexOf("ORA-01403") < 0) {
                if (localSQLException2.getMessage().indexOf("ORA-06510") < 0) {
                    System.out.println("Error: " + localSQLException2.getMessage());
                }

                localIFWMSDBAuthenticatorResults1.setAuthenticated(false);
                return localIFWMSDBAuthenticatorResults1;
            }
        } finally {
            try {
                if (localCallableStatement != null) {
                    localCallableStatement.close();
                }
                if (localConnection != null)
                    localConnection.close();
            } catch (SQLException localSQLException7) {
                localSQLException7.printStackTrace();
            }
        }
        return localIFWMSDBAuthenticatorResults1;
    }

    public class IFWMSDBAuthenticatorResults {
        private boolean authenticated;
        private String userId;
        private String userName;
        private ArrayList authPrincipals;

        public IFWMSDBAuthenticatorResults() {
        }

        public IFWMSDBAuthenticatorResults(boolean param1, String paramArrayList, ArrayList<Principal> arg4) {
            this.authenticated = param1;
            Object localObject1 = paramArrayList;
            this.userName = (String) paramArrayList;
            this.userId = (String) paramArrayList;
            this.authPrincipals = arg4;
        }

        public void setAuthenticated(boolean paramBoolean) {
            this.authenticated = paramBoolean;
        }

        public boolean isAuthenticated() {
            return this.authenticated;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String paramString) {
            this.userName = paramString;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String paramString) {
            this.userId = paramString;
        }

        public ArrayList getAuthPrincipals() {
            return this.authPrincipals;
        }

        public void setAuthPrincipals(ArrayList paramArrayList) {
            this.authPrincipals = paramArrayList;
        }
    }
}
