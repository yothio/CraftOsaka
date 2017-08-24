package craftosaka.syukupili.ui.fragment;

import android.widget.EditText;
import android.widget.TextView;

import java.util.EventListener;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.PrefUtil;
import craftosaka.syukupili.util.Util;

/**
 * Created by yocchi on 2017/08/24.
 */

public class CreateChildAccountDialog extends BaseDialogFragment {
    EditText passEdit, nameEdit;
    CreateChildAccountDialog.MyCallback callback;

    String okButtonText = "作成";
    String cancelButtonText = "キャンセル";

    public void setCallback(CreateChildAccountDialog.MyCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.create_child_account_dialog;
    }

    @Override
    protected void initView() {
        super.initView();

        ((TextView) mDialog.findViewById(R.id.ok_button)).setText(okButtonText);
        ((TextView) mDialog.findViewById(R.id.cancel_button)).setText(cancelButtonText);
        passEdit = mDialog.findViewById(R.id.child_pass_text);
        nameEdit = mDialog.findViewById(R.id.child_name_text);
    }

    @Override
    protected boolean pressOkButton() {
        boolean bool = true;

        if (Util.isEmptyEditText(passEdit) & Util.isEmptyEditText(nameEdit)) {
            bool = false;
        } else if (!Util.checkEditTextLength(passEdit)) {
            bool = false;
        } else {
//            PrefUtil.setParent(passEdit.getText().toString());
            callback.positive(bool, nameEdit.getText().toString(), passEdit.getText().toString());
        }
        return bool;
    }

    @Override
    protected void pressNegativeButton() {
        callback.negative();
    }


    public interface MyCallback extends EventListener {
        void positive(Boolean bool, String name, String pass);

        void negative();
    }
}
