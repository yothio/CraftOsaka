package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.PointListItem;
import craftosaka.syukupili.ui.activity.MenuActivity;
import craftosaka.syukupili.ui.adapter.PointListRecyclerAdapter;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.PointDateManager;

/**
 * Created by yocchi on 2017/08/16.
 */

public class PointExchangeFragment extends BaseFragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<PointListItem> list = new ArrayList<>();
    FloatingActionButton fab;


    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public static PointExchangeFragment newInstance() {
        PointExchangeFragment fragment = new PointExchangeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PointDateManager.getInstance().getPointDate(list);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_point_layout, container, false);


        //レイアウトと結びつけ
        recyclerView = v.findViewById(R.id.point_list_recyclerview);
        fab = v.findViewById(R.id.floating_action_button_fab);
//子供には景品追加のボタンは見せない
        if (!Data.getInstance().childParentFrag) {
            fab.setVisibility(View.INVISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KadListFragment", String.valueOf(list.size()));
                PointItemCreateDialog dialog = new PointItemCreateDialog();
                dialog.show(getFragmentManager(), "");
                dialog.setCallback(new PointItemCreateDialog.MyCallback() {
                    @Override
                    public void positive(String itemName, int point, Boolean bool) {
                        if (bool) {
                            PointListItem pointItem = new PointListItem();
                            pointItem.setPointItemName(itemName);
                            pointItem.setPoint(String.valueOf(point));
                            list.add(list.size(), pointItem);

                            PointDateManager.getInstance().insertDataBase(pointItem);

                            adapter.notifyItemInserted(list.size());
                        }
                    }

                    @Override
                    public void negative() {

                    }
                });

            }
        });

        //アダプターに配列を渡す
        adapter = new PointListRecyclerAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        return v;
    }

    /**
     * PointExchangeFragment専用のスワイプ処理を設定します。
     */
    @Override
    public void setOnFling() {
        ((MenuActivity) getActivity()).setMethod_onFling(new MenuActivity.OriginalSimpleOnGestureListener() {
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //左スワイプ
                        Toast.makeText(getContext(), "左スワイプ", Toast.LENGTH_SHORT).show();
                    } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //右スワイプ
                        Toast.makeText(getContext(), "右スワイプ", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
//                    nothing
                }
                return false;
            }
        });
    }

    /**
     * フラグメントを切り替えたときにMenuActivityから呼び出され、
     * 各フラグメント毎に設定を行う。
     */
    public void setFunction() {
        //スワイプ処理の設定
        super.setOnFling(); //デフォルトの設定
//        setOnFling(); //PointExchangeFragment独自の処理を行う場合こっちを使う
        //KeyDownイベント処理を設定
        super.setOnKeyDown();

    }
}