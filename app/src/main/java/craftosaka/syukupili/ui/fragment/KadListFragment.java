package craftosaka.syukupili.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.ui.adapter.KadListRecyclerAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListFragment extends BaseFragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<KadListItem> list;
    FloatingActionButton fab;

    public static KadListFragment newInstance() {
        KadListFragment fragment = new KadListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //fragmentでのsetContentview
        View v = inflater.inflate(R.layout.fragment_kadlist_fragment, container, false);
        //データをリストに入れる
        loadList();
        //レイアウトと結びつけ
        recyclerView = v.findViewById(R.id.kadlist_recyclerview);
        fab = v.findViewById(R.id.floating_action_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ダイアログボックス表示
                createDialogBox();
                //パーミッションの説明ダイアログを作成
//                DialogFragment noticeDialogFragment = new NoticeDialogFragment();
                //パーミッションの説明ダイアログを表示
//                noticeDialogFragment.show(getActivity().getFragmentManager(), "noticeDialog");

//                Log.d("KadListFragment", String.valueOf(list.size()));
//                list.add(new KadListItem("" + list.size()));
//                adapter.notifyDataSetChanged();
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

    private void createDialogBox() {
        // カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog_create_subject_layout,
                (ViewGroup)getActivity().findViewById(R.id.dialog_layout));

        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("課題新規登録")
                .setView(layout)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    //ダイアログOKクリック時
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogPositiveClick(dialog);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onDialogNegativeClick(dialogInterface);
                    }
                }).create();
        builder.show();
    }

    /**
     * ダイアログボックスのOKボタンを押した時の処理
     * @param dialog
     */
    public void onDialogPositiveClick(DialogInterface dialog) {
        Log.d("KadListFragment", String.valueOf(list.size()));
        list.add(new KadListItem());
        adapter.notifyDataSetChanged();
    }

    /**
     * ダイアログボックスのCANCELボタンを押した時の処理
     * @param dialog
     */
    public void onDialogNegativeClick(DialogInterface dialog) {

    }

    public void loadList() {
        list = new ArrayList<>();
        KadListItem kad = new KadListItem();
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