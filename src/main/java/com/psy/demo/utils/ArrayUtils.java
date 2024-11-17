package com.psy.demo.utils;

import com.alibaba.fastjson.JSONObject;


public class ArrayUtils {

    public static String[][] generateTwoDimensionalArray(String[] originalArray,int step) {
        int length = originalArray.length;

        if (length <= step) {
            // 如果数组长度不大于3，直接返回包含原始数组的二维数组
            return new String[][]{originalArray};
        } else {
            // 计算二维数组的行数和列数
            int rows = (length + step-1) / step; // 向上取整，确保能容纳所有元素
            int cols = step;

            String[][] twoDimensionalArray = new String[rows][cols];

            // 填充二维数组
            for (int i = 0; i < length; i++) {
                int row = i / step;
                int col = i % step;
                twoDimensionalArray[row][col] = originalArray[i];
            }

            return twoDimensionalArray;
        }
    }



}
