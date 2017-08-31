package com.zn.lichen.framework.interfaces;

/**
 * Created by lichen on 2016/12/21.
 * <p>
 * app与framework之间通信的接口
 */

public interface FrameworkSupport {

    /**
     * 账号在别处登录
     */
    void onSessionInVaild();
}
