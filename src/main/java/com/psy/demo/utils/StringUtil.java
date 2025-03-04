package com.psy.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.psy.demo.global.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;

public class StringUtil {

    private static final String CHARSET = "UTF-8";


    public static String toQueryUrl(String baseUrl, List<? extends NameValuePair> params) {
        String url = baseUrl;
        if (CollectionUtils.isNotEmpty(params)) {
            String queryStr = params.stream()
                    .map(p -> encodeAndConcat(p.getName(), p.getValue()))
                    .collect(Collectors.joining("&"));
            url = baseUrl + "?" + queryStr;
        }
        return url;
    }

    /**
     * To query URL used in GET operation.
     */


    private static String encodeAndConcat(String key, Object val) {
        try {
            String valStr = val == null ? "" : val.toString();
            return URLEncoder.encode(key, CHARSET) + "=" + URLEncoder.encode(valStr, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String doubleToPercentString(double value) {
        return String.format("%.0f%%", value * 100);
    }

    //string 变成2位小数
    public static String intTo2Decimal(int amount) {
        return String.format("%.2f", amount / 100.0);
    }

    public static boolean isNumeric(String str) {
        // 正则表达式可以检测整数和小数，包括正负号和小数点
        String regex = "[-+]?\\d*(\\.\\d+)?$";
        return str.matches(regex);
    }


    public static String[] getStringArr(String binaryString, List<String> source) {
        // 遍历字符串的每一位
        List<String> list = new ArrayList<>();
        for (int i = 0; i < binaryString.length(); i++) {
            String bitString = String.valueOf(binaryString.charAt(i));
            if (bitString.equals("1")) {
                list.add(source.get(i));
            }
        }
        return list.toArray(new String[]{});
    }


    public static String getPriceString(String amount) {
        if (StringUtils.isEmpty(amount) || !StringUtils.isNumeric(amount)) {
            throw new BaseException("amount wrong format");
        }
        int amountI = Integer.valueOf(amount);
        double d = amountI / 100.0;
        String formattedValue = String.format("%.2f", d);
        return "总价¥" + formattedValue + "元 实付¥" + formattedValue + "元";

    }

    public static LinkedHashMap<String, Long> convertToTreeMapWithComparator(Map<String, Long> map, Comparator<? super String> comparator) {
        // 使用Stream API按value排序，并将结果收集到LinkedHashMap中
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // 按value逆序排序
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // 保留原始key
                        Map.Entry::getValue, // 保留原始value
                        (oldValue, newValue) -> oldValue, // 合并函数，这里不会用到因为key是唯一的
                        LinkedHashMap::new // 使用LinkedHashMap来保持排序后的顺序
                ));


    }

    public static List<String> getUniString(boolean isListenStyle) {

        List<List<String>> list = isListenStyle ? getListenStyleListList() : getExpertAreasListList();

        List<String> listFinal = new ArrayList<>();
        list.forEach(listFinal::addAll);

        Map<String, Long> map = listFinal.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Comparator<String> comparator = (s1, s2) -> Long.compare(map.get(s2), map.get(s1));
        // 使用Stream API将Map转换为TreeMap并带上自定义比较器
        LinkedHashMap<String, Long> sortedMap = convertToTreeMapWithComparator(map, comparator);
        System.out.println(JSONObject.toJSONString(sortedMap));
        return new ArrayList<>(sortedMap.keySet());
    }

    @NotNull
    private static List<List<String>> getListenStyleListList() {
        List<String> list1 = Arrays.asList("亲和力", "温暖", "和蔼", "尊重", "用心陪伴");
        List<String> list2 = Arrays.asList("温暖", "细腻", "抱持");
        List<String> list3 = Arrays.asList("温暖", "抱持", "真诚", "涵容", "不评判");
        List<String> list4 = Arrays.asList("温暖", "有耐心", "保密", "尊重", "理性");
        List<String> list5 = Arrays.asList("尊重", "温暖", "思考");
        List<String> list6 = Arrays.asList("温暖", "尊重", "和蔼");
        List<String> list7 = Arrays.asList("温暖", "尊重", "治愈");
        List<String> list8 = Arrays.asList("真诚", "温暖", "用心", "友善", "尊重");
        List<String> list9 = Arrays.asList("温柔", "坚定", "专业");
        List<String> list10 = Arrays.asList("温暖", "尊重", "理性");
        List<String> list11 = Arrays.asList("温暖", "抱持", "真诚", "耐心", "善解人意");


        return getLists(list1, list2, list3, list4, list5, list6, list7, list8, list9, list10, list11);
    }

    @NotNull
    private static List<List<String>> getExpertAreasListList() {
        List<String> list1 = Arrays.asList("自我成长", "亲子教育", "亲密关系", "人际关系");
        List<String> list2 = Arrays.asList("自我成长", "情绪困扰", "创伤疗愈", "亲子关系", "原生家庭");
        List<String> list3 = Arrays.asList("抑郁焦虑", "人际关系", "婚姻家庭");
        List<String> list4 = Arrays.asList("亲密关系", "自我成长", "亲子教育", "人际关系");
        List<String> list5 = Arrays.asList("个人成长", "情绪情感", "人际关系");
        List<String> list6 = Arrays.asList("自我成长", "情绪困扰", "婚姻情感", "亲密关系");
        List<String> list7 = Arrays.asList("人际关系", "自我成长 ", "情绪困扰");
        List<String> list8 = Arrays.asList("婚姻情感", "亲子关系", "个人成长", "人际关系");
        List<String> list9 = Arrays.asList("亲子关系", "情绪情感");
        List<String> list10 = Arrays.asList("自我成长", "亲子教育", "人际关系", "职场困扰", "情绪困扰");
        List<String> list11 = Arrays.asList("抑郁焦虑", "情绪情感", "婚姻爱情", "原生家庭", "个人成长", "职场规划", "家庭教育", "亲子关系");

        return getLists(list1, list2, list3, list4, list5, list6, list7, list8, list9, list10, list11);
    }

    @NotNull
    private static List<List<String>> getLists(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10, List<String> list11) {
        List<List<String>> listFinal = new ArrayList<>();
        listFinal.add(list1);
        listFinal.add(list2);
        listFinal.add(list3);
        listFinal.add(list4);
        listFinal.add(list5);
        listFinal.add(list6);
        listFinal.add(list7);
        listFinal.add(list8);
        listFinal.add(list9);
        listFinal.add(list10);
        listFinal.add(list11);
        return listFinal;
    }



    private static void dealList(List<String> list1, List<String> constantString) {
        String[] arr = new String[constantString.size()];
        for (int i = 0; i < constantString.size(); i++) {
            if (list1.contains(constantString.get(i))) {
                arr[i] = "1";
            } else {
                arr[i] = "0";
            }
        }
        String res = String.join("", arr);
        System.out.println(res);
    }

    public static String getSrc(String type, String name) {
        String result;
        try {
            result = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseException("mp3 getSrc encode error...");
        }
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return MyConstantString.GROWTH_CENTER_URL_PREFIX + type + "/" + result + MyConstantString.GROWTH_CENTER_URL_SUFFIX;
    }



}
