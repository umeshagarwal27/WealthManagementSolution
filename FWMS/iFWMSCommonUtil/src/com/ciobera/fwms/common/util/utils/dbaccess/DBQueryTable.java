/*****************************************************************************************************
 ** Program Name            - DBQueryTable.java
 ** Program Description     - This class contains the logic to query DB.
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/
package com.ciobera.fwms.common.util.utils.dbaccess;

import java.sql.Connection;

public class DBQueryTable {
    private GetDataFromDatabase data;

    public DBQueryTable(String JNDI_DS) {
        data = new GetDataFromDatabase(true, JNDI_DS);
    }

    public String[] getAllFields(String tablename) {
        //This method will list all the fields for the particular specified tablename
        String query = "select * from " + tablename;
        return data.getFields(query);
    }

    public String[][] getAllqueryvalues(String query) {
        //This method will execute the specified query and return two dimensional array that contains each row
        return data.getValues(query);
    }

    public int ExecQuery(String query) {
        //This method will return the value for the particular for the specified tablename
        return data.ExecQuery(query);
    }

    public Connection getDBConnection() {
        //This method will return a connection object from the pool
        return data.getDBConnection();
    }

    public void closeDBConnection() {
        //This method closes the DB connection object
        data.closeDBConnection();
    }
}
