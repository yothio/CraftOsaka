package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.fragment.CreateParentAccountDialog;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.PrefUtil;

/**
 * Created by Fukkun on 2017/08/17.
 */


//親のログイン画面
public class ParentLoginActivity extends BaseActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login_layout);
        frameLayout = (FrameLayout) findViewById(R.id.focus_view);
    }

    //ログインボタンクリック
    public void loginButton_click(View view) {
        String pass = "";
        EditText passText = (EditText) findViewById(R.id.pass_text);

        pass = passText.getText().toString();
        //ログイン
        if (PrefUtil.getParentPass().equals(pass)) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            Data.getInstance().parentFrag = true;
            intent.putExtra("fromLogin",true);
            //親と子の認識をしないといけないので親がログインしたときはnowUserをnullにします
            Data.getInstance().setNowUser(null);
            startActivity(intent);
        } else {
            passText.setError("パスワードが間違っています");
        }
    }

    //view以外をタップした時
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠してフォーカスを移す
        hideKeyboard();
        frameLayout.requestFocus();
        return super.onTouchEvent(event);
    }

    public void create_acount_Button_Click(View view) {
//        Intent intent = new Intent(getApplicationContext(), CreateParentAccount.class);
//        startActivity(intent);

        CreateParentAccountDialog dialog = new CreateParentAccountDialog();
        dialog.show(getSupportFragmentManager(), "");
        dialog.setCallback(new CreateParentAccountDialog.MyCallback() {
            @Override
            public void positive(Boolean bool) {

                if (bool) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    Data.getInstance().parentFrag = true;
                    intent.putExtra("fromLogin",true);

                    startActivity(intent);
                }
            }

            @Override
            public void negative() {

            }
        });
    }
}
