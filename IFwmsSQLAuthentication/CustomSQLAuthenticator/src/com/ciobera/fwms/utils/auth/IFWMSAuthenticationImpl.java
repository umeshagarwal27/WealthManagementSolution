package com.ciobera.fwms.utils.auth;


import javax.management.*;
import javax.management.modelmbean.ModelMBean;
import weblogic.management.commo.CommoMBeanInstance;
import java.lang.reflect.*;


public  class IFWMSAuthenticationImpl extends weblogic.management.security.authentication.AuthenticatorImpl
  implements java.io.Serializable
{
   static final long serialVersionUID = 1L;

   
   /**
    * @deprecated Replaced by IFWMSAuthenticationImpl (ModelMBean base).
    */
   public IFWMSAuthenticationImpl (CommoMBeanInstance base)
        throws MBeanException
   { super(base); }




   //****************************************************************************************************
   //***************************************** GENERATED METHODS ****************************************
   //****************************************************************************************************


   //****************************************************************************************************
   //******************************************* METHODS STUBS ******************************************
   //****************************************************************************************************
//@constructorMethods


}

