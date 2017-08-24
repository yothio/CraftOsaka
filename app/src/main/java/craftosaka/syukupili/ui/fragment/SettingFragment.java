package craftosaka.syukupili.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import craftosaka.syukupili.R;

/**
 * Created by yocchi on 2017/08/16.
 */

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_setting_layout, container, false);
/*
        List<Map<String, String>> glist = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> clist = new ArrayList<List<Map<String, String>>>();

        Map<String, String> gdata = new HashMap<String, String>();
        gdata.put("acountTop", "アカウント管理");

        glist.add(gdata);


        List<Map<String, String>> childList = new ArrayList<Map<String, String>>();

        String[] list = {"アカウント作成","アカウント削除","ポイント交換レート","難易度設定"};
        // 各子ノード用データ格納
        for (int j = 0; j < list.length; j++) {
            Map<String, String> childData = new HashMap<String, String>();
            childData.put( list[j], list[j]);
            // 子ノードのリストに文字を格納
            childList.add(childData);
        }
        clist.add(childList);
        // 全体の子ノードリストに各小ノードリストのデータを格納



        // アダプタを作る
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getActivity(), glist,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "title" }, new int[] { android.R.id.text1 },
                clist, android.R.layout.simple_expandable_list_item_2,
                new String[] { "TITLE", "SUMMARY" }, new int[] {
                android.R.id.text1, android.R.id.text2 });

        View view = inflater.inflate(R.layout.activity_menu, container, false);

        ExpandableListView lv = (ExpandableListView)view.findViewById(R.id.acountsettinglist);

                //生成した情報をセット
                lv.setAdapter(adapter);
        */

        return v;
    }



    /**
     * フラグメントを切り替えたときにMenuActivityから呼び出され、
     * 各フラグメント毎に設定を行う。
     */
    public void setFunction() {
        //KeyDownイベント処理を設定
        super.setOnKeyDown();
    }
}
