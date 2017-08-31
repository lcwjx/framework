package com.zn.lichen.framework.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zn.lichen.framework.constants.FrameworkConstants;
import com.zn.lichen.framework.interfaces.MarkAble;
import com.zn.lichen.framework.manager.LocalBroadcast;
import com.zn.lichen.framework.model.viewmodel.BaseViewModel;

import java.io.Serializable;

/**
 * Created by lichen on 2016/12/20.
 */

public abstract class BaseFragment extends Fragment implements MarkAble {

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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mViewModel = (BaseViewModel) savedInstanceState.getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        } else if (getArguments() != null) {
            mViewModel = (BaseViewModel) getArguments().getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mMyBroadCastReceiver != null) {
            LocalBroadcast.unregisterLocalReceiver(mMyBroadCastReceiver);
        }
        mMyBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcast.registerLocalReceiver(mMyBroadCastReceiver, listReceiveActions());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LocalBroadcast.unregisterLocalReceiver(mMyBroadCastReceiver);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mViewModel != null) {
            outState.putSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, mViewModel);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * 列出页面需要接收的广播类型
     *
     * @return
     */
    protected abstract String[] listReceiveActions();
}
