package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.adapter.KadListRecyclerAdapter;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.SQLiteDataManager;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListFragment extends BaseFragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<KadListItem> list = new ArrayList<>();
    FloatingActionButton fab;

    public static KadListFragment newInstance() {
        KadListFragment fragment = new KadListFragment();
        return fragment;
    }
int i = 13;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //fragmentでのsetContentview
        View v = inflater.inflate(R.layout.fragment_kadlist_fragment, container, false);
        //データをリストに入れる
//        loadList();
        //レイアウトと結びつけ
        recyclerView = v.findViewById(R.id.kadlist_recyclerview);
        fab = v.findViewById(R.id.floating_action_button);
        SQLiteDataManager.getInstance().getKadData(list);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KadListFragment", String.valueOf(list.size()));
//                KadListItem kad = new KadListItem();
//                kad.setKadName("dummyName");
//                kad.setChildName("dummychild");
//                kad.setEndDate(20202020);
//                kad.setPoint(list.size());
//                list.add(kad);
                i++;
                SQLiteDataManager.getInstance().insertDataBase(i);
                SQLiteDataManager.getInstance().updateKadDate(list);
                adapter.notifyItemInserted(0);
            }
        });

        //アダプターに配列を渡す
        adapter = new KadListRecyclerAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        setFunction();

        return v;
    }


    public void loadList() {
        KadListItem kad = new KadListItem();
        kad.setKadName("dummyName");
        kad.setChildName("dummychild");
        kad.setEndDate(20202020);
        kad.setPoint(114);
        list.add(kad);
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