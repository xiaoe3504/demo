package com.psy.demo.utils;

import org.apache.http.client.methods.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author xy-peng
 */
public interface Credentials {

    String getSchema();

    String getToken(HttpRequestWrapper request) throws IOException;

}
