package com.psy.demo.utils;

import com.psy.demo.vo.res.MoodMapResFinalVO;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static MoodMapResFinalVO [][] convertListToDoubleArray(List<MoodMapResFinalVO> list, int sizePerSubArray) {
        int numberOfSubArrays = (int) Math.ceil(list.size() / (double) sizePerSubArray);
        MoodMapResFinalVO[][] doubleArray =  new MoodMapResFinalVO[numberOfSubArrays][sizePerSubArray];

        for (int i = 0; i < list.size(); i++) {
            int subArrayIndex = i / sizePerSubArray;
            int elementIndex = i % sizePerSubArray;
            doubleArray[subArrayIndex][elementIndex] = list.get(i);
        }
        return doubleArray;
    }


}
