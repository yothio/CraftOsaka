package craftosaka.syukupili.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import craftosaka.syukupili.R;

/**
 * Created by yocchi on 2017/08/15.
 */

public class MenuActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewPager = (ViewPager)findViewById(R.id.fragment_viewpager);
    }
}
