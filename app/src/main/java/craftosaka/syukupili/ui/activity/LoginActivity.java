package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.account.Account;
import craftosaka.syukupili.util.account.CreateAccount;

/**
 * Created by Fukkun on 2017/08/17.
 */

//子供のログイン画面
public class LoginActivity extends BaseActivity {
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        account = new Account(this);
    }

    //ログインボタンクリック
    public void loginbutton_click(View view) {

        String name = "";
        String pass = "";
       MaterialEditText nametext = (MaterialEditText) findViewById(R.id.nametext);
        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.pass_text);
        name = nametext.getText().toString();
        pass = passtext.getText().toString();

        if (!account.login(name,pass)) {
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

    public void create_Acount_Button_Click(View view){

        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivity(intent);

    }

}
