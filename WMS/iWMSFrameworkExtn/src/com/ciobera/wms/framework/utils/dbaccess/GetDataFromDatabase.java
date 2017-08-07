/*****************************************************************************************************
 ** Program Name            - GetDataFromDatabase.java
 ** Program Description     - This class contains the logic get data from database
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.wms.framework.utils.dbaccess;

import com.ciobera.wms.framework.logger.LoggingUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.adf.share.logging.ADFLogger;

class GetDataFromDatabase {

    public static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(DBConnection.class);
    private static int count = 0;
    private Connection conn;
    private DBConnection dbConn;

    GetDataFromDatabase(boolean usePool, String JNDI_DS) {
        try {
            dbConn = new DBConnection();
            conn =
                dbConn.getDBConnection(usePool,
                                       JNDI_DS); //Returns connection object depending on requirement
            if (usePool) {
                incrementCount();
            }
            LoggingUtil.logDebugMessages(LOGGER,
                                         "The connection object = " + conn);
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "Exception in GetDataFromDatabase(boolean usePool): Connection Failed :" +
                                         e.toString());
            e.printStackTrace();
        }
    }

    Connection getDBConnection() {
        if (conn == null) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "######## RETURNING CLOSED CONNECTION OBJECT #########");
        }
        return conn;
    }

    public String[][] getValues(String query) {
        long start_time = System.currentTimeMillis();
        String[][] value;
        Statement stmt1 = null;
        ResultSet rs1 = null;

        try {
            stmt1 =
                conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
            rs1 = stmt1.executeQuery(query);

            ResultSetMetaData rsmd1 = rs1.getMetaData();
            int colcount = rsmd1.getColumnCount();
            int rowcount = 0;

            while (rs1.next()) {
                rowcount++;
            }
            if (rowcount == 0) {
                return null;
            }
            rs1.beforeFirst();
            value = new String[rowcount][colcount];
            value = datareturn(rs1, rowcount, colcount);

            return value;
        } catch (SQLException e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "1 Exception inside getValues(String query) : " +
                                         e.toString());
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "2 Exception inside getValues(String query) : " +
                                         e.toString());
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                rs1 = null;
            } catch (Exception rE) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "Exception inside getValues(String query) during resultset close() : " +
                                             rE.toString());
            }
            try {
                stmt1.close();
                stmt1 = null;
            } catch (Exception sE) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "Exception inside getValues(String query) during statement close() : " +
                                             sE.toString());
            }
            long end_time = System.currentTimeMillis();
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Query:" + query + "||" +
                                         (end_time - start_time) + "ms");
        }
        return null;
    }

    public String[] getFields(String query) {
        long start_time = System.currentTimeMillis();
        String[] fields = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;

        try {
            stmt1 =
                conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
            rs1 = stmt1.executeQuery(query);

            ResultSetMetaData rsmd1 = rs1.getMetaData();
            int colcount = rsmd1.getColumnCount();
            fields = new String[colcount];

            for (int count = 0; count < colcount; count++) {
                fields[count] = rsmd1.getColumnLabel(count + 1);
            }
        } catch (SQLException e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "1 Exception inside getFields(String query) : " +
                                         e.toString());
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "2 Exception inside getFields(String query) : " +
                                         e.toString());
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                rs1 = null;
            } catch (Exception rE) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "Exception inside getFields(String query) during resultset close() : " +
                                             rE.toString());
            }
            try {
                stmt1.close();
                stmt1 = null;
            } catch (Exception sE) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "Exception inside getFields(String query) during statement close() : " +
                                             sE.toString());
            }
            long end_time = System.currentTimeMillis();
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Query:" + query + "||" +
                                         (end_time - start_time) + "ms");
        }
        return fields;
    }

    private String[][] datareturn(ResultSet rs, int row, int colcount) {
        String[][] data = null;
        try {
            data = new String[row][colcount];
            int i1;
            int j1 = 0;
            while (rs.next()) {
                for (i1 = 0; i1 < colcount; i1++) {
                    data[j1][i1] = rs.getString(i1 + 1);
                }
                j1++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public int ExecQuery(String query) {
        long start_time = System.currentTimeMillis();
        int result = 0;
        int rs1 = 0;
        Statement stmt1 = null;

        try {
            stmt1 =
                conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
            rs1 = stmt1.executeUpdate(query);
            result = 0;
        } catch (SQLException e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "1 Exception inside ExecQuery(String query) : " +
                                         e.toString());
            result = -1;
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "2 Exception inside ExecQuery(String query) : " +
                                         e.toString());
            result = -1;
            e.printStackTrace();
        } finally {
            try {
                stmt1.close();
                stmt1 = null;
            } catch (Exception sE) {
                LoggingUtil.logErrorMessages(LOGGER,
                                             "Exception inside ExecQuery(String query) during statement close() : " +
                                             sE.toString());
            }

            long end_time = System.currentTimeMillis();

            LoggingUtil.logDebugMessages(LOGGER,
                                         "Query:" + query + "||" +
                                         (end_time - start_time) + "ms");
        }

        return result;
    }

    public void closeDBConnection() {
        try {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "About to close connection object : " +
                                         dbConn);
            dbConn.closeDBConnection();
            decrementCount();
        } catch (Exception e) {
            LoggingUtil.logErrorMessages(LOGGER,
                                         "Exception inside closeDBConnection() " +
                                         e.toString());
        }
    }

    private static final synchronized void incrementCount() {
        count++;
    }

    private static final synchronized void decrementCount() {
        count--;
        LoggingUtil.logDebugMessages(LOGGER,
                                     "Open Connection Objects : " + count);
    }
}
