package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import craftosaka.syukupili.R;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListFragment extends BaseFragment {

    public static KadListFragment newInstance(){
        KadListFragment fragment = new KadListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_kadlist_fragment,container,false);



        return v;
    }
}