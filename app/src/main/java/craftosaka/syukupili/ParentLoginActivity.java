package craftosaka.syukupili;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.ui.activity.BaseActivity;

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
    public void loginbutton_click(View view) {

        String name = "";
        String pass = "";

        name = findViewById(R.id.nametext).toString();

        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.passtext);
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


    }

}
