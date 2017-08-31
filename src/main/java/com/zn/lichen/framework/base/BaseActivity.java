package com.zn.lichen.framework.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zn.lichen.framework.constants.FrameworkConstants;
import com.zn.lichen.framework.interfaces.MarkAble;
import com.zn.lichen.framework.manager.LocalBroadcast;
import com.zn.lichen.framework.model.viewmodel.BaseViewModel;

import java.io.Serializable;

/**
 * Created by lichen on 2016/12/20.
 */

public abstract class BaseActivity extends FragmentActivity implements MarkAble {

    private MyBroadCastReceiver mMyBroadCastReceiver;
    private BaseViewModel mViewModel;

    @Override
    public String getInstanceTag() {
        return this.getClass().getSimpleName() + this.hashCode();
    }

    protected class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int code = intent.getIntExtra(FrameworkConstants.MESSAGE_CODE, FrameworkConstants.MESSAGE_SUCCESS);
            String content = intent.getStringExtra(FrameworkConstants.MESSAGE_CONTENT);
            Serializable data = intent.getSerializableExtra(FrameworkConstants.MESSAGE_DATA);
            onMessageReceive(action, code, content, data);
        }
    }

    /**
     * broadcast 响应方法
     *
     * @param action
     * @param code
     * @param message
     * @param data
     */
    protected void onMessageReceive(String action, int code, String message, Serializable data) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mViewModel = (BaseViewModel) savedInstanceState.getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            mViewModel = (BaseViewModel) getIntent().getExtras().getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        }
        if (mMyBroadCastReceiver != null) {
            LocalBroadcast.unregisterLocalReceiver(mMyBroadCastReceiver);
        }
        mMyBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcast.registerLocalReceiver(mMyBroadCastReceiver, listReceiveActions());
    }

    /**
     * 列出页面需要接收的广播类型
     *
     * @return
     */
    protected abstract String[] listReceiveActions();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcast.unregisterLocalReceiver(mMyBroadCastReceiver);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mViewModel != null) {
            outState.putSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, mViewModel);
        }
        super.onSaveInstanceState(outState);
    }
}
