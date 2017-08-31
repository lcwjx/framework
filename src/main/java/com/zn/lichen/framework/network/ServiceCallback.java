package com.zn.lichen.framework.network;

import com.zn.lichen.framework.task.NetwrokTaskError;

/**
 * Created by lichen on 2016/12/21.
 */
public interface ServiceCallback {

    void onTaskStart(String serverTag);

    void onTaskSuccess(String serverTag);

    void onTaskFail(NetwrokTaskError error, String serverTag);
}
