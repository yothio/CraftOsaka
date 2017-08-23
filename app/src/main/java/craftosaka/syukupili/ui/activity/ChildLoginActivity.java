package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import craftosaka.syukupili.R;
import craftosaka.syukupili.util.Util;
import craftosaka.syukupili.util.account.Account;

/**
 * Created by Fukkun on 2017/08/17.
 */

//子供のログイン画面
public class ChildLoginActivity extends BaseActivity {
    Account account;
    FrameLayout frameLayout;
    BootstrapButton loginBtn;
    String name = "";
    String pass = "";
    EditText passText, nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_login_layout);
        frameLayout = (FrameLayout) findViewById(R.id.focus_view);

        loginBtn = (BootstrapButton) findViewById(R.id.login_button);


        nameText = (EditText) findViewById(R.id.name_text);
        passText = (EditText) findViewById(R.id.pass_text);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameText.getText().toString();
                pass = passText.getText().toString();
                //ログイン
                if (Util.existAccount(name, pass)) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                } else {
                    passText.setError("パスワードが間違っています");
                }
            }
        });

    }


    //view以外をタップした時
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠してフォーカスを移す
        hideKeyboard();
        frameLayout.requestFocus();
        return super.onTouchEvent(event);
    }

}
