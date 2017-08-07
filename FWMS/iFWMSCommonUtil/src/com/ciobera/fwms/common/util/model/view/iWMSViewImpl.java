package com.ciobera.fwms.common.util.model.view;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.SQLBuilder;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;

public class iWMSViewImpl extends ViewObjectImpl {
    private Key currentRowKey;
    private int currentRowIndexInRange;

    public iWMSViewImpl() {
        super();
    }

    public static final ADFLogger logger =
        ADFLogger.createADFLogger(iWMSViewImpl.class);

    public static int NUMBER = Types.NUMERIC;
    public static int DATE = Types.DATE;
    public static int VARCHAR2 = Types.VARCHAR;


    /**
     * Simplifies calling a stored function with bind variables
     *
     * You can use the NUMBER, DATE, and VARCHAR2 constants in this
     * class to indicate the function return type for these three common types,
     * otherwise use one of the JDBC types in the java.sql.Types class.
     *
     * NOTE: If you want to invoke a stored procedure without any bind variables
     * ====  then you can just use the basic getDBTransaction().executeCommand()
     *
     * @param sqlReturnType JDBC datatype constant of function return value
     * @param stmt stored function statement
     * @param bindVars Object array of parameters
     * @return function return value as an Object
     */
    protected Object callStoredFunction(int sqlReturnType, String stmt,
                                        Object[] bindVars) {
        CallableStatement st = null;

        try {
            st =
                getDBTransaction().createCallableStatement("begin ? := " +
                                                           stmt + "; end;", 0);
            st.registerOutParameter(1, 1);
            st.registerOutParameter(1, sqlReturnType);
            if (bindVars != null) {
                for (int z = 0; z < bindVars.length; z++) {


                    logger.info("stmt   :..........." + stmt);
                    logger.info("bindvar   :..........." + bindVars[z]);

                    st.setObject(z + 2, bindVars[z]);
                }
            }
            st.execute();

            return st.getObject(1);
        } catch (SQLException e) {
            throw new JboException(e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
    }


    protected Object getNamedBindParamValue(String varName, Object[] params) {
        Object result = null;
        if (getBindingStyle() == SQLBuilder.BINDING_STYLE_ORACLE_NAME) {
            if (params != null) {
                for (Object param : params) {
                    Object[] nameValue = (Object[])param;
                    String name = (String)nameValue[0];
                    if (name.equals(varName)) {
                        return (String)nameValue[1];
                    }
                }
            }
        }
        throw new JboException("No bind variable named '" + varName + "'");
    }

    /**
     * Store a new result set in the query-collection-private user-data context
     */
    protected void storeNewResultSet(Object qc, ResultSet rs) {
        ResultSet existingRs = getResultSet(qc);
        // If this query collection is getting reused, close out any previous rowset
        if (existingRs != null) {
            try {
                existingRs.close();
            } catch (SQLException s) {
            }
        }
        setUserDataForCollection(qc, rs);
        hasNextForCollection(qc); // Prime the pump with the first row.
    }


    /**
     * Retrieve the result set wrapper from the query-collection user-data
     */
    protected ResultSet getResultSet(Object qc) {
        return (ResultSet)getUserDataForCollection(qc);
    }

    @Override
    public void beforeRollback(TransactionEvent TransactionEvent) {
        if (this.isExecuted()) {
            ViewRowImpl currentRow = (ViewRowImpl)this.getCurrentRow();
            if (currentRow != null) {
                byte newRowState = currentRow.getNewRowState();
                if (newRowState != Row.STATUS_INITIALIZED &&
                    newRowState != Row.STATUS_NEW) {
                    currentRowKey = currentRow.getKey();
                    int rangeIndexOfCurrentRow =
                        this.getRangeIndexOf(currentRow);
                    currentRowIndexInRange = rangeIndexOfCurrentRow;
                }
            }
        }
        super.beforeRollback(TransactionEvent);
    }

    @Override
    public void afterRollback(TransactionEvent TransactionEvent) {
        super.afterRollback(TransactionEvent);
        if (currentRowKey != null) {
            this.executeQuery();
            Key k = new Key(currentRowKey.getAttributeValues());
            Row[] found = this.findByKey(k, 1);
            if (found != null && found.length == 1) {
                Row r = this.getRow(k);
                this.setCurrentRow(r);
                if (currentRowIndexInRange >= 0) {
                    this.scrollRangeTo(r, currentRowIndexInRange);
                }
            }
        }
        currentRowKey = null;
    }

    /**
     * Return either null or a new oracle.jbo.domain.Date
     */
    private static Date nullOrNewDate(Timestamp t) {
        return t != null ? new Date(t) : null;
    }

    /**
     * Return either null or a new oracle.jbo.domain.Number
     */
    private static Number nullOrNewNumber(BigDecimal b) {
        try {
            return b != null ? new Number(b) : null;
        } catch (SQLException s) {
        }
        return null;
    }
}
