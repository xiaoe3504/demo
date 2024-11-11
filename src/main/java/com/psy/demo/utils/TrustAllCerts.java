package com.psy.demo.utils;

import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class TrustAllCerts implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (chain == null) {
            throw new IllegalArgumentException("  Check Server x509Certificates is null");
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public static SSLSocketFactory createSSLSocketFactoryNew(InputStream caInput) throws Exception {
        // 创建一个SSLContext，并使用TrustManagerFactory初始化它
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, getTrustManagers(caInput), null);
        // 返回SSLContext的SSLSocketFactory
        return sslContext.getSocketFactory();
    }


    public static TrustManager[] getTrustManagers(InputStream caInput) throws Exception {
        // 上面的getSslSocketFactory方法已经包含了创建TrustManagers的代码
        // 这里只是为了演示如何单独获取TrustManagers
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        InputStream caInputLocal = caInput;
        Certificate ca;
        try {
            ca = certFactory.generateCertificate(caInputLocal);
        } finally {
            caInputLocal.close();
        }

        keyStore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        return tmf.getTrustManagers();
    }


    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


}

