package weblogic.management.security;


import  java.util.ArrayList;
import  java.util.Map;
import  java.util.HashMap;
import  java.util.Set;
import  weblogic.utils.codegen.ImplementationFactory;
import  weblogic.utils.codegen.RoleInfoImplementationFactory;




/**
 * This is a generated class that provides a mapping from 
 * interface classes to implementation classes
 */
public class IFWMSAUTHENTICATIONBeanInfoFactory implements RoleInfoImplementationFactory {
  private static final Map interfaceMap;
  private static final ArrayList roleInfoList;
  private static final IFWMSAUTHENTICATIONBeanInfoFactory SINGLETON;
  static {
    interfaceMap = new HashMap(1);
    interfaceMap.put("com.ciobera.fwms.utils.auth.IFWMSAuthenticationMBean","com.ciobera.fwms.utils.auth.IFWMSAuthenticationMBeanImplBeanInfo");
    roleInfoList = new ArrayList(1);
    roleInfoList.add("com.ciobera.fwms.utils.auth.IFWMSAuthenticationMBean");
    SINGLETON = new IFWMSAUTHENTICATIONBeanInfoFactory();
  }


  public static final ImplementationFactory getInstance() {
    return SINGLETON; 
  }


  public String getImplementationClassName( String interfaceName ) {
    return (String)interfaceMap.get(interfaceName);
  }


  public String[] getInterfaces() {
    Set keySet = interfaceMap.keySet();
    return (String[])keySet.toArray(new String[keySet.size()]);
  }


  public String[] getInterfacesWithRoleInfo() {
    return (String[])roleInfoList.toArray(new String[roleInfoList.size()]);
  }

  public String getRoleInfoImplementationFactoryTimestamp() {
    return "1502180852980";
  }


}
