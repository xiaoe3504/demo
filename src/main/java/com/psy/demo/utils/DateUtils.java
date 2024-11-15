package com.psy.demo.utils;

import com.psy.demo.global.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

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

    // 辅助方法：计算并返回给定日期后的第一个周三
    private static LocalDate getNextWednesday(LocalDate date) {
        // 获取当前日期是周几
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 根据当前周几来计算距离下一个周三需要加的天数
        // 注意：若当前已是周三，则需加7天来到下一个周三，否则直接计算到本周三的天数
        int daysToAdd = (dayOfWeek == DayOfWeek.WEDNESDAY) ? 7 : (DayOfWeek.WEDNESDAY.getValue() - dayOfWeek.getValue() + 7) % 7;

        // 返回计算后的下一个周三的日期
        return date.plusDays(daysToAdd);
    }

    public static boolean validWithdrawalTime(LocalDateTime orderTime) {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 设定目标为下一个周三
        LocalDate nextWednesday = getNextWednesday(today);
        // 计算两个日期之间的天数差异
        long daysUntilNextWed = ChronoUnit.DAYS.between(orderTime.toLocalDate(), nextWednesday);

        // 打印出结果
//        System.out.println("距离下一个周三还有 " + daysUntilNextWed + " 天");

        return daysUntilNextWed >= 7;
    }



}
