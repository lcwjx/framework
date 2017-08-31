package com.zn.lichen.framework.network;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zn.lichen.framework.model.response.BaseResponse;

/**
 * Created by lichen on 2016/12/21.
 */
public class BusinessResult {
    public boolean isSuccess = true;
    public String errorString = "";

    public BusinessResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


    public BusinessResult(String data) {
        if (!TextUtils.isEmpty(data)) {
            try {
                BaseResponse response = new Gson().fromJson(data, BaseResponse.class);
                if (response == null) {
                    isSuccess = false;
                    return;
                }
                if (response.code == 200 || response.code == 10000) {
                    errorString = "";
                } else {
                    isSuccess = false;
                    errorString = response.message;
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public BusinessResult(BaseResponse response) {
        if (response == null) {
            isSuccess = false;
            return;
        }
        if (response.code == 200 || response.code == 10000) {
            errorString = "";
        } else {
            isSuccess = false;
            errorString = response.message;
        }

    }
}
