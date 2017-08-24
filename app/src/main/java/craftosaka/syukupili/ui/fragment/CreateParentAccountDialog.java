package craftosaka.syukupili.ui.fragment;

import android.widget.EditText;
import android.widget.TextView;

import java.util.EventListener;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.PrefUtil;
import craftosaka.syukupili.util.Util;

/**
 * Created by yocchi on 2017/08/23.
 */

public class CreateParentAccountDialog extends BaseDialogFragment {
    EditText passEdit, againPassEdit;
    MyCallback callback;
    String okButtonText = "作成";
    String cancelButtonText = "キャンセル";

    public void setCallback(MyCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.create_parent_account_dialog_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        ((TextView)mDialog.findViewById(R.id.ok_button)).setText(okButtonText);
        ((TextView)mDialog.findViewById(R.id.cancel_button)).setText(cancelButtonText);
        passEdit = mDialog.findViewById(R.id.pass_text);
        againPassEdit = mDialog.findViewById(R.id.again_pass_text);
    }

    @Override
    protected boolean pressOkButton() {
        boolean bool = true;

        if (Util.isEmptyEditText(passEdit) & Util.isEmptyEditText(againPassEdit)) {
            bool = false;
        }else if(!Util.checkEditTextLength(passEdit)){
            bool = false;
        } else if (!Util.isEqualsEditText(passEdit, againPassEdit)) {
            bool = false;
        } else {
            PrefUtil.setParent(passEdit.getText().toString());
            callback.positive(bool);
        }
        return bool;
    }

    @Override
    protected void pressNegativeButton() {
        callback.negative();
    }


    public interface MyCallback extends EventListener {
        void positive(Boolean bool);

        void negative();
    }
}
