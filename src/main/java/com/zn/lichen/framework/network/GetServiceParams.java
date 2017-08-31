package com.zn.lichen.framework.network;

import okhttp3.Headers;
import okhttp3.Request;

/**
 * Created by lichen on 2016/12/21.
 */

public class GetServiceParams<T> extends ServiceParams<T> {

    public GetServiceParams(String url, Class<T> responsType) {
        super(url, responsType);
    }

    @Override
    public Request getRequest(String tag) {
        return new okhttp3.Request.Builder()
                .url(getUrl()).headers(Headers.of(headers))
                .tag(tag)
                .build();
    }
}
