package com.psy.demo.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
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
