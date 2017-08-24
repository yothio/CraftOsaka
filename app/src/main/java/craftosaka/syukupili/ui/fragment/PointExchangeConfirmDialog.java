package craftosaka.syukupili.ui.fragment;

import android.widget.TextView;

import java.util.EventListener;

import craftosaka.syukupili.R;

/**
 * Created by yoshiki on 2017/08/24.
 */

public class PointExchangeConfirmDialog extends BaseDialogFragment {
    MyCallback callback;
    TextView itemText;
    String name;
    int point;
    int nowPoint;


    public void setCallback(MyCallback callback) {
        this.callback = callback;
    }

    public void setNeedInfo(String name, int point, int nowPoint) {
        this.name = name;
        this.point = point;
        this.nowPoint = nowPoint;
    }

    public interface MyCallback extends EventListener {
        void positive(Boolean bool);

        void negative();
    }

    @Override
    protected void initView() {
        super.initView();
        itemText = mDialog.findViewById(R.id.confirm_item_label);
        itemText.setText(name + ":" + point + "\n" +  "いま:" +nowPoint + "→" + "のこり:" +(nowPoint - point));
    }

    @Override
    protected int setContentViewId() {
        return R.layout.point_confirm_dialog;
    }

    @Override
    protected boolean pressOkButton() {
        Boolean bool = false;

        if(nowPoint >= point){
            bool = true;
        }

        callback.positive(bool);
        return true;
    }

    @Override
    protected void pressNegativeButton() {
        callback.negative();
    }
}
