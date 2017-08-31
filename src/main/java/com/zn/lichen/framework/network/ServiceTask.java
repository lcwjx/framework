package com.zn.lichen.framework.network;

import com.zn.lichen.framework.task.BaseTask;
import com.zn.lichen.framework.task.TagConfig;

/**
 * Created by lichen on 2016/12/21.
 */

public class ServiceTask extends BaseTask {
    public ServiceParams serviceParams;
    public DataConfig dataConfig;
    private ServiceCallback callback;


    public ServiceTask(ServiceParams serviceParams) {
        this.serviceParams = serviceParams;
        tagConfig.primaryTag = serviceParams.serviceTag;
        dataConfig = new DataConfig();
    }

    public ServiceTask setUseVirtualData(boolean useVirtualData) {
        dataConfig.useVirtualData = useVirtualData;
        return this;
    }

    public TagConfig getTagConfig() {
        return tagConfig;
    }

    public void setTagConfig(TagConfig tagConfig) {
        this.tagConfig = tagConfig;
    }

    public ServiceParams getServiceParams() {
        return serviceParams;
    }

    public ServiceCallback getCallback() {
        return callback;
    }

    public ServiceTask setCallback(ServiceCallback callback) {
        this.callback = callback;
        return this;
    }
}
