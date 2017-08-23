package craftosaka.syukupili.ui.fragment;

import android.widget.EditText;

import java.util.EventListener;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.Util;

/**
 * Created by yocchi on 2017/08/23.
 */

public class PointItemCreateDialog extends BaseDialogFragment {

    private MyCallback callback = null;
    String str = "title";
    Integer point = 0;
    private EditText itemNameEditText, itemPointEditText;

    @Override
    protected void initView() {
        super.initView();

        itemNameEditText = mDialog.findViewById(R.id.body_edit_text);
        itemPointEditText = mDialog.findViewById(R.id.point_edit_text);
    }

    public void setCallback(MyCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.create_point_item_dialog;
    }

    //ダイアログにてokボタンを押した時の処理
    @Override
    protected boolean pressOkButton() {


        boolean bool = true;
        if (Util.isEmptyEditText(itemNameEditText)) {
            bool = false;
        } else {
            str = itemNameEditText.getText().toString();
        }
        if (Util.isEmptyEditText(itemPointEditText)) {
            bool = false;
        } else {
            point = Integer.parseInt(itemPointEditText.getText().toString());
        }
        if (bool) {
        }
        callback.positive(str, point, bool);

        return bool;
    }

    @Override
    protected void pressNegativeButton() {
        callback.negative();
    }


    public interface MyCallback extends EventListener {
        void positive(String itemName, int point, Boolean bool);

        void negative();
    }
}
