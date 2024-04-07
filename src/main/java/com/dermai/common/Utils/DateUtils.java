package com.dermai.common.Utils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Shaobo
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }
}
