package craftosaka.syukupili.util.account;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.activity.BaseActivity;

/**
 * Created by Fukkun on 2017/08/17.
 */

//子供のアカウント作成画面
public class CreateAccount extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_acount_layout);
    }

    //アカウント作成ボタンクリック
    public void create_button_click(View view) {

        MaterialEditText nametext = (MaterialEditText) findViewById(R.id.nametext);
        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.pass_text);
        MaterialEditText passtext2 = (MaterialEditText) findViewById(R.id.passtext2);

        //パスワードが正しく入力されているか
        if (!passtext.getText().toString().equals(passtext2.getText().toString())) {

            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("パスワードを正しく入力してください")
                    .setPositiveButton("OK", null)
                    .show();
            return;

        }

        Account account = new Account(this);

//アカウント作成
        if (!account.createAcount(nametext.getText().toString(), passtext.getText().toString())) {

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
