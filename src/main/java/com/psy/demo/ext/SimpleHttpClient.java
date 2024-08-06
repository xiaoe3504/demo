package com.psy.demo.ext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.psy.demo.global.BaseException;
import com.psy.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SimpleHttpClient {
    private static final int CONNECT_TIMEOUT_MS = 2000;
    private static final int CONNECT_REQ_TIMEOUT_MS = 1000;
    private static final int SOCKET_TIMEOUT_MS = 5000;
    private static final int MAX_CONN_TOTAL = 1000;
    private static final int MAX_CONN_PER_ROUTE = 200;
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private CloseableHttpClient httpClient;

    @PostConstruct
    public void init() {
        RequestConfig conf = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT_MS)
                .setConnectionRequestTimeout(CONNECT_REQ_TIMEOUT_MS)
                .setSocketTimeout(SOCKET_TIMEOUT_MS)
                .setCircularRedirectsAllowed(true)
                .build();

        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(conf)
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();
    }

    @PreDestroy
    public void destroy() {
        IOUtils.closeQuietly(httpClient);
    }


    public ResponseReader doDelete(String url, int id, Map<String, String> mapHeader) {
        Validate.notBlank(url, "No target URL provided");
        HttpDelete httpDelete = new HttpDelete(url + id);
        if (mapHeader != null && !mapHeader.isEmpty()) {
            mapHeader.forEach(httpDelete::setHeader);
        }
        return execute(httpDelete);
    }


    public ResponseReader doGet(String url) {
        Validate.notBlank(url, "No target URL provided");
        return execute(new HttpGet(url));
    }


    public ResponseReader doGet(String baseUrl, List<? extends NameValuePair> params, Map<String, String> mapHeader) {
        Validate.notBlank(baseUrl, "No base URL provided");
        String url = StringUtil.toQueryUrl(baseUrl, params);
        HttpGet httpGet = new HttpGet(url);
        if (mapHeader != null && !mapHeader.isEmpty()) {
            mapHeader.forEach(httpGet::setHeader);
        }
        return execute(httpGet);
    }

    public ResponseReader doGet(String url, Object obj, Map<String, String> mapHeader) {
        Validate.notBlank(url, "No base URL provided");
        HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(url);
        if (mapHeader != null && !mapHeader.isEmpty()) {
            mapHeader.forEach(httpGetWithEntity::setHeader);
        }
        String content = obj instanceof String ? (String) obj : JSONObject.toJSONString(obj);
        httpGetWithEntity.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON.getCharset()));

        return execute(httpGetWithEntity);
    }


    public ResponseReader doPost(String url, String content,
                                 ContentType contentType,
                                 Map<String, String> mapHeader) {
        Validate.notBlank(url, "No target URL provided");
        Validate.notBlank(content, "No content provided");
        contentType = ObjectUtils.defaultIfNull(contentType, ContentType.TEXT_PLAIN);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType.getMimeType());
        if (mapHeader != null && !mapHeader.isEmpty()) {
            mapHeader.forEach(httpPost::setHeader);
        }
        httpPost.setEntity(new StringEntity(content, contentType.getCharset()));

        return execute(httpPost);
    }

    public ResponseReader doPost(String url, Object obj, Map<String, String> mapHeader) {
        String content = obj instanceof String ? (String) obj : JSONObject.toJSONString(obj);
        return doPost(url, content, ContentType.APPLICATION_JSON, mapHeader);
    }

    public ResponseReader doPost(String url, List<? extends NameValuePair> formData) {
        Validate.notBlank(url, "No target URL provided");

        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(formData)) {
            httpPost.setEntity(new UrlEncodedFormEntity(formData, DEFAULT_CHARSET));
        }
        return execute(httpPost);
    }


    public ResponseReader execute(HttpRequestBase request) {
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            log.error("ResponseReader execute:" + e.getMessage(), e);
            throw new BaseException("execute error");
        }

        ResponseReader responseReader = new ResponseReader(response);
        int statusCode = response.getStatusLine().getStatusCode();
        if (is2xxSuccess(statusCode)) {
            return responseReader;
        } else {
            String resStr = responseReader.readString();
            String msg = String.format("Status=%s, entity=%s", response.getStatusLine(), resStr);
            log.error("ResponseReader execute:" + msg);
            throw new BaseException("ResponseReader execute error" + resStr);
        }
    }

    private static boolean is2xxSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }


    public static class ResponseReader {
        private final CloseableHttpResponse response;

        public ResponseReader(CloseableHttpResponse response) {
            this.response = response;
        }

        public String readString() {
            try {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, DEFAULT_CHARSET) : null;
            } catch (IOException e) {
                log.error("readString error:" + e.getMessage(), e);
                throw new BaseException("readString error");
            } finally {
                IOUtils.closeQuietly(response);
            }
        }

        public byte[] readByteArray() {
            try {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toByteArray(entity) : null;
            } catch (IOException e) {
                log.error("readByteArray error:" + e.getMessage(), e);
                throw new BaseException("readByteArray error");
            } finally {
                IOUtils.closeQuietly(response);
            }
        }

        public <T> T readObject(Class<T> cls) {
            String str = readString();
            try {
                return JSON.parseObject(str, cls);
            } catch (JSONException e) {
                log.error("readObject error:" + e.getMessage(), e);
                throw new BaseException("readObject error");
            }
        }

        public <T> T readObject(Type type) {
            String str = readString();
            try {
                return JSON.parseObject(str, type);
            } catch (JSONException e) {
                log.error("readObject error:" + e.getMessage(), e);
                throw new BaseException("readObject error");

            }
        }

        public <T> T readObject(TypeReference<T> typeRef) {
            String str = readString();
            try {
                return JSON.parseObject(str, typeRef);
            } catch (JSONException e) {
                log.error("readObject error:" + e.getMessage(), e);
                throw new BaseException("readObject error");
            }
        }
    }

}
