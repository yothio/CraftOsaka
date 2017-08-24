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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.model.User;
import craftosaka.syukupili.ui.adapter.KadListRecyclerAdapter;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.KadDataManager;
import craftosaka.syukupili.util.PrefUtil;
import craftosaka.syukupili.util.Util;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by yocchi on 2017/08/16.
 */

public class KadListFragment extends BaseFragment {

    private final String ALERTE_SLECT = "※登録する子の名前IDを選択してください";
    private final String ALERTE_CREATE = "子のアカウントを作成してください";

    RecyclerView recyclerView;
    KadListRecyclerAdapter adapter;
    List<KadListItem> list;
    FloatingActionButton fab;

    //課題作成ダイアログボックス
    EditText titleEditText, detailEditText;
    TextView startTextView, endTextView;
    Spinner childrenSpinner;
    EditText grantPointEditText;
    Button sBtn, eBtn;
    private String childId;
    private List<User> childList = new ArrayList<>();

    //
    private String dialogTitle="";
    private String dialogDetail="";
    private String dialogChild="";
    private String dialogPoint="0";
    private String dialogStart="";
    private String dialogEnd="";

    public static KadListFragment newInstance() {
        KadListFragment fragment = new KadListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Data.getInstance().getNowUser() == null){
            childId = null;
        }else {
            childId = String.valueOf(Data.getInstance().getNowUser().getId());
        }
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
        if (!Data.getInstance().parentFrag) {
            fab.setVisibility(View.INVISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ダイアログボックス表示
                createKadDialogBox();
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
        titleEditText.setText(dialogTitle);
        //内容
        detailEditText = layout.findViewById(R.id.new_kad_content);
        detailEditText.setText(dialogDetail);

        startTextView = layout.findViewById(R.id.start_date_edit_text);
        startTextView.setText(dialogStart);
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
        endTextView.setText(dialogEnd);

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
        childList = PrefUtil.getUserList();
        //子のアカウント登録が出来たらこっちを使う
        String[] arr = new String[childList.size()+1];
        if(childList.size() != 0){
            arr[0] = ALERTE_SLECT;
            for(int i = 0;i < childList.size();i++){
                arr[i+1] = childList.get(i).getName()+":"+childList.get(i).getId();
                Log.d("childList",arr[i]);
            }
        }else{
            arr[0] = ALERTE_CREATE;
        }

        //子のアカウントできるまでのテスト用
//        String[] arr = new String[]{"子1:1"};
//        String[] arr = new String[]{ALERTE_SLECT};
        setSpinner(childrenSpinner,arr);


        //付与ポイント
        grantPointEditText = layout.findViewById(R.id.grant_point);
        grantPointEditText.setText(dialogPoint);
    }

    private void setSpinner(Spinner childrenSpinner,String arr[]) {
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childrenSpinner.setAdapter(adapter);
    }

    /**
     * ダイアログボックスのOKボタンを押した時の処理
     *
     * @param dialog
     */
    public void onDialogPositiveClick(DialogInterface dialog) {
        boolean check = true;

        //ダイアログボックスの入力値取得
        dialogTitle = titleEditText.getText().toString();
        dialogDetail = detailEditText.getText().toString();
        dialogChild = childrenSpinner.getSelectedItem().toString();
        dialogPoint = grantPointEditText.getText().toString();
        dialogStart = startTextView.getText().toString();
        dialogEnd = endTextView.getText().toString();

        //入力内容のチェック
        KadListItem item = new KadListItem();
        if(Util.checkNullChar(dialogTitle)) {
            item.setKadName(dialogTitle);
        }else{ check = false;}
        if(Util.checkNullChar(dialogDetail)) {
            item.setKadContent(dialogDetail);
        }else{ check = false;}

        if(!dialogChild.equals(ALERTE_SLECT) && !dialogChild.equals(ALERTE_CREATE)){
            int subStringIndex = dialogChild.indexOf(":");
            item.setChildName(dialogChild.substring(0,subStringIndex));

            if(Util.stringToInteger(dialogChild.substring(subStringIndex+1))) {
                item.setChildId(Integer.parseInt(dialogChild.substring(subStringIndex + 1)));
            }else{ check = false;}

        }else{ check = false;}

        Log.d("KadListFragment",item.getKadName() + " : " + item.getKadContent() + " : " + item.getChildName());

        //pointの取得
        if(Util.stringToInteger(dialogPoint)) {
            item.setPoint(Integer.parseInt(dialogPoint));
        }else{ check = false;}
        Log.d("KadListFragment",item.getPoint() + "pt");

        //開始日と終了日取得
        String date = Util.convertDate(dialogStart);
        if(Util.stringToInteger(date)) {
            int startDate = Integer.parseInt(date);
            item.setStartDate(startDate);
        }else{ check = false;}


        date = Util.convertDate(dialogEnd);
        if(Util.stringToInteger(date)){
            int endDate = Integer.parseInt(date);
            item.setEndDate(endDate);
        }else{ check = false;}
        Log.d("KadListFragment",item.getStartDate() + " " + item.getEndDate());

        item.setProgressFrag(0);
        item.setSettingFrag(false);
        Log.d("KadListFragment",item.getProgressFrag() + " " + item.isSettingFrag());

        if(check) {
            //DB Insert
            KadDataManager.getInstance().insertDataBase(item);
            Log.d("KadListFragment", String.valueOf(list.size()));
            list.add(list.size(), item);
            adapter.notifyDataSetChanged();

            clearDialogData();
        }else{
            Toast.makeText(getContext(),"入力内容が不正です",Toast.LENGTH_SHORT).show();
            createKadDialogBox();
        }
    }

    /**
     * 格納したダイアログボックスの入力内容を初期の状態に戻す
     */
    private void clearDialogData() {
        dialogTitle = "";
        dialogDetail = "";
        dialogChild = "";
        dialogPoint = "0";
        dialogStart = "";
        dialogEnd = "";
    }


    /**
     * ダイアログボックスのCANCELボタンを押した時の処理
     *
     * @param dialogInterface
     */
    public void onDialogNegativeClick(DialogInterface dialogInterface) {
        clearDialogData();
    }

    public void loadList() {

        list = new ArrayList<>();
        KadDataManager.getInstance().getKadData(list,childId);
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