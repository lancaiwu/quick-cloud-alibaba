package com.lancaiwu.alibaba.cloud.security.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lancaiwu
 * @since 2020/10/8 09:53
 */
@SuppressWarnings("Duplicates")
@Slf4j
public class TimeUtil {

    public static final ThreadLocal<SimpleDateFormat> FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_DAY = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_HOUR = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_MINUTE = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_SECOND = ThreadLocal.withInitial(() -> new SimpleDateFormat("ss"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_DAY_HOUR_MINUTE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_HOUR_MINUTE = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm"));

    public static long getTimeId(String time) {
        long timeId = 0;
        try {
            Date date = FORMAT.get().parse(time);
            timeId = date.getTime();
        } catch (ParseException e) {
            log.error("FORMAT parse time failure!! error={}", e.getMessage());
        }
        return timeId;
    }

    public static Date parseTime(String time) {
        Date parse = null;
        try {
            parse = TimeUtil.FORMAT.get().parse(time);
        } catch (ParseException e) {
            log.error("FORMAT parse time failure!! error={}", e.getMessage());
        }
        return parse;
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("Thread sleep failure!! error={}", e.getMessage());
        }
    }

    // 根据日期取得星期几
    public static String getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }
}