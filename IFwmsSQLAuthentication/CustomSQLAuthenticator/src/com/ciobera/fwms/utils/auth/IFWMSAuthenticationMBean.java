package com.ciobera.fwms.utils.auth;


import javax.management.*;
import weblogic.management.commo.RequiredModelMBeanWrapper;



/**
 * No description provided.
 * @root IFWMSAuthentication
 * @customizer com.ciobera.fwms.utils.auth.IFWMSAuthenticationImpl(new RequiredModelMBeanWrapper(this))
 * @dynamic false

 */
public interface IFWMSAuthenticationMBean extends weblogic.management.commo.StandardInterface,weblogic.descriptor.DescriptorBean, weblogic.management.security.authentication.AuthenticatorMBean {
                
        


        /**
         * No description provided.

         * @default "com.ciobera.fwms.utils.auth.db.IFWMSAuthenticationProviderImpl"
         * @dynamic false
         * @non-configurable
         * @validatePropertyDeclaration false

         * @preserveWhiteSpace
         */
        public java.lang.String getProviderClassName ();


        
        


        /**
         * No description provided.

         * @default "jdbc:oracle:thin:@localhost:1521:orcl"
         * @dynamic false

         * @preserveWhiteSpace
         */
        public java.lang.String getDbAuthUrl ();


        /**
         * No description provided.

         * @default "jdbc:oracle:thin:@localhost:1521:orcl"
         * @dynamic false

         * @param newValue - new value for attribute DbAuthUrl
         * @exception InvalidAttributeValueException
         * @preserveWhiteSpace
         */
        public void setDbAuthUrl (java.lang.String newValue)
                throws InvalidAttributeValueException;


        
        


        /**
         * No description provided.

         * @default "hr"
         * @dynamic false

         * @preserveWhiteSpace
         */
        public java.lang.String getDbAuthUsername ();


        /**
         * No description provided.

         * @default "hr"
         * @dynamic false

         * @param newValue - new value for attribute DbAuthUsername
         * @exception InvalidAttributeValueException
         * @preserveWhiteSpace
         */
        public void setDbAuthUsername (java.lang.String newValue)
                throws InvalidAttributeValueException;


        
        


        /**
         * No description provided.

         * @default "hr"
         * @dynamic false

         * @preserveWhiteSpace
         */
        public java.lang.String getDbAuthPassword ();


        /**
         * No description provided.

         * @default "hr"
         * @dynamic false

         * @param newValue - new value for attribute DbAuthPassword
         * @exception InvalidAttributeValueException
         * @preserveWhiteSpace
         */
        public void setDbAuthPassword (java.lang.String newValue)
                throws InvalidAttributeValueException;


        
        


        /**
         * No description provided.

         * @default "Authentication Provider"
         * @dynamic false
         * @non-configurable
         * @validatePropertyDeclaration false

         * @preserveWhiteSpace
         */
        public java.lang.String getDescription ();


        
        


        /**
         * No description provided.

         * @default "1.0"
         * @dynamic false
         * @non-configurable
         * @validatePropertyDeclaration false

         * @preserveWhiteSpace
         */
        public java.lang.String getVersion ();



        
        /**
         * @default "IFWMSAuthentication"
         * @dynamic false
         * @owner RealmAdministrator
         * @VisibleToPartitions ALWAYS
         */
         public java.lang.String getName();

          

}
