package craftosaka.syukupili.ui.fragment;

import android.widget.EditText;

import java.util.EventListener;

import craftosaka.syukupili.R;

/**
 * Created by yocchi on 2017/08/23.
 */

public class PointItemCreateDialog extends BaseDialogFragment {

    private MyCallback callback = null;
    String str = "title";
    int point = 0;
    private EditText editText;

    @Override
    protected void initView() {
        super.initView();

        editText = (EditText)mDialog.findViewById(R.id.body_edit_text);

    }

    public void setCallback(MyCallback callback){
        this.callback = callback;
    }

    @Override
    protected int setContentViewId() {
        return R.layout.create_point_item_dialog;
    }

    @Override
    protected void pressOkButton() {
        point = Integer.parseInt(editText.getText().toString());
        callback.positive(str,point);
    }

    @Override
    protected void pressNegativeButton() {
        callback.negative();
    }


    public interface MyCallback extends EventListener{
        void positive(String itemName,int point);
        void negative();
    }
}
