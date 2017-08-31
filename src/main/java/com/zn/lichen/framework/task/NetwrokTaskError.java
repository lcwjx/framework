package com.zn.lichen.framework.task;

import com.zn.lichen.framework.network.BusinessResult;

/**
 * Created by lichen on 2017/1/3.
 */
public class NetwrokTaskError {
    public interface TaskCodeList {
        int ServerError = 1000;
        int TimeoutError = 1001;
        int NetworkError = 1002;
        int BusinessError = 1003;
    }

    public String errorString;
    public int errorCode;


    public NetwrokTaskError(BusinessResult result) {
        this.errorCode = TaskCodeList.BusinessError;
        this.errorString = result.errorString;
    }

    public NetwrokTaskError(int errorCode, String errorString) {
        this.errorCode = errorCode;
        this.errorString = errorString;
    }

    @Override
    public String toString() {
        return "errorCode:" + errorCode + ",errString:" + errorString;
    }
}
