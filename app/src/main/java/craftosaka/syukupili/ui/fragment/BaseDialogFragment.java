package craftosaka.syukupili.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.EventListener;

import craftosaka.syukupili.R;
import lombok.Getter;

/**
 * Created by yocchi on 2017/08/23.
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {
    protected static Callback callback = null;
    @Getter
    protected Dialog mDialog;
    protected View mOkBtn;
    protected View mCancelBtn;

    public interface Callback extends EventListener {
        void positive();
        void negative();
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity());
        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); // タイトル非表示
        mDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN); // フルスクリーン
        mDialog.setContentView(setContentViewId());
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 背景を透明にする

        initView();

        return mDialog;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ok_button:
                if (pressOkButton()){
                    dismiss();
                }
                break;
            case R.id.cancel_button:
                pressNegativeButton();
                dismiss();
                break;
        }
    }

    @LayoutRes
    abstract protected int setContentViewId();

    protected void initView(){
        mOkBtn =  mDialog.findViewById(R.id.ok_button);
        mCancelBtn = mDialog.findViewById(R.id.cancel_button);
        mOkBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);;
    };


    //trueの時はダイアログを閉じる
    //未入力検知よう
    abstract protected boolean pressOkButton();

    abstract protected void pressNegativeButton();

}