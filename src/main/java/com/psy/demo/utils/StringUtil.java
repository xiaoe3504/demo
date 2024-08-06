package com.psy.demo.utils;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

}
