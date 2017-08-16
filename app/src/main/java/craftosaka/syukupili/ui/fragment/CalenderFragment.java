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

public class CalenderFragment extends BaseFragment {
    public static CalenderFragment newInstance(){
        CalenderFragment fragment = new CalenderFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_calender_layout,container,false);



        return v;
    }
}