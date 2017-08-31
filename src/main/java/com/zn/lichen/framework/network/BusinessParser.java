package com.zn.lichen.framework.network;

/**
 * Created by lichen on 2016/12/21.
 */
public interface BusinessParser<T> {

    /**
     * 解析返回的response
     *
     * @param t 返回的数据
     * @return
     */
    BusinessResult parseData(T t);
}
