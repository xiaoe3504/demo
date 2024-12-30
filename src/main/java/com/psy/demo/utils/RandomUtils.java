package com.psy.demo.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static String genRandomLetters(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            //判断产生的随机数是0还是1，是0进入if语句用于输出数字，是1进入else用于输出字符
            if (Math.random() < 0.5) {
                sb.append(RANDOM.nextInt(10));
            } else {
                sb.append((char) ('A' + RANDOM.nextInt(26)));
            }
        }
        return sb.toString();
    }


}
