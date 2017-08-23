package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import craftosaka.syukupili.R;
import craftosaka.syukupili.util.KadDataManager;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button parentBtn = (Button) findViewById(R.id.parent_button);
        Button childBtn = (Button) findViewById(R.id.child_button);

        parentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        childBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KadDataManager.getInstance().deleteDataBase();
                Log.d("StartActivity", "ok");
            }
        });
    }
}
