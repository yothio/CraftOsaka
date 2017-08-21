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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SQLiteDataManager.getInstance().getKadData(list);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //fragmentでのsetContentview
        View v = inflater.inflate(R.layout.fragment_kadlist_fragment, container, false);
        //レイアウトと結びつけ
        recyclerView = v.findViewById(R.id.kadlist_recyclerview);
        fab = v.findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KadListFragment", String.valueOf(list.size()));

                //課題の有無を確認
                int i = 0;
                if (list.size() != 0) {
                    i = list.get(list.size() - 1).getKadId() + 1;
                }
                //データベースに課題を追加　テスト　引数int　本番 KadListItem
                SQLiteDataManager.getInstance().insertDataBase(i);
                //テスト段階のみ使用　：　本番は上で記述しているKadListItemをlistにaddするだけ
                SQLiteDataManager.getInstance().updateKadDate(list);
                adapter.notifyDataSetChanged();
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