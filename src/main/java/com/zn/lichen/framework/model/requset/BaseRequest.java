package com.zn.lichen.framework.model.requset;

import com.zn.lichen.framework.interfaces.UnProguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lichen on 2017/1/3.
 */

public class BaseRequest implements UnProguard {
    private TreeMap<String, String> parseFromObjectAsClass(Class<?> clz) {
        TreeMap<String, String> params = new TreeMap<>();
        if (BaseRequest.class.isAssignableFrom(clz)) {
            params.putAll(parseFromObjectAsClass(clz.getSuperclass()));
            Field[] fs = clz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true);
                String val = null;
                try {
                    val = String.valueOf(f.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!"null".equals(val)) {
                    params.put(f.getName(), val);
                }
            }
        }
        return params;
    }

    public String getReqUrl() {
        String paramUrl = "";
        StringBuilder urlBuilder = new StringBuilder();
        Iterator<?> iter = parseFromObjectAsClass(this.getClass()).entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            try {
                urlBuilder.append(
                        "&" + key + "=" + URLEncoder.encode(String.valueOf(val), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                urlBuilder.append("&" + key + "=" + val);
            }
        }

        if (!urlBuilder.toString().equals("")) {
            paramUrl = urlBuilder.toString().replaceFirst("&", "?");

        }
        return paramUrl;
    }

    public Map<String, String> getPostMap() {
        return parseFromObjectAsClass(this.getClass());
    }
}
