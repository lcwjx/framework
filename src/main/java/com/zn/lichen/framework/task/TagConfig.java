package com.zn.lichen.framework.task;

/**
 * Created by lichen on 2016/12/21.
 */
public class TagConfig {
    /**
     * 上下文
     */
    public String contextTag;
    /**
     * 主要标识,由具体内容决定
     */
    public String primaryTag = "default";
    /**
     * 时间戳
     */
    public long timeTag;

    public TagConfig() {
        timeTag = System.currentTimeMillis();
    }


    public String getTag() {
        return contextTag + "-" + primaryTag + "-" + timeTag;
    }

}
