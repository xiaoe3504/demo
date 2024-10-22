package com.psy.demo.utils;

import com.psy.demo.global.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateUtils {

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    public static String getTimeInterval(String dateTimeStr) {
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            // 如果格式不匹配，将抛出此异常
            log.error("Error parsing date time string: " + e.getMessage(), e);
            throw new BaseException("Error parsing date time string...");
        }

        Duration between = Duration.between(dateTime, now);
        long minutesDiff = between.toMinutes();
        if (minutesDiff < 60) {
            return minutesDiff + "分钟前";
        }
        long hoursDiff = between.toHours();
        if (hoursDiff < 24) {
            return hoursDiff + "个小时前";
        }
        long daysDiff = between.toDays();
        return daysDiff + "天前";
    }

    public static int getDaysMonth(int step) {
        // 获取当前日期
        LocalDate today = LocalDate.now().plusMonths(step);
        // 获取当前年份和月份
        YearMonth yearMonth = YearMonth.from(today);
        // 获取当前月份的天数
        return yearMonth.lengthOfMonth();
    }

    public static int dealDateStringToInt(String dateString) {
        // 获取当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date.getDayOfMonth();
    }

    public static int getFirstDateCurrentMonth() {
        // 获取本月第一天的日期
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        // 获取这一天是周几
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        return dayOfWeek.getValue();
    }


}
