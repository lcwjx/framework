package com.zn.lichen.framework.widget;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zn.lichen.framework.R;
import com.zn.lichen.framework.manager.FragmentExchangeManager;
import com.zn.lichen.framework.model.entity.DialogExchangeModel;
import com.zn.lichen.framework.network.ServiceCallback;
import com.zn.lichen.framework.task.NetworkExcuter;
import com.zn.lichen.framework.task.NetwrokTaskError;

/**
 * 实现显示加载进度框效果。
 */
public class ProcessDialogFragment extends BaseDialogFragment implements ServiceCallback {

    private TextView mDlgContent;

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return mBackStackId;
    }

    public static ProcessDialogFragment getInstance(Bundle bundle) {
        ProcessDialogFragment baseDialogFragment = new ProcessDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppProgressDialogTheme);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel dialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (dialogExchangeModel != null) {
                mDialogTag = dialogExchangeModel.getTag();
                mContentTxt = dialogExchangeModel.getDialogContext();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.process_load_data_layout, container, false);
        SimpleDraweeView img = (SimpleDraweeView) layoutView.findViewById(R.id.image);
        mDlgContent = (TextView) layoutView.findViewById(R.id.tip);
        if (!TextUtils.isEmpty(mContentTxt)) {
            mDlgContent.setText(mContentTxt);
        }


        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.drawable.gif_loading))
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();

        img.setController(draweeController);

        return layoutView;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void dismissSelf() {
        FragmentExchangeManager.removeFragment(getFragmentManager(), this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        NetworkExcuter.getInstance().cancelRequest(mDialogTag);
    }

    public void setContentText(String content) {
        mDlgContent.setText(content);
    }

    @Override
    public void onTaskStart(String serverTag) {

    }

    @Override
    public void onTaskSuccess(String serverTag) {
        dismissSelf();
    }

    @Override
    public void onTaskFail(NetwrokTaskError error, String serverTag) {
        dismissSelf();
    }
}
