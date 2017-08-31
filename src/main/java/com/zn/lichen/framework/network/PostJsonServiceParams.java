package com.zn.lichen.framework.network;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by lichen on 2017/1/3.
 */

public class PostJsonServiceParams extends ServiceParams {
    protected String jsonEntity;

    public PostJsonServiceParams(String url, Class responsType) {
        super(url, responsType);
    }

    public PostJsonServiceParams setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
        return this;
    }

    @Override
    public Request getRequest(String tag) {
        return new okhttp3.Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(RequestBody.create(MediaType.parse("application/json"), jsonEntity))
                .tag(tag)
                .build();
    }
}
