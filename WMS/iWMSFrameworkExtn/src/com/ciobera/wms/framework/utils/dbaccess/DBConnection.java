/*****************************************************************************************************
 ** Program Name            - DBConnection.java
 ** Program Description     - This class contains the logic to get DB connection.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.wms.framework.utils.dbaccess;

import com.ciobera.wms.framework.logger.LoggingUtil;
import com.ciobera.wms.framework.utils.common.ADFUtil;

import java.io.IOException;

import java.sql.Connection;

import javax.naming.InitialContext;

import javax.sql.DataSource;

import oracle.adf.share.logging.ADFLogger;

class DBConnection {
    public static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(DBConnection.class);
    private DataSource ds = null;
    private Connection dbconn = null;

    public DBConnection() throws IOException, Exception {
    }

    //Borrow connection object from the pool
    public Connection getDBConnection(String JNDI_DS) throws IOException,
                                                             Exception {
        LoggingUtil.logDebugMessages(LOGGER,
                                     "Here to fetch a Connection from pool! + " +
                                     JNDI_DS);
        if (ds == null) {
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup(JNDI_DS);
        }
        dbconn = ds.getConnection();
        if (dbconn == null) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "DBConnection: No Connection returned from Pool for : " +
                                         ADFUtil.getApplicationProperty("JNDI_DS"));
        }
        LoggingUtil.logDebugMessages(LOGGER,
                                     "The connection object from pool = " +
                                     dbconn);
        return dbconn;
    }

    public Connection getDBConnection(boolean usePool,
                                      String JNDI_DS) throws IOException,
                                                             Exception {
        return this.getDBConnection(JNDI_DS);
    }

    public void closeDBConnection() throws IOException, Exception {
        try {
            if (dbconn != null) {
                dbconn.close();
                dbconn = null;
            } else {
                LoggingUtil.logDebugMessages(LOGGER,
                                             "DBConnection object is NULL");
            }
        } catch (Exception e) {
            LoggingUtil.logDebugMessages(LOGGER,
                                         "Exception inside DBConnection.closeDBConnection()" +
                                         e.toString());
        }
    }
}
