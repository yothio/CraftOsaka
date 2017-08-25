package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import craftosaka.syukupili.R;
import craftosaka.syukupili.model.User;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.ImageResizer;
import craftosaka.syukupili.util.KadDataManager;
import craftosaka.syukupili.util.PointDateManager;
import craftosaka.syukupili.util.PrefUtil;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.parent);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.child);
        ImageButton parentBtn = (ImageButton) findViewById(R.id.parent_button);
        ImageButton childBtn = (ImageButton) findViewById(R.id.child_button);
        childBtn.setImageBitmap(ImageResizer.resizeTooBigBitmap(bitmap2));
        parentBtn.setImageBitmap(ImageResizer.resizeTooBigBitmap(bitmap1));
//        Button parentBtn = (Button) findViewById(R.id.parent_button);

//        Button childBtn = (Button) findViewById(R.id.child_button);

        parentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParentLoginActivity.class);
                startActivity(intent);
            }
        });

        childBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                KadDataManager.getInstance().deleteDataBase();
//                PointDateManager.getInstance().deleteDataBase();

                for (User user : PrefUtil.getUserList()) {
                    Log.d("User", "名前" + user.getName() + "パスワード" + user.getPassword() + "ポイント" + user.getPoint());
                }

                Intent intent = new Intent(getApplicationContext(), ChildLoginActivity.class);
                startActivity(intent);

            }
        });



    }
}
