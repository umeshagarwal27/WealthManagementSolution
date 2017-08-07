package com.ciobera.fwms.common.util.logger;


import oracle.adf.share.logging.ADFLogger;

/**
 * Description:  Perform all the logging feature
 * @version 1.0
 * @author Sriram S
 */

public class LoggingUtil {


    /**This method will return the ADFLogger for the respective class
     * @param className
     * @return
     */
    public static ADFLogger createLogger(Class className) {
        return ADFLogger.createADFLogger(className);
    }

    /**Method to log debug messages
     * @param logObject
     * @param logMsg
     */
    public static void logDebugMessages(ADFLogger logObject, String logMsg) {
        if (logObject.isFine()) {
            logObject.fine(logMsg);
        }
    }

    /**Method to log warnings
     * @param logObject
     * @param logMsg
     */
    public static void logWarnMessages(ADFLogger logObject, String logMsg) {
        if (logObject.isWarning()) {
            logObject.warning(logMsg);
        }
    }

    /**Method to log exceptions and error messages
     * @param logObject
     * @param logMsg
     */
    public static void logErrorMessages(ADFLogger logObject, String logMsg) {
        if (logObject.isSevere()) {
            logObject.severe(logMsg);
        }
    }
}
