package com.zn.lichen.framework.network;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

/**
 * Created by lichen on 2017/1/3.
 */

public class PostServiceParams extends ServiceParams {

    protected HashMap<String, String> postMap;

    public PostServiceParams(String url, Class responsType) {
        super(url, responsType);
    }

    public PostServiceParams setPostMap(HashMap<String, String> postMap) {
        this.postMap = postMap;
        return this;
    }

    @Override
    public Request getRequest(String tag) {

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> param : postMap.entrySet()) {
            builder.add(param.getKey(), param.getValue());
        }

        return new okhttp3.Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(builder.build())
                .tag(tag)
                .build();
    }
}
