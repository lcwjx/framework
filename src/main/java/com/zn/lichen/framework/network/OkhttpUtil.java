package com.zn.lichen.framework.network;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by lichen on 2017/1/3.
 */

public class OkhttpUtil {

    private static OkHttpClient sOkHttpClient;

    public static synchronized void init(SSLSocketFactory factory) {
        sOkHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(factory)//设置https证书
                .readTimeout(30, TimeUnit.SECONDS)//设置read超时时间
                .build();

    }

    public static OkHttpClient getOkHttpClient() {
        return sOkHttpClient;
    }

    /**
     * 用于https图片加载，信任所有证书
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static OkHttpClient getUnsafeOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {

        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };

        final TrustManager[] trustAllCerts = new TrustManager[]{trustManager};
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        final SSLSocketFactory sslSocketFactory = sslContext
                .getSocketFactory();


        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        return client;
    }
}
