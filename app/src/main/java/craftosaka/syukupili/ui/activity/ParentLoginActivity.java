package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.account.Account;
import craftosaka.syukupili.util.account.CreateParentAccount;

/**
 * Created by Fukkun on 2017/08/17.
 */


//親のログイン画面
public class ParentLoginActivity extends BaseActivity {

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login_layout);
        account = new Account(this);
    }

    //ログインボタンクリック
    public void loginButton_click(View view) {
        String pass = "";
        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.pass_text);
        pass = passtext.getText().toString();
        //ログイン
        if (!account.login(pass)) {
            //ログインに失敗
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("ログインに失敗しました")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("ログインに成功しました")
                .setPositiveButton("OK", null)
                .show();
    }

    public void create_acount_Button_Click(View view){
        Intent intent = new Intent(getApplicationContext(),CreateParentAccount.class);
        startActivity(intent);

    }
}
