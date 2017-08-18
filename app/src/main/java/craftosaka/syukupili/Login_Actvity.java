package craftosaka.syukupili;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by Fukkun on 2017/08/17.
 */

//子供のログイン画面
public class Login_Actvity extends BaseActivity {


    Acount acount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login_layout);
        acount = new Acount(this);

    }

    //ログインボタンクリック
    public void loginbutton_click(View view) {

        String name = "";
        String pass = "";

        name = findViewById(R.id.nametext).toString();

        MaterialEditText passtext = (MaterialEditText) findViewById(R.id.passtext);
        pass = passtext.getText().toString();

        if (!acount.login(pass)) {
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("ログインに失敗しました")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }


    }

}
