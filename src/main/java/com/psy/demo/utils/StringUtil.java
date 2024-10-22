package com.psy.demo.utils;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

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
        String percentValue = String.format("%.2f%%", value * 100);
        return percentValue;
    }

    public static String[] getStringArr(String binaryString, List<String> source) {
        // 遍历字符串的每一位
        List<String> list=new ArrayList<>();
        for (int i = 0; i < binaryString.length(); i++) {
            String bitString = String.valueOf(binaryString.charAt(i));
            if (bitString.equals("1")) {
               list.add(source.get(i));
            }
        }
        return list.toArray(new String[]{});
    }

    public static void main(String[] args) {
        List<String>source=new ArrayList<>();

        String[] res = getStringArr("10100", MyConstantString.LISTEN_STYLE_LIST);
        System.out.println(Arrays.toString(res));
    }


}
