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
public class CreateParentAccount extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_acount_layout);
    }

    //アカウント作成ボタンクリック
    public void create_button_click(View view) {

        MaterialEditText nameText = (MaterialEditText) findViewById(R.id.nametext);
        MaterialEditText passText = (MaterialEditText) findViewById(R.id.passtext);

        Account account = new Account(this);

        if (!account.createAcount(nameText.getText().toString(), passText.getText().toString())) {

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
