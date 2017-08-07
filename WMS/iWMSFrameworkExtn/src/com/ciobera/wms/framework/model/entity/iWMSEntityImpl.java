package com.ciobera.wms.framework.model.entity;


import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jbo.AttributeDef;
import oracle.jbo.AttributeList;
import oracle.jbo.server.DBTransactionImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;

public class iWMSEntityImpl extends EntityImpl {
    public iWMSEntityImpl() {
        super();
    }

    /**
     * This method iterates through all the attributes of current entity and populate 
     * CreatedBy attribute during DML_INSERT operation and LastUpdatedBy attribute during
     * DML_INSERT and DML_UPDATE operation with userId fetched from getUserId() method.
     */
    protected void prepareForDML(int i, TransactionEvent transactionEvent) {
        if (i == DML_INSERT || i == DML_UPDATE) {
            long userId = getUserId();
            String[] attrNames = this.getAttributeNames();
            for (int j = 0; j < attrNames.length; j++) {
                if ("CreatedBy".equalsIgnoreCase(attrNames[j]) && i == DML_INSERT) {
                    this.setAttribute(attrNames[j], userId);
                }
                if ("LastUpdatedBy".equalsIgnoreCase(attrNames[j])) {
                    this.setAttribute(attrNames[j], userId);
                }
            }
        }
        super.prepareForDML(i, transactionEvent);
    }

    /**
     * Helper method to retreive userId for the loggedin user.
     * This method query XX_IAP_CURRENT_USERDETAIL_V db view to fetch USER_ID.
     * @return
     */
    private long getUserId() {
        Statement stmt = null;
        long userId = 0;
        String sqlQuery = "Select * FROM XX_IAP_CURRENT_USERDETAIL_V";
        try {
            DBTransactionImpl dbTransaction = (DBTransactionImpl) getDBTransaction();
            stmt = dbTransaction.createStatement(0);
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                userId = rs.getLong("USER_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return userId;
    }

    /**
     * This method iterates through all of the attribute definitions
     * for the current entity and for any attribute that has the
     * attribute-level custom property named "SequenceName" set, it populates
     * that attribute with the next value from the Oracle database sequence
     * whose name is provided as the value of that custom property.
     * @param attributeList
     */
    protected void create(AttributeList attributeList) {
        // Call default create method
        super.create(attributeList);

        //Look for SequenceName Attribute and setup the value
        for (AttributeDef def : getEntityDef().getAttributeDefs()) {
            String sequenceName = (String) def.getProperty("SequenceName");
            if (sequenceName != null) {
                SequenceImpl s = new SequenceImpl(sequenceName, getDBTransaction());
                Class javaType = def.getJavaType();
                String sequenceVal = s.getSequenceNumber().toString();
                if ("java.lang.Integer".equals(javaType.getName())) {
                    populateAttributeAsChanged(def.getIndex(), new Integer(sequenceVal));
                } else if ("java.math.BigInteger".equals(javaType.getName())) {
                    populateAttributeAsChanged(def.getIndex(), new BigInteger(sequenceVal));
                } else if ("java.math.BigDecimal".equals(javaType.getName())) {
                    populateAttributeAsChanged(def.getIndex(), new BigDecimal(sequenceVal));
                } else if ("java.lang.Long".equals(javaType.getName())) {
                    populateAttributeAsChanged(def.getIndex(), new Long(sequenceVal));
                } else {
                    populateAttributeAsChanged(def.getIndex(), s.getSequenceNumber());
                }
            }
        }
    }
}

