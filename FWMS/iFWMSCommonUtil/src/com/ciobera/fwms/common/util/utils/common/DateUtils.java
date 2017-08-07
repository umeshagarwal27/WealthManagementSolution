/*****************************************************************************************************
 ** Program Name            - DateUtils.java
 ** Program Description     - Date Utility Class
 ** Date written            -
 ** Author                  - Umesh Agarwal
 ** Additional Information  -
 ** Copyright notice        -
 ******************************************************************************************************/

package com.ciobera.fwms.common.util.utils.common;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    private Date now;

    /**
     *     Letter   Date or Time Component  Presentation    Examples
    G   Era designator  Text    AD
    y   Year    Year    1996; 96
    M   Month in year   Month   July; Jul; 07
    w   Week in year    Number  27
    W   Week in month   Number  2
    D   Day in year     Number  189
    d   Day in month    Number  10
    F   Day of week in month    Number  2
    E   Day in week     Text    Tuesday; Tue
    a   Am/pm marker    Text    PM
    H   Hour in day (0-23)      Number  0
    k   Hour in day (1-24)      Number  24
    K   Hour in am/pm (0-11)    Number  0
    h   Hour in am/pm (1-12)    Number  12
    m   Minute in hour  Number  30
    s   Second in minute        Number  55
    S   Millisecond     Number  978
    z   Time zone       General time zone       Pacific Standard Time; PST; GMT-08:00
    Z   Time zone       RFC 822 time zone       -0800

    Pattern letters are usually repeated, as their number determines the exact presentation:

     * Text: For formatting, if the number of pattern letters is 4 or more, the full form is used; otherwise a short or abbreviated form is used if available. For parsing, both forms are accepted, independent of the number of pattern letters.
     * Number: For formatting, the number of pattern letters is the minimum number of digits, and shorter numbers are zero-padded to this amount. For parsing, the number of pattern letters is ignored unless it's needed to separate two adjacent fields.
     * Year: For formatting, if the number of pattern letters is 2, the year is truncated to 2 digits; otherwise it is interpreted as a number.
     */

    public DateUtils() {
        now = new Date();
    }

    public Date getNow() {
        return now;
    }

    public String getCommonDate() {
        return getFormattedDate("MM/dd/yyyy");
    }

    public String getCommonDateNumeric() {
        return getFormattedDate("MMddyyyy");
    }

    public String getYMD() {
        return getFormattedDate("yyyy-MM-dd");
    }

    public Date addYear() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getNow());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public String getShortDate() {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(now);
    }

    public String getMediumDate() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(now);
    }

    public String getLongDate() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(now);
    }

    public String getDayOfWeek() {
        return (new SimpleDateFormat("EEEE")).format(now);
    }

    public String getFormattedDate(String formatMask) {
        return (new SimpleDateFormat(formatMask)).format(now);
    }

    public String getYear() {
        return (new SimpleDateFormat("yyyy")).format(now);
    }

    public long getTime() {
        return now.getTime();
    }

    /**
     * Wrapper for the convertTimezone method.
     * Always returns time in PST.
     * @param tstamp
     *        A java.sql.Timestamp Object passed by client.
     * @param fromTimeZone
     *        A String Object. This is the TimeZone of the tstamp
     *        variable.
     * @return
     *        Null if Passed TimeZone value is invalid. For valid TimeZone
     *        values check TimeZone.getAvailableIDs().
     *        If valid values then return the converted Timestamp object.
     */
    public static Timestamp convertTimezonePST(Timestamp tstamp,
                                               String fromTimeZone) {

        return convertTimezone(tstamp, fromTimeZone, "PST");

    }


    /**
     * The method <code>convertTimezone</code> converts a given Time into a
     * specified timezone.
     * @param tstamp
     *        A java.sql.Timestamp Object passed by client.
     * @param fromTimeZone
     *        A String Object. This is the TimeZone of the tstamp
     *        variable.
     * @param toTimeZone
     *        A String Object. This is the TimeZone to be converted
     *        into.
     * @return
     *        Null if Passed TimeZone values are invalid. For valid TimeZone
     *        values check TimeZone.getAvailableIDs().
     *        If valid values then return the converted Timestamp object.
     */
    public static Timestamp convertTimezone(Timestamp tstamp,
                                            String fromTimeZone,
                                            String toTimeZone) {
        //Calendar object for the input date
        Calendar fromCal = null;

        //Calendar object for the output date
        Calendar toCal = null;

        //return object
        Timestamp changedTimestamp = null;

        //Calculates total offset from GMT (Includes Daylight saving
        //valid till 2037)
        int fromOffset = 0;

        //Calculates total offset from GMT (Includes Daylight saving
        //valid till 2037)
        int toOffset = 0;

        //Difference between time zones.
        int offsetDiff = 0;

        long toTime = 0;

        //Array of valid TimeZone ID's
        String[] availableIDs = TimeZone.getAvailableIDs();
        //Sort Array before binary search
        Arrays.sort(availableIDs);
        if (Arrays.binarySearch(availableIDs, fromTimeZone) > 0 &&
            Arrays.binarySearch(availableIDs, toTimeZone) > 0 &&
            tstamp != null) {
            fromCal = Calendar.getInstance(TimeZone.getTimeZone(fromTimeZone));
            fromCal.setTime(tstamp);
            fromOffset =
                fromCal.get(Calendar.ZONE_OFFSET) +
                fromCal.get(Calendar.DST_OFFSET);
            toCal = Calendar.getInstance(TimeZone.getTimeZone(toTimeZone));
            toCal.setTime(tstamp);
            toOffset =
                toCal.get(Calendar.ZONE_OFFSET) +
                toCal.get(Calendar.DST_OFFSET);
            offsetDiff = toOffset - fromOffset;
            toTime = toCal.getTime().getTime();
            toTime += offsetDiff;
            changedTimestamp = new Timestamp(toTime);
        }

        return changedTimestamp;
    }
}
