package craftosaka.syukupili.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.android.datetimepicker.date.DatePickerDialog;

import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.App;
import craftosaka.syukupili.util.SQLiteDataManager;

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
                SQLiteDataManager.getInstance().deleteDataBase();
                Log.d("StartActivity", "ok");
            }
        });
    }
}
