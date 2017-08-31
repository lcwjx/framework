package com.zn.lichen.framework.task;

/**
 * Created by lichen on 2016/12/21.
 */

public class BaseTask {
    protected TagConfig tagConfig;
    protected UIConfig uiConfig;

    public BaseTask() {
        tagConfig = new TagConfig();
        uiConfig = new UIConfig();
    }

    public String getTag() {
        return tagConfig.getTag();
    }

    public BaseTask setShowProcess(boolean showProcess) {
        uiConfig.isShowProcess = showProcess;
        return this;
    }

    public BaseTask setCancelable(boolean cancelable) {
        uiConfig.isCancelable = cancelable;
        return this;
    }

    public BaseTask setProgressContent(String progressContent) {
        uiConfig.progressContent = progressContent;
        return this;
    }

    public BaseTask setShowErrorToast(boolean showErrorToast) {
        uiConfig.showErrorToast = showErrorToast;
        return this;
    }

    public TagConfig getTagConfig() {
        return tagConfig;
    }

    public void setTagConfig(TagConfig tagConfig) {
        this.tagConfig = tagConfig;
    }

    public UIConfig getUiConfig() {
        return uiConfig;
    }

    public void setUiConfig(UIConfig uiConfig) {
        this.uiConfig = uiConfig;
    }

}
