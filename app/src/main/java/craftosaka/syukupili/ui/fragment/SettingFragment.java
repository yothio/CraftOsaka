package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.User;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.NotifyUtil;
import craftosaka.syukupili.util.PrefUtil;

/**
 * Created by yocchi on 2017/08/16.
 */

public class SettingFragment extends BaseFragment {
    CoordinatorLayout root;
    ExpandableListView accountList;
    TextView pointTv, pointTextTv;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_setting_layout, container, false);
        accountList = (ExpandableListView) v.findViewById(R.id.account_setting_list);

        root = v.findViewById(R.id.setting_fragment_root);
        pointTv = v.findViewById(R.id.point_show_main_text);
        pointTextTv = v.findViewById(R.id.point_show_sub_text);

        if (Data.getInstance().parentFrag) {
            pointTv.setVisibility(View.INVISIBLE);
            pointTextTv.setVisibility(View.INVISIBLE);
        } else {
            pointTv.setText(String.valueOf(Data.getInstance().getNowUser().getPoint()));
            accountList.setEnabled(false);
        }


        List<Map<String, String>> glist = new ArrayList<>();
        List<List<Map<String, String>>> clist = new ArrayList<>();

        Map<String, String> gdata = new HashMap<>();
        gdata.put("acountTop", "アカウント管理");

        glist.add(gdata);


        List<Map<String, String>> childList = new ArrayList<>();

        String[] list = {"アカウント作成", "アカウント削除", "ポイント交換レート", "難易度設定"};
        // 各子ノード用データ格納
        for (int j = 0; j < list.length; j++) {
            Map<String, String> childData = new HashMap<String, String>();
            childData.put("KEY", list[j]);
            // 子ノードのリストに文字を格納
            childList.add(childData);
        }
        clist.add(childList);
        // 全体の子ノードリストに各小ノードリストのデータを格納
//    public SimpleExpandableListAdapter(Context context,
//                List < ? extends Map<String, ?>>groupData,int groupLayout, String[] groupFrom,
//        int[] groupTo, List<? extends List<? extends Map<String, ?>>>childData,
//        int childLayout, String[] childFrom,int[] childTo){
//


        // アダプタを作る
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getActivity(),
                glist,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"acountTop"},
                new int[]{android.R.id.text1},
                clist,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"KEY", "SUMMARY"},
                new int[]{android.R.id.text1, android.R.id.text2});

        accountList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {


                switch (i1) {
                    case 0:
                        CreateChildAccountDialog childAccountDialog = new CreateChildAccountDialog();
                        childAccountDialog.setCallback(new CreateChildAccountDialog.MyCallback() {
                            @Override
                            public void positive(Boolean bool, String name, String pass) {
                                if (bool) {
                                    User user = new User();
                                    user.setId(PrefUtil.getUserList().size());
                                    user.setName(name);
                                    user.setPassword(pass);
                                    user.setPoint(0);
                                    PrefUtil.saveUserItem(user);
                                    NotifyUtil.insertSuccessNotify(root);
                                } else {
                                    Log.d("StartActivity", "アカウント作成失敗");
                                }
                            }

                            @Override
                            public void negative() {

                            }
                        });
                        childAccountDialog.show(getFragmentManager(), "");
                        break;

                }

                Log.d("確認", "" + view);
                Log.d("確認", "i:" + i);
                Log.d("確認", "i1:" + i1);
                Log.d("確認", "l:" + l);

                //i1によってどのitemをタップしたか決まる 0ならアカウント作成 1ならアカウント削除など


                return false;
            }
        });


        //生成した情報をセット
        accountList.setAdapter(adapter);

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
