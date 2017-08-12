package com.ciobera.fwms.common.util.utils.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Properties;

import oracle.adf.share.logging.ADFLogger;


public class EnvironmentProperties implements Serializable {
    @SuppressWarnings("compatibility:5592126336017826964")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(EnvironmentProperties.class);
    private static String CONFIG = "Config.properties";
    public static final String DS = System.getProperty("file.separator");
    private static Properties appProperties = null;

    /**
     * default constructor
     */
    public EnvironmentProperties() {
        super();
    }

    /**
     * Returns the propery value as String
     * @param propName
     * @return
     */
    public static String getConfigProperty(String propName) {
        if (appProperties == null) {
            readProperties();
        }
        //        _logger.warning("property value returned :- " + propName + "::" +
        //                        appProperties.getProperty(propName));
        return appProperties.getProperty(propName);
    }


    /**
     * Static Method to Read Properties as save in static properties object appProperties
     */
    private static void readProperties() {
        _logger.warning("entering into readProperties");
        FileInputStream propertiesStream = null;
        appProperties = null;
        try {
            String propertyFilePath =
                System.getProperty("wls.home") + DS + "propertyFile" + DS + "Config.properties";
            _logger.warning("property file path : " + propertyFilePath);
            //path of properties file
            if (propertyFilePath != null) {
                propertiesStream = new FileInputStream(propertyFilePath);
                appProperties = new Properties();
                appProperties.load(propertiesStream);
            }
        } catch (Exception e) {
            _logger.severe("EnvironmentProperties Exception:" + e);
            e.printStackTrace();
        } finally {

            try {
                if (propertiesStream != null) {
                    propertiesStream.close();
                }
            } catch (IOException ioe) {
                _logger.severe("EnvironmentProperties IOException:" + ioe);
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Method to return InputSteam based on Jar, File System, OS
     * @return
     */
    public InputStream getFileStream() {
        InputStream in = null;

        ClassLoader servletClassLoader = this.getClass().getClassLoader();
        try {
            in = servletClassLoader.getSystemResourceAsStream(CONFIG);
        } catch (Exception e) {
            _logger.severe("EnvironmentProperties Exception:" + e);
            e.printStackTrace();
        }
        return in;
    }
}
