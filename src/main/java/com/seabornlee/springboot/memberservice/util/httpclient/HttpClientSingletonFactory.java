package com.seabornlee.springboot.memberservice.util.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class HttpClientSingletonFactory {


    private int connectionTimeToLive = 20;
    private int maxTotalConnection = 50;
    private int maxPerRoute = 20;
    private int socketTimeout = 20000;
    private int connectionTimeout = 20000;
    private int connectionRequestTimeout = 20000;

    public int getConnectionTimeToLive() {
        return connectionTimeToLive;
    }

    public HttpClientSingletonFactory setConnectionTimeToLive(int connectionTimeToLive) {
        this.connectionTimeToLive = connectionTimeToLive;
        return this;
    }

    public int getMaxTotalConnection() {
        return maxTotalConnection;
    }

    public HttpClientSingletonFactory setMaxTotalConnection(int maxTotalConnection) {
        this.maxTotalConnection = maxTotalConnection;
        return this;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public HttpClientSingletonFactory setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
        return this;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public HttpClientSingletonFactory setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public HttpClientSingletonFactory setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public HttpClientSingletonFactory setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
        return this;
    }

    private static class HttpClientHolder {

        private static CloseableHttpClient client;
        private static HttpClientBuilder httpClientBuilder;
        private static HttpClientSingletonFactory factory;
        private static CloseableHttpClient httpsClient;

        public static CloseableHttpClient getClient() {
            if (null == client) {
                client = getHttpClientBuilder().build();
            }
            return client;
        }

        public static CloseableHttpClient getHttpsClient() {
            if (null == httpsClient) {
                httpsClient = getHttpClientBuilder().setSSLSocketFactory(createSSLConnSocketFactory()).build();
            }
            return httpsClient;
        }

        public static HttpClientBuilder getHttpClientBuilder() {
            if (null == httpClientBuilder) {
                httpClientBuilder = getFactory().getHttpClientBuilder();
            }
            return httpClientBuilder;
        }

        public static HttpClientSingletonFactory getFactory() {

            if (null == factory) {
                factory = new HttpClientSingletonFactory();
            }

            return factory;
        }
    }


    public HttpClientBuilder getHttpClientBuilder() {

        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(connectionTimeToLive, TimeUnit.SECONDS);
        poolingConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
        poolingConnectionManager.setMaxTotal(maxTotalConnection);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).build();

        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

        BasicCookieStore cookieStore = new BasicCookieStore();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(poolingConnectionManager)
                .setDefaultRequestConfig(requestConfig).setRedirectStrategy(redirectStrategy)
                .setDefaultCookieStore(cookieStore);
        return httpClientBuilder;
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClientHolder.getClient();
    }

    public static CloseableHttpClient getHttpsClient() {
        return HttpClientHolder.getHttpsClient();
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}