package com.psy.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.psy.demo.global.BaseException;
import com.psy.demo.vo.res.CozeBootRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static org.apache.http.HttpHeaders.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Slf4j
public class HttpUtils {

    public static <T> T dealHttpGet(CloseableHttpClient httpClient, String url, String token, Class<T> clazz) {
        HttpGet httpGet;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("httputils dealGet error");
        }
        httpGet.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpGet.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());
        //拼装http头的Authorization内容
        httpGet.addHeader(AUTHORIZATION, token);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            return getTFromResponse(clazz, response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("httputils dealGet error");
        } finally {
            closeResponse(response);
        }
    }

    private static void closeResponse(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("dealGet error");
        }
    }

    @Nullable
    private static <T> T getTFromResponse(Class<T> clazz, CloseableHttpResponse response) {
        if (SC_OK != response.getStatusLine().getStatusCode()) {
            log.error("httputils dealHttp fail...");
            return null;
        } else {
            String resStr;
            try {
                resStr = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                throw new BaseException("httputils EntityUtils.toString error");
            }
            if (!StringUtils.isNotEmpty(resStr)) {
                return null;
            }
            return JSONObject.parseObject(resStr, clazz);
        }
    }

    public static <T> T dealHttpPost(CloseableHttpClient httpClient, String url, String token,
                                     String body,
                                     Class<T> clazz) {
        HttpPost httpPost = genHttpPost(url, token, body);
        //拼装http头的Authorization内容
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            return getTFromResponse(clazz, response);
        } catch (IOException e) {
            throw new BaseException("httpUtils dealHttpPost error");
        } finally {
            closeResponse(response);
        }
    }

    public static CozeBootRes dealCozeHttpPostStream(CloseableHttpClient httpClient, String url, String token,
                                                     String body) {
        HttpPost httpPost = genHttpPost(url, token, body);
        //拼装http头的Authorization内容
        CloseableHttpResponse response = null;
        StringBuilder sb = new StringBuilder();
        try {
            response = httpClient.execute(httpPost);
            // Get the response entity
            HttpEntity entity = response.getEntity();
            // If the entity is not null, process the response stream
            if (entity != null) {
                try (InputStream inputStream = entity.getContent();
                     BufferedReader reader = new BufferedReader
                             (new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    String line;
                    boolean isComplected = false;
                    while ((line = reader.readLine()) != null) {
                        // Process each line of the response stream
                        if ("event:conversation.message.completed".equals(line)) {
                            isComplected = true;
                            continue;
                        }
                        if (isComplected) {
                            if (line.contains("\"type\":\"answer\"")) {
                                sb.append(line);
                                break;
                            } else {
                                isComplected = false;
                            }
                        }
                        log.info(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new BaseException("httpUtils dealHttpPost error");
        } finally {
            closeResponse(response);
        }
        String s = sb.toString().substring(5);
        return JSONObject.parseObject(s, CozeBootRes.class);
    }

    @NotNull
    private static HttpPost genHttpPost(String url, String token, String body) {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(ACCEPT, APPLICATION_JSON.toString());
        httpPost.addHeader(CONTENT_TYPE, APPLICATION_JSON.toString());

        if (StringUtils.isNotEmpty(token)) {
            httpPost.addHeader(AUTHORIZATION, token);
        }
        StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        return httpPost;
    }

    public static void main(String[] args) {
        String s = "data:{\"id\":\"7461082163966902284\",\"conversation_id\":\"7461082131364626486\",\"bot_id\":\"7457857320757395493\",\"role\":\"assistant\",\"type\":\"answer\",\"content\":\"不太理解你说的“士大夫撒发”是什么意思呢。你可以解释得更详细一点吗？这是一种特殊的情绪表达，还是有其他特殊含义的词汇呢？\",\"content_type\":\"text\",\"chat_id\":\"7461082148859150347\",\"section_id\":\"7461082131364626486\",\"created_at\":1737168566}";
        s = s.substring(5);
        CozeBootRes cozeBootRes = JSONObject.parseObject(s, CozeBootRes.class);
        String chatId = cozeBootRes.getChat_id();
        String content = cozeBootRes.getContent();
        System.out.println(s);
    }

}
