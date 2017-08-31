package com.zn.lichen.framework.constants;

/**
 * 常量类
 * Created by lichen on 2016/12/20.
 */

public interface FrameworkConstants {

    //intent中取值所用的标记
    String MESSAGE_CODE = "message_code";
    String MESSAGE_CONTENT = "message_content";
    String MESSAGE_DATA = "message_data";
    //事件结果的状态类型
    int MESSAGE_SUCCESS = 0;
    int MESSAGE_FAIL = 1;
    int MESSAGE_CANCEL = 2;
    //页面数据跳转赋值
    String PAGE_BASE_EXCHANGEMODEL = "BaseExchangeModel";
}
