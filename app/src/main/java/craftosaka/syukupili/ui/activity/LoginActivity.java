package craftosaka.syukupili.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.account.Account;

/**
 * Created by Fukkun on 2017/08/17.
 */

//子供のログイン画面
public class LoginActivity extends BaseActivity {
    Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login_layout);
        account = new Account(this);
    }

    //ログインボタンクリック
    public void loginbutton_click(View view) {

        String name = "";
        String pass = "";
        name = findViewById(R.id.nametext).toString();
        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.pass_text);
        pass = passtext.getText().toString();

        if (!account.login(pass)) {
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("ログインに失敗しました")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }
    }
}
