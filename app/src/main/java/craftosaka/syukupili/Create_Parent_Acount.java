package craftosaka.syukupili;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.ui.activity.BaseActivity;

/**
 * Created by Fukkun on 2017/08/17.
 */

//親のアカウント作成画面
public class Create_Parent_Acount extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_acount_layout);
    }

    //アカウント作成ボタンクリック
    public void create_button_click(View view) {

        MaterialEditText nametext = (MaterialEditText) findViewById(R.id.nametext);
        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.passtext);

        Acount acount = new Acount(this);

        if (!acount.createAcount(nametext.getText().toString(), passtext.getText().toString())) {

            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("アカウント作成に失敗しました")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("アカウント作成に成功しました")
                .setPositiveButton("OK", null)
                .show();
    }
}
