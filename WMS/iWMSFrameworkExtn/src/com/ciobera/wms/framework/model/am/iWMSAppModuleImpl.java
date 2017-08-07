package com.ciobera.wms.framework.model.am;


import com.ciobera.wms.framework.utils.common.ADFUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.Session;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.DBTransactionImpl;
import oracle.jbo.server.ViewObjectImpl;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;


public class iWMSAppModuleImpl extends ApplicationModuleImpl {
    public static final ADFLogger logObject =
        ADFLogger.createADFLogger(iWMSAppModuleImpl.class);

    static final String databasePooling =
        ADFUtil.getApplicationProperty("APP_MODULE_DB_MODE");
    static final String appMode =
        ADFUtil.getApplicationProperty("APPLICATION_MODE");
    String dbContextParam = null;

    public iWMSAppModuleImpl() {
        super();
    }

    /**
     * Method to setup the  in database Context
     * @param param
     * @return
     */
    public boolean setDBContext(String param) {
        boolean success = false;
        this.dbContextParam = param;
        DBTransactionImpl dbTransaction = (DBTransactionImpl)getDBTransaction();
        //sample db call
        CallableStatement custCtxStmt;
        custCtxStmt =
            dbTransaction.createCallableStatement(("begin " + "   null; " +
                                                   "end;"), 0);
        try {
            custCtxStmt.execute();
            success = true;
        } catch (SQLException e) {
            success = false;
            logObject.info("Error setting database context :" + e.toString());
            throw new JboException(e);
        }
        return success;
    }

    @Override
    protected void prepareSession(Session session) {
        super.prepareSession(session);
        this.getDBTransaction().setClearCacheOnRollback(Boolean.FALSE);
    }

    /**
     * Call stored procedure in out.
     * @param dBTransaction the d b transaction
     * @param procName the proc name
     * @param mapIn the map in
     * @param mapOut the map out
     * @return the map
     * @throws Exception the exception
     */
    protected Map<Integer, Object> callStoredProcedureInOut(DBTransaction dBTransaction,
                                                            String procName,
                                                            Map<Integer, Object> mapIn,
                                                            Map<Integer, Object> mapOut) throws Exception {
        CallableStatement st = null;
        try {
            logObject.fine("callSPInOutTableParams begin");
            st =
                dBTransaction.createCallableStatement("begin " + procName +
                                                      "; end;", 0);
            for (Map.Entry<Integer, Object> e : mapIn.entrySet()) {
                st.setObject(e.getKey(), e.getValue());
            }
            for (Map.Entry<Integer, Object> e : mapOut.entrySet()) {
                if (e.getValue() instanceof Object[]) {
                    Object[] list = (Object[])e.getValue();
                    st.registerOutParameter(e.getKey(), (Integer)list[0],
                                            (String)list[1]);
                } else
                    st.registerOutParameter(e.getKey(), (Integer)e.getValue());
            }
            st.executeUpdate();
            logObject.fine("proc name {} Executed", procName);
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            for (Map.Entry<Integer, Object> e : mapOut.entrySet()) {
                if (e.getValue() instanceof Object[]) {
                    Object[] list = (Object[])e.getValue();
                    if (list[0].equals(Types.ARRAY) &&
                        st.getArray(e.getKey()) != null)
                        map.put(e.getKey(), st.getArray(e.getKey()).getArray());
                    else if (list[0].equals(Types.STRUCT) && list[2] != null)
                        map.put(e.getKey(),
                                st.getObject(e.getKey(),
                                             (Map<String, Class<?>>)list[2]));
                    else
                        map.put(e.getKey(), st.getObject(e.getKey()));
                } else
                    map.put(e.getKey(), st.getObject(e.getKey()));
            }
            logObject.fine("callSPInOutTableParams end");
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            closeStatement(st);
        }
    }

    /**
     * Close statement.
     * @param st the st
     * @throws Exception the exception
     */
    private void closeStatement(PreparedStatement st) throws Exception {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            try {
                st.close();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    /**For Execute direct SQL statments
     * @param sql
     */

    public void executeSQL(String sql) {
        //e.g ALTER SESSION SET NLS_LANGUAGE='" +langval+ "'
        this.getDBTransaction().executeCommand(sql);
    }

    public void callFunctionSample() {
        try {
            Connection conn =
                this.getDBTransaction().createStatement(1).getConnection();
            String call = "{ ? = call PKG.Function1(?, ?, ?, ?, ?) }";
            CallableStatement cstmt = conn.prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
            cstmt.setString(2, "Param1");
            cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
            cstmt.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.CHAR);
            cstmt.registerOutParameter(6, oracle.jdbc.OracleTypes.CHAR);
            cstmt.executeUpdate();
            //get values
            cstmt.getString(1);
        } catch (Exception e) {
            logObject.severe(e.toString());
        }
    }

    public void sampleArrayCall() {
        try {
            Connection conn =
                this.getDBTransaction().createStatement(1).getConnection();
            ArrayDescriptor oracleVarchar2Collection =
                ArrayDescriptor.createDescriptor("VARCHAR2_ARRAY_TYPE", conn);
            String[] str_array = new String[100];
            ARRAY ora_str_array =
                new ARRAY(oracleVarchar2Collection, conn, str_array);
            CallableStatement stmt =
                conn.prepareCall("{ call function1(?, ?)}");
            stmt.setObject(1, ora_str_array);
            stmt.registerOutParameter(2, OracleTypes.ARRAY,
                                      "VARCHAR2_ARRAY_TYPE");
            stmt.execute();
            ARRAY ora_errors = ((OracleCallableStatement)stmt).getARRAY(2);
            String[] val = (String[])ora_errors.getArray();
        } catch (Exception e) {
            return;
        }
    }

    public void sampleSqlQuery() {
        Statement stmt = null;
        String query =
            "select col1, col2, col3, " + "col4, col5 " + "from sample_tbl";
        try {
            stmt = this.getDBTransaction().createStatement(0);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String col1val = rs.getString("col1");
                int col2val = rs.getInt("col2");
                float col3val = rs.getFloat("col3");
                int col4val = rs.getInt("col4");
                int col5val = rs.getInt("col5");
            }
            stmt.close();
        } catch (SQLException e) {
            logObject.severe(e.getMessage());
        }
    }

    public void performRefCursorSampleFromFunction() {
        OracleCallableStatement oraCallStmt = null;
        OracleResultSet deptResultSet = null;
        try {
            Connection conn =
                this.getDBTransaction().createStatement(1).getConnection();
            oraCallStmt =
                (OracleCallableStatement)conn.prepareCall("{? = call ref_cursor_package.get_dept_ref_cursor(?)}");
            oraCallStmt.registerOutParameter(1,
                                             OracleTypes.CURSOR); // or OracleTypes.Ref
            oraCallStmt.setInt(2, 104);
            oraCallStmt.execute();
            deptResultSet =
                (OracleResultSet)oraCallStmt.getCursor(1); // or .getObject
            while (deptResultSet.next()) {
                System.out.println(" - " + deptResultSet.getString(2) + " (" +
                                   deptResultSet.getInt(1) + "), " +
                                   deptResultSet.getString(3));
            }
            System.out.println();
            oraCallStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Method to retreive row based on the key
     * @param keyValue
     * @param vo
     * @return
     */
    public Row findRowByKey(String keyValue, ViewObjectImpl vo) {
        Row r = null;
        Key keyVal = new Key(new Object[] { keyValue });
        r = vo.getRow(keyVal);
        vo.getFilteredRows("AttrName", keyValue);
        vo.findByKey(keyVal, 1);
        return r;

    }

    private Connection getCurrentConnection() {
        Statement st = null;
        try {
            st = getDBTransaction().createStatement(0);
            return st.getConnection();
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        } finally {
            if (st != null)
                try {
                    st.close();
                } catch (SQLException s2) {
                }
        }
    }
}

