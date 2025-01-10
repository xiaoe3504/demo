package com.psy.demo.utils;

import java.util.Arrays;
import java.util.List;

public class MyConstantString {
    //私钥
    public static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDz1EXZgwc/sShz\n" +
            "FLlI4PR2rJKlyS6yeC8hgrfK+cVYPj32nc7BPmWznd2H0nVQRoofSepRz1GTVhRc\n" +
            "Q9gzEvOKcM9NeZH24TEnczCFbA9yO4NEwvQUXOvf8Ojkh50MCEeqk4BUZQOoY90L\n" +
            "dFeMfWozNmS03jVlmhxJ0zX+6nS8a+9xMSicvGt94xxgSzkNUekcSARr74mRXRIm\n" +
            "jlyA8rgma17wySSD/FQ6bxYo2RhlWXN5m/19Gr5wfX1KdSPLJx9cOPrImLCrJNeh\n" +
            "FOaennTNjzHIAEkiVvcj0f8nsahpbrTZXx9h7X6U5fGNKeOTWuh3SqGkjFohZust\n" +
            "REFgiIUNAgMBAAECggEBALrCvvXKDQVWFtt+5iTSWa2EpZbisCf3MnKCg3CvO99J\n" +
            "XYDGgxU1TAT2SDa+lyjzvtwUa6xaFeu6pUG+nZ0RSQWjPsXi9lCZUGMdMe8gQ/yF\n" +
            "ZRiIExh9zBXtmxVgYoRS20HmVQdPkCuswiUB6gUsEupDIYQAJ/o4vq41X0hwIRYo\n" +
            "P5g1WEXkzgA2/8MCURC7vCxuuND6TVLCf36K21dHvXsrrTTihgX2HW3DGiv3KDtG\n" +
            "e2x4JcYrI17tCwRdP1HChloE49m3pO8wz8fcJeLwhnT7qHVljmKFSvlw9IE1ucaA\n" +
            "926FTXUaj1ZO16Ch0fEldh6kkU1EbEXLa3AwXr/ZVakCgYEA/+zeqTOAiBMLTT6x\n" +
            "qeCmoyz2H4HDMdV5+NYkLOj0EuwV/ioOWUSNsKAMoDzw8FVZS07nlKBr6SIJOK94\n" +
            "V5TOenp3uTSoKPCoKxkzH2mu07JSD/HM9XxjNyDqDe6Jw28nkB6UBuNwHa/p4WBa\n" +
            "Q9Ja5Lkw/bMTW9A928GAx0yho5sCgYEA8+Z/uGZxpqnM0wHOqHbTiV+x00qVnIEm\n" +
            "Acx4iZBUTECEzeYn8g4I5q0jlJN6y5cz96R1q6r5wHAPTWaHkKQ1AY1/8t/mDUck\n" +
            "Ez9kyIG8rNalVZBSqTuXnpqYUG+PM4YPTQqHt5iCRaWadAgqqa7zJX4YFyDXTSYR\n" +
            "kHKwqfvz6HcCgYB4uIEnizW7+k/AdixUF7ZDDnhi0FEE9s/8Qvb5gEFxk4+kPq5a\n" +
            "CW5UvIp4I4b7tybR0TyH7Uw5wmZbKNo5ZeYn/BDxehdAiJSrD83/qVhgX6M6BjNK\n" +
            "MQyT+qCtRbISUCJ3ZxUoUqyWNF68gzjQ+S8K6Rhk883T51zmhlZ6rHkqtQKBgGo8\n" +
            "YjFt3d8cYu0eAmf6qhVSK2C/Qf+TeXybFX3F8hHsoGwW7htO5a2kiLT0sC3xxF7/\n" +
            "sYl+xZ5+sQujtzTXcYclnS+rOsdZz2ra2AlGCWq3a/ijDn5MSLR63hFbJmLo581E\n" +
            "4uyjb0rq4SXdUMfsPlh8Xf3k/iLKjCgA7NZ9ujXtAoGAVuXpTFQjJzE5MgBr1oIB\n" +
            "6NSh1kO6AQASpb3fv4CBExxPkJzycPlKZukN9nnnoU3x5oxB51gqYYOy4Y9ojMvj\n" +
            "81kOOzLYapHQUBVtmP/Ueqs01A1bTctMt6lphg5lxwsMaTpkMZxnFzlIjTvBrQHJ\n" +
            "miraZBbA7xtFBcywSXo9RXo=\n" +
            "-----END PRIVATE KEY-----\n";
    //公钥
    public static final String PUBLIC_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDz1EXZgwc/sShz\n" +
            "FLlI4PR2rJKlyS6yeC8hgrfK+cVYPj32nc7BPmWznd2H0nVQRoofSepRz1GTVhRc\n" +
            "Q9gzEvOKcM9NeZH24TEnczCFbA9yO4NEwvQUXOvf8Ojkh50MCEeqk4BUZQOoY90L\n" +
            "dFeMfWozNmS03jVlmhxJ0zX+6nS8a+9xMSicvGt94xxgSzkNUekcSARr74mRXRIm\n" +
            "jlyA8rgma17wySSD/FQ6bxYo2RhlWXN5m/19Gr5wfX1KdSPLJx9cOPrImLCrJNeh\n" +
            "FOaennTNjzHIAEkiVvcj0f8nsahpbrTZXx9h7X6U5fGNKeOTWuh3SqGkjFohZust\n" +
            "REFgiIUNAgMBAAECggEBALrCvvXKDQVWFtt+5iTSWa2EpZbisCf3MnKCg3CvO99J\n" +
            "XYDGgxU1TAT2SDa+lyjzvtwUa6xaFeu6pUG+nZ0RSQWjPsXi9lCZUGMdMe8gQ/yF\n" +
            "ZRiIExh9zBXtmxVgYoRS20HmVQdPkCuswiUB6gUsEupDIYQAJ/o4vq41X0hwIRYo\n" +
            "P5g1WEXkzgA2/8MCURC7vCxuuND6TVLCf36K21dHvXsrrTTihgX2HW3DGiv3KDtG\n" +
            "e2x4JcYrI17tCwRdP1HChloE49m3pO8wz8fcJeLwhnT7qHVljmKFSvlw9IE1ucaA\n" +
            "926FTXUaj1ZO16Ch0fEldh6kkU1EbEXLa3AwXr/ZVakCgYEA/+zeqTOAiBMLTT6x\n" +
            "qeCmoyz2H4HDMdV5+NYkLOj0EuwV/ioOWUSNsKAMoDzw8FVZS07nlKBr6SIJOK94\n" +
            "V5TOenp3uTSoKPCoKxkzH2mu07JSD/HM9XxjNyDqDe6Jw28nkB6UBuNwHa/p4WBa\n" +
            "Q9Ja5Lkw/bMTW9A928GAx0yho5sCgYEA8+Z/uGZxpqnM0wHOqHbTiV+x00qVnIEm\n" +
            "Acx4iZBUTECEzeYn8g4I5q0jlJN6y5cz96R1q6r5wHAPTWaHkKQ1AY1/8t/mDUck\n" +
            "Ez9kyIG8rNalVZBSqTuXnpqYUG+PM4YPTQqHt5iCRaWadAgqqa7zJX4YFyDXTSYR\n" +
            "kHKwqfvz6HcCgYB4uIEnizW7+k/AdixUF7ZDDnhi0FEE9s/8Qvb5gEFxk4+kPq5a\n" +
            "CW5UvIp4I4b7tybR0TyH7Uw5wmZbKNo5ZeYn/BDxehdAiJSrD83/qVhgX6M6BjNK\n" +
            "MQyT+qCtRbISUCJ3ZxUoUqyWNF68gzjQ+S8K6Rhk883T51zmhlZ6rHkqtQKBgGo8\n" +
            "YjFt3d8cYu0eAmf6qhVSK2C/Qf+TeXybFX3F8hHsoGwW7htO5a2kiLT0sC3xxF7/\n" +
            "sYl+xZ5+sQujtzTXcYclnS+rOsdZz2ra2AlGCWq3a/ijDn5MSLR63hFbJmLo581E\n" +
            "4uyjb0rq4SXdUMfsPlh8Xf3k/iLKjCgA7NZ9ujXtAoGAVuXpTFQjJzE5MgBr1oIB\n" +
            "6NSh1kO6AQASpb3fv4CBExxPkJzycPlKZukN9nnnoU3x5oxB51gqYYOy4Y9ojMvj\n" +
            "81kOOzLYapHQUBVtmP/Ueqs01A1bTctMt6lphg5lxwsMaTpkMZxnFzlIjTvBrQHJ\n" +
            "miraZBbA7xtFBcywSXo9RXo=\n" +
            "-----END PRIVATE KEY-----\n";
    //商户号
    public static final String MERCHANT_ID = "1666640344";
    //商户证书序号
    public static final String MERCHANT_SERIAL_NUMBER = "22F2E9D42AEB04742DF885F977AA97FF4798A632";
    //    public static final String MERCHANT_SERIAL_NUMBER = "78771AC71692D28417E8FEAD16A25F9BC5BAB82E";
    // appid
    public static final String APPID = "wxb1cd5bc64dbc5980";
    public static final String API_V3_KEY = "wangguanghui060912wangguanghui00";


    public static final String SCHEMA = "WECHATPAY2-SHA256-RSA2048";
    public static final String JSAPI_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    public static final String QUERY_TRANSACTION_ID_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/";

    public static final String WITHDRAW_URL = "https://api.mch.weixin.qq.com/v3/transfer/batches";
    public static final String CER_URL = "https://api.mch.weixin.qq.com/v3/certificates";
    public static final String CER_TEXT = "CER_TEXT";
    public static final int BEICHUAN_CHARACTER_ID = 30744;

    public static final String BAICHUAN_ROLE_USER = "user";
    public static final String BAICHUAN_MODEL = "Baichuan-NPC-Turbo";
    public static final double BAICHUAN_TEMPERATURE = 0.8;
    public static final int BAICHUAN_TOP_K = 10;
    public static final int BAICHUAN_MAX_TOKENS = 512;

    public static final String DEFAULT_AVATAR_URL = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
    public static final String DEFAULT_NICK_NAME = "微信用户";

    public static final String DEFAULT_ANSWER = "您好,暂时还没有这方面的答案";

    public static final String DAY_MSG_LIMIT = "DAY_MSG_LIMIT";

    public static final String AVATAR_URL_PREFIX = "https://7072-prod-8gnfzqw5a0ea2d6b-1323362704.tcb.qcloud.la/userinfo/";
    public static final String AVATAR_URL_SUFFIX = "/avatar.jpeg";

    public static final String GROWTH_CENTER_URL_PREFIX = "https://7072-prod-8gnfzqw5a0ea2d6b-1323362704.tcb.qcloud.la/growthcenter/";
    public static final String GROWTH_CENTER_URL_SUFFIX = ".mp3";


    public static final String DEFAULT_NULL_STRING = "";

    public static final String ORDER_DESCRIPTION = "订单内容";
    public static final String ORDER_PRICE = "订单价格";
    public static final String ORDER_NO = "订单编号";
    public static final String ORDER_TIME = "订单时间";
    public static final String WECHATPAY_SERIAL = "Wechatpay-Serial";

    public static final String ORGANIZATION = "ORGANIZATION";

    public static final List<String> LISTEN_STYLE_LIST = Arrays.asList(
            "温暖",
            "尊重",
            "真诚",
            "抱持",
            "理性",
            "和蔼",
            "治愈",
            "保密",
            "温柔",
            "涵容",
            "坚定",
            "思考",
            "专业",
            "有耐心",
            "亲和力",
            "友善",
            "用心",
            "细腻",
            "善解人意",
            "不评判",
            "耐心",
            "用心陪伴"
    );

    public static final List<String> EXPERT_AREAS_LIST = Arrays.asList(
            "人际关系",
            "自我成长",
            "亲子关系",
            "情绪困扰",
            "个人成长",
            "亲密关系",
            "情绪情感",
            "亲子教育",
            "抑郁焦虑",
            "婚姻情感",
            "原生家庭",
            "自我成长 ",
            "婚姻爱情",
            "职场困扰",
            "家庭教育",
            "创伤疗愈",
            "职场规划",
            "婚姻家庭"
    );

}
