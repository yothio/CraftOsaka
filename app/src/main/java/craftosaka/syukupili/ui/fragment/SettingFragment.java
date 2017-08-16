package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import craftosaka.syukupili.R;

/**
 * Created by yocchi on 2017/08/16.
 */

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance(){
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_setting_layout,container,false);



        return v;
    }
}
