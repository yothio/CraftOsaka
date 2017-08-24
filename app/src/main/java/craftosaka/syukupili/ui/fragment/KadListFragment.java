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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.ui.adapter.KadListRecyclerAdapter;
import craftosaka.syukupili.util.KadDataManager;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListFragment extends BaseFragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<KadListItem> list;
    FloatingActionButton fab;

    //課題作成ダイアログボックス
    EditText titleEditText, detailEditText;
    TextView startTextView, endTextView;
    Spinner childrenSpinner;
    EditText grantPointEditText;
    Button sBtn, eBtn;

    public static KadListFragment newInstance() {
        KadListFragment fragment = new KadListFragment();
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        loadList();
//    }

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
                createKadDialogBox();

//                Log.d("KadListFragment", String.valueOf(list.size()));
//                list.add(new KadListItem("" + list.size()));
//                adapter.notifyDataSetChanged();
            }
        });


        //アダプターに配列を渡す
//        adapter = new KadListRecyclerAdapter(getContext(), list);
        adapter = new KadListRecyclerAdapter(getContext(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        setFunction();

        return v;
    }

    /**
     * 課題作成ダイアログボックスを作る
     */
    private void createKadDialogBox() {
        // カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(
                LAYOUT_INFLATER_SERVICE);

        final View layout = inflater.inflate(R.layout.dialog_create_subject_layout,
                (ViewGroup) getActivity().findViewById(R.id.dialog_layout));

        //開始日を設定するボタン
//        sBtn = layout.findViewById(R.id.textView3);
//        sBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("KadListFragment", "aaa");
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
//                datePickerDialog.show();
//                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker dialog, int year, int monthOfYear, int dayOfMonth) {
                        //year,month,dayの取得
//                        startTextView.setText("" + year);
//                    }
//                });
//            }
//        });


        preparationCreateKadDialogBox(layout);

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
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onDialogNegativeClick(dialogInterface);
                    }
                }).create();
        builder.show();
    }

    /**
     * 課題作成ダイアログボックスを作るための準備
     *
     * @param layout
     */
    private void preparationCreateKadDialogBox(View layout) {
        //タイトル
        titleEditText = layout.findViewById(R.id.new_kad_name);
        //内容
        detailEditText = layout.findViewById(R.id.new_kad_content);

        startTextView = layout.findViewById(R.id.start_date_edit_text);

        //開始日を設定するボタン

        sBtn = layout.findViewById(R.id.textView3);
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KadListFragment", "aaa");
                //現在日付を取得表示する
                Calendar calender = Calendar.getInstance();
                String year = String.valueOf(calender.get(Calendar.YEAR));
                String month = String.valueOf(calender.get(Calendar.MONTH) + 1);
                if(month.length() == 1){
                    month = "0"+month;
                }
                String date = String.valueOf(calender.get(Calendar.DATE));
                if(date.length() == 1){
                    date = "0"+date;
                }
                startTextView.setText(year + "/" + month + "/" + date);
            }
        });

        endTextView = layout.findViewById(R.id.end_date_edit_text);

        //終了日を設定するボタン
        eBtn = layout.findViewById(R.id.textView4);
        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KadListFragment", "aaa");
                //現在日付を取得表示する
                Calendar calender = Calendar.getInstance();
                String year = String.valueOf(calender.get(Calendar.YEAR));
                String month = String.valueOf(calender.get(Calendar.MONTH) + 1);
                if(month.length() == 1){
                    month = "0"+month;
                }
                String date = String.valueOf(calender.get(Calendar.DATE));
                if(date.length() == 1){
                    date = "0"+date;
                }
                endTextView.setText(year + "/" + month + "/" + date);
            }
        });

        //子リストのアイテムを選択するSpinner
        childrenSpinner = layout.findViewById(R.id.children_spinner);
        //spinnerに表示する子供のリストを作成する


        //付与ポイント
        grantPointEditText = layout.findViewById(R.id.grant_point);
    }

    /**
     * ダイアログボックスのOKボタンを押した時の処理
     *
     * @param dialog
     */
    public void onDialogPositiveClick(DialogInterface dialog) {
        //タイトルと内容のテキスト取得
        String title = titleEditText.getText().toString();

        KadListItem item = new KadListItem();
        item.setKadId(list.size());
        item.setKadName(title);

        String detail = detailEditText.getText().toString();

        item.setKadContent(detail);

        //選択されているアイテムを取得
        String child = childrenSpinner.getSelectedItem().toString();
        item.setChildId(childrenSpinner.getSelectedItemPosition());
        item.setChildName(child);

        Log.d("KadListFragment",item.getKadName() + " : " + item.getKadContent() + " : " + item.getChildName());



        //pointの取得
        int grantPoint = Integer.parseInt(grantPointEditText.getText().toString());
        item.setPoint(grantPoint);
        Log.d("KadListFragment",item.getPoint() + "pt");

        //開始日と終了日取得
        String start = startTextView.getText().toString();
        String end = endTextView.getText().toString();

        int startDate = Integer.parseInt(start.substring(0,4) + start.substring(5,7) + start.substring(8));
        int endDate = Integer.parseInt(end.substring(0,4) + end.substring(5,7) + end.substring(8));

        item.setStartDate(startDate);
        item.setEndDate(endDate);
        Log.d("KadListFragment",item.getStartDate() + " " + item.getEndDate());

        item.setProgressFrag(0);
        boolean b = false;
        item.setSettingFrag(b);

        Log.d("KadListFragment",item.getProgressFrag() + " " + item.isSettingFrag());

        //DB Insert
        KadDataManager.getInstance().insertDataBase(item);

        Log.d("KadListFragment", String.valueOf(list.size()));
        list.add(list.size(),item);

        adapter.notifyDataSetChanged();
    }

    /**
     * ダイアログボックスのCANCELボタンを押した時の処理
     *
     * @param dialogInterface
     */
    public void onDialogNegativeClick(DialogInterface dialogInterface) {

    }

    public void loadList() {
        list = new ArrayList<>();
//        KadListItem kad = new KadListItem();
        KadDataManager.getInstance().getKadData(list);
//        list.add(kad);
    }

    /**
     * フラグメントを切り替えたときにMenuActivityから呼び出され、
     * 各フラグメント毎に設定を行う。
     */
    public void setFunction() {
        //スワイプ処理の設定
        super.setOnFling(); //デフォルトの設定
//        setOnFling(); //KadListFragment独自の処理を行う場合こっちを使う
        //KeyDownイベント処理を設定
        super.setOnKeyDown();
    }

}