package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.activity.MenuActivity;

import static android.R.drawable.ic_media_ff;
import static android.R.drawable.ic_media_rew;

/**
 * Created by yocchi on 2017/08/16.
 */

public class CalenderFragment extends BaseFragment {
    //月の切り替えボタン
    ImageButton prevMonth,nextMonth;
    //年月表示用TextView
    TextView yearMonth;
    //日付用TextView
    TextView a1,a2,a3,a4,a5,a6,a7; //第１週目
    TextView b1,b2,b3,b4,b5,b6,b7; //第２週目
    TextView c1,c2,c3,c4,c5,c6,c7; //第３週目
    TextView d1,d2,d3,d4,d5,d6,d7; //第４週目
    TextView e1,e2,e3,e4,e5,e6,e7; //第５週目
    TextView f1,f2,f3,f4,f5,f6,f7; //第６週目

    //Schedule用TextView1
    TextView a1_1,a2_1,a3_1,a4_1,a5_1,a6_1,a7_1; //第１週目
    TextView b1_1,b2_1,b3_1,b4_1,b5_1,b6_1,b7_1; //第２週目
    TextView c1_1,c2_1,c3_1,c4_1,c5_1,c6_1,c7_1; //第３週目
    TextView d1_1,d2_1,d3_1,d4_1,d5_1,d6_1,d7_1; //第４週目
    TextView e1_1,e2_1,e3_1,e4_1,e5_1,e6_1,e7_1; //第５週目
    TextView f1_1,f2_1,f3_1,f4_1,f5_1,f6_1,f7_1; //第６週目

    //Schedule用TextView2
    TextView a1_2,a2_2,a3_2,a4_2,a5_2,a6_2,a7_2; //第１週目
    TextView b1_2,b2_2,b3_2,b4_2,b5_2,b6_2,b7_2; //第２週目
    TextView c1_2,c2_2,c3_2,c4_2,c5_2,c6_2,c7_2; //第３週目
    TextView d1_2,d2_2,d3_2,d4_2,d5_2,d6_2,d7_2; //第４週目
    TextView e1_2,e2_2,e3_2,e4_2,e5_2,e6_2,e7_2; //第５週目
    TextView f1_2,f2_2,f3_2,f4_2,f5_2,f6_2,f7_2; //第６週目

    //
    TextView tv,newtv;

    //予定詳細表示用
    ListView listView;
    private ArrayList<Map<String, Object>> list = new ArrayList<>();
    private SimpleAdapter adapter;

    private View v;
    private FragmentActivity activity;

    private Calendar calendar;
    //表示中の年・月・日・最大日数、表示中の前の月・次の月、現在の年・月・日
    private int year,month,day,dayMax,prevMax,dayWeek,nowYear,nowMonth,nowDay;

    public CalenderFragment(){
        calendar = Calendar.getInstance();
        nowYear = calendar.get(Calendar.YEAR);
        nowMonth = calendar.get(Calendar.MONTH);
        nowDay = calendar.get(Calendar.DATE);
        Log.d("NowDate",nowYear+"/"+nowMonth+"/"+nowDay);
    }

    public static CalenderFragment newInstance(){
        CalenderFragment fragment = new CalenderFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        activity = getActivity();

        v = inflater.inflate(R.layout.fragment_calender_layout,container,false);

        //前の月を表示するボタン
        prevMonth = v.findViewById(R.id.prev_month);
        prevMonth.setImageDrawable(getResources().getDrawable(ic_media_rew,activity.getTheme()));
        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCalendar(year,month-1);
            }
        });


        //次の月を表示するボタン
        nextMonth = v.findViewById(R.id.next_month);
        nextMonth.setImageDrawable(getResources().getDrawable(ic_media_ff,activity.getTheme()));
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCalendar(year,month+1);
            }
        });

        //年月を表示するTextView
        yearMonth = v.findViewById(R.id.year_month);

        //日付を表示するTextView
        setWeek();
        setWeekSchedule1();

        listView = v.findViewById(R.id.listView);

        //日付を表示するTextViewに日付を入れてカレンダーを作る
        createCalendar(nowYear,nowMonth);

        return v;
    }

    /**
     * フラグメントを切り替えたときにMenuActivityから呼び出され、
     * 各フラグメント毎に設定を行う。
     */
    public void setFunction(){
        //スワイプ検出時の処理を設定
        setOnFling();
        //KeyDownイベント処理を設定
        super.setOnKeyDown();
    }

    /**
     * スワイプ処理をCalenderFragment用に書き換えます
     */
    @Override
    public void setOnFling(){
        ((MenuActivity)getActivity()).setMethod_onFling(new MenuActivity.OriginalSimpleOnGestureListener(){
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //左スワイプ　次の月表示
                        createCalendar(year,month+1);
                    } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //右スワイプ　前の月表示
                        createCalendar(year,month-1);
                    }
                }catch (Exception e){
//                    nothing
                }
                return false;
            }
        });
    }

    /**
     * 選択した日付の予定の詳細を表示します。
     * @param view
     */
    public void selectedDay(View view) {
        if(tv != null){
            tv.setBackgroundColor(getResources().getColor(R.color.white,activity.getTheme()));
        }

        newtv = view.findViewById(view.getId());
        newtv.setBackgroundColor(getResources().getColor(R.color.lightbluesky,activity.getTheme()));

        tv = newtv;

        String getDayCS;
        int getDay;
        try {
            getDayCS = tv.getText().toString();
            getDay = Integer.parseInt(getDayCS);
        } catch (Exception e) {
            Log.d("CalenderException", "selectedDay　Error");
            return;
        }

        //year,month,getDayを引数にしてその日の予定を検索。

        //選択した日付の予定詳細表示欄で予定を表示する。
        createScheduleList();
    }

    private void createScheduleList() {
        Log.d("CalenderFragment","createScheduleList/予定詳細を表示する");
        if(list.size() > 0) {
            list.clear();
        }

        if(list.size() == 0){
            Map<String, Object> data = new HashMap();
            data.put("title", "今日の予定　タイトル1");
            data.put("detail", "詳細1");
            list.add(data);

        }
        Map<String, Object> data = new HashMap();
        data.put("title", "今日の予定　タイトル2");
        data.put("detail", "詳細2");
        list.add(data);

        // リストビューにアイテム追加用のアダプターを設定
        adapter = new SimpleAdapter(
                getActivity(),
                list,
                R.layout.item_schedule_list,
                new String[]{"title", "detail"},
                new int[]{R.id.title,R.id.detail});
        listView.setAdapter(adapter);
        ViewCompat.setNestedScrollingEnabled(listView, true);

    }

    /**
     * カレンダーを作るための準備とカレンダー上部の表示変更を行います
     * @param year　作成するカレンダーの年
     * @param month　作成するカレンダーの月
     */
    private void createCalendar(int year,int month) {
        //日付を1日にしておく
        //calendar.set(Calendar.DATE,1);
        Log.d("createCalendar","引数year="+year+" month="+month);
        /**
         * カレンダー上部(年月)の設定
         */
        this.year = year;
        this.month = month;

        if(this.month > 11){
            //月が一周した(来年になったとき)
            this.month = 0;
            this.year++;
        }else if(this.month < 0){
            //去年になったとき
            this.month = 11;
            this.year--;
        }
        //カレンダー上部にカレンダーの年月を表示
        yearMonth.setText(this.year + "年　" + (this.month+1) + "月");
        Log.d("createCalendar", this.year + "/" + (this.month+1) + "のカレンダーを作ります");

        //引数で変数calendarの内容を書き換える
        calendar.clear();
        calendar.set(this.year,this.month,1);

        /**
         * カレンダーの表示
         */
        //今月の日数を取得
        dayMax = calendar.getActualMaximum(calendar.DATE);

        //今月の1日の曜日を取得
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //月を先月にして、先月の日数を取得
        calendar.set(Calendar.MONTH,this.month-1);
        prevMax = calendar.getActualMaximum(calendar.DATE);

        Log.d("dayMax", String.valueOf(dayMax) + " "+ prevMax + " " + dayWeek);

        calendarView(dayMax,dayWeek,prevMax);
    }

    /**
     * カレンダーを作ります。めんどくさいやり方してます。
     * @param dayMax　その月の最大日数
     * @param dayWeek　1日の曜日
     * @param prevMax　先月の最大日数
     */
    private void calendarView(int dayMax, int dayWeek, int prevMax) {
        //第１週目の表示から！

        String setText = "";
        //月の始まりが日曜日じゃなかったら,先月分の表示
        if(dayWeek != Calendar.SUNDAY){
            createPrevDays(dayWeek,prevMax);
        }

        //今月の日付、今月の日付を入れ終わって空きがあれば来月の日付を入れていく
        int day = 1;
        create1stWeek(dayWeek,day);

    }

    /**
     * 当月の初日前、カレンダーに空きがある場合先月の最後の日で埋める
     * @param dayWeek　当月の開始曜日
     * @param prevMax　先月の最大日数
     */
    private void createPrevDays(int dayWeek, int prevMax) {
        int i = 1;
        String setText = "";

        switch (dayWeek - 1){
            case Calendar.FRIDAY:
                setText = String.valueOf(prevMax);
                a6.setText(setText);
                a6.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                prevMax--;
            case Calendar.THURSDAY:
                setText = String.valueOf(prevMax);
                a5.setText(setText);
                a5.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                prevMax--;

            case Calendar.WEDNESDAY:
                setText = String.valueOf(prevMax);
                a4.setText(setText);
                a4.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                prevMax--;

            case Calendar.TUESDAY:
                setText = String.valueOf(prevMax);
                a3.setText(setText);
                a3.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                prevMax--;

            case Calendar.MONDAY:
                setText = String.valueOf(prevMax);
                a2.setText(setText);
                a2.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                prevMax--;

            case Calendar.SUNDAY:
                setText = String.valueOf(prevMax);
                a1.setText(setText);
                a1.setTextColor(getResources().getColor(R.color.lightpink,activity.getTheme()));
        }
    }

    /**
     * 第１週目の日付を入れていきます
     * @param dayWeek　当月の開始曜日
     * @param day　日付。１週目なので貰うときは必ず１
     */
    private void create1stWeek(int dayWeek, int day) {
        String setText = "";

        switch (dayWeek) {
            case Calendar.SUNDAY:
                setText = String.valueOf(day);
                a1.setText(setText);
                a1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
                day++;
            case Calendar.MONDAY:
                setText = String.valueOf(day);
                a2.setText(setText);
                a2.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
                day++;
            case Calendar.TUESDAY:
                setText = String.valueOf(day);
                a3.setText(setText);
                a3.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
                day++;
            case Calendar.WEDNESDAY:
                setText = String.valueOf(day);
                a4.setText(setText);
                a4.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
                day++;
            case Calendar.THURSDAY:
                setText = String.valueOf(day);
                a5.setText(setText);
                a5.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
                day++;
            case Calendar.FRIDAY:
                setText = String.valueOf(day);
                a6.setText(setText);
                a6.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
                day++;
            case Calendar.SATURDAY:
                setText = String.valueOf(day);
                a7.setText(setText);
                a7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
                day++;
        }
        create2ndWeek(day);
    }

    /**
     * 第２週目を作成するー
     * @param day　１週目から引き継いだ日付
     */
    private void create2ndWeek(int day) {

        String setText = "";

            setText = String.valueOf(day);
            b1.setText(setText);
            b1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
            day++;

            setText = String.valueOf(day);
            b2.setText(setText);
            day++;

            setText = String.valueOf(day);
            b3.setText(setText);
            day++;

            setText = String.valueOf(day);
            b4.setText(setText);
            day++;

            setText = String.valueOf(day);
            b5.setText(setText);
            day++;

            setText = String.valueOf(day);
            b6.setText(setText);
            day++;

            setText = String.valueOf(day);
            b7.setText(setText);
            b7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
            day++;

        create3rdWeek(day);
    }

    /**
     * 3週目
     * @param day　日付
     */
    private void create3rdWeek(int day) {
        String setText = "";

        setText = String.valueOf(day);
        c1.setText(setText);
        c1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
        day++;

        setText = String.valueOf(day);
        c2.setText(setText);
        day++;

        setText = String.valueOf(day);
        c3.setText(setText);
        day++;

        setText = String.valueOf(day);
        c4.setText(setText);
        day++;

        setText = String.valueOf(day);
        c5.setText(setText);
        day++;

        setText = String.valueOf(day);
        c6.setText(setText);
        day++;

        setText = String.valueOf(day);
        c7.setText(setText);
        c7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
        day++;

        create4thWeek(day);
    }

    /**
     * ４回目です
     * @param day
     */
    private void create4thWeek(int day) {
        String setText = "";

        setText = String.valueOf(day);
        d1.setText(setText);
        d1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
        day++;

        setText = String.valueOf(day);
        d2.setText(setText);
        day++;

        setText = String.valueOf(day);
        d3.setText(setText);
        day++;

        setText = String.valueOf(day);
        d4.setText(setText);
        day++;

        setText = String.valueOf(day);
        d5.setText(setText);
        day++;

        setText = String.valueOf(day);
        d6.setText(setText);
        day++;

        setText = String.valueOf(day);
        d7.setText(setText);
        d7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
        day++;

        create5thWeek(day);
    }

    /**
     * 5(以下略)
     * @param day
     */
    private void create5thWeek(int day) {
        String setText = "";

        if(day > dayMax){
            createNextDays(Calendar.SUNDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e1.setText(setText);
        e1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.MONDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e2.setText(setText);
        e2.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.TUESDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e3.setText(setText);
        e3.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.WEDNESDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e4.setText(setText);
        e4.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.THURSDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e5.setText(setText);
        e5.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.FRIDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e6.setText(setText);
        e6.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.SATURDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e7.setText(setText);
        e7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
        day++;

        create6thWeek(day);
    }

    /**
     * 6(ry
     * @param day
     */
    private void create6thWeek(int day) {
        String setText = "";

        if(day > dayMax){
            createNextDays(Calendar.SUNDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f1.setText(setText);
        f1.setTextColor(getResources().getColor(R.color.red, activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.MONDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f2.setText(setText);
        f2.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.TUESDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f3.setText(setText);
        f3.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.WEDNESDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f4.setText(setText);
        f4.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.THURSDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f5.setText(setText);
        f5.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.FRIDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f6.setText(setText);
        f6.setTextColor(getResources().getColor(R.color.black,activity.getTheme()));
        day++;

        if(day > dayMax){
            createNextDays(Calendar.SATURDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f7.setText(setText);
        f7.setTextColor(getResources().getColor(R.color.blue, activity.getTheme()));
        day++;
    }

    /**
     * カレンダーの後ろ側空きスペースに来月分の日付を入れていきます
     * @param dayOfTheWeek　来月の日付の開始曜日
     * @param i　スタートが５週目からか６週目からか
     */
    private void createNextDays(int dayOfTheWeek, int i) {
        String setText = "";
        int day = 1;
        switch (i){
            case 5:
                switch (dayOfTheWeek){
                    case Calendar.SUNDAY:
                        setText = String.valueOf(day);
                        e1.setText(setText);
                        e1.setTextColor(getResources().getColor(R.color.lightpink,activity.getTheme()));
                        day++;

                    case Calendar.MONDAY:
                        setText = String.valueOf(day);
                        e2.setText(setText);
                        e2.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;

                    case Calendar.TUESDAY:
                        setText = String.valueOf(day);
                        e3.setText(setText);
                        e3.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.WEDNESDAY:
                        setText = String.valueOf(day);
                        e4.setText(setText);
                        e4.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.THURSDAY:
                        setText = String.valueOf(day);
                        e5.setText(setText);
                        e5.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.FRIDAY:
                        setText = String.valueOf(day);
                        e6.setText(setText);
                        e6.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;

                    case Calendar.SATURDAY:
                        setText = String.valueOf(day);
                        e7.setText(setText);
                        e7.setTextColor(getResources().getColor(R.color.lightbluesky,activity.getTheme()));
                        day++;
                        dayOfTheWeek = 1;
                }
            case 6:
                switch (dayOfTheWeek){
                    case Calendar.SUNDAY:
                        setText = String.valueOf(day);
                        f1.setText(setText);
                        f1.setTextColor(getResources().getColor(R.color.lightpink,activity.getTheme()));
                        day++;

                    case Calendar.MONDAY:
                        setText = String.valueOf(day);
                        f2.setText(setText);
                        f2.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;

                    case Calendar.TUESDAY:
                        setText = String.valueOf(day);
                        f3.setText(setText);
                        f3.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.WEDNESDAY:
                        setText = String.valueOf(day);
                        f4.setText(setText);
                        f4.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.THURSDAY:
                        setText = String.valueOf(day);
                        f5.setText(setText);
                        f5.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;
                    case Calendar.FRIDAY:
                        setText = String.valueOf(day);
                        f6.setText(setText);
                        f6.setTextColor(getResources().getColor(R.color.darkgray,activity.getTheme()));
                        day++;

                    case Calendar.SATURDAY:
                        setText = String.valueOf(day);
                        f7.setText(setText);
                        f7.setTextColor(getResources().getColor(R.color.lightbluesky,activity.getTheme()));
                }
        }
    }

    /**
     * めんどくさいことしてます。
     */
    private void setWeek() {
        //第１週目
        a1 = (TextView)v.findViewById(R.id.a1);
        a2 = (TextView)v.findViewById(R.id.a2);
        a3 = (TextView)v.findViewById(R.id.a3);
        a4 = (TextView)v.findViewById(R.id.a4);
        a5 = (TextView)v.findViewById(R.id.a5);
        a6 = (TextView)v.findViewById(R.id.a6);
        a7 = (TextView)v.findViewById(R.id.a7);

        //第2週目
        b1 = (TextView)v.findViewById(R.id.b1);
        b2 = (TextView)v.findViewById(R.id.b2);
        b3 = (TextView)v.findViewById(R.id.b3);
        b4 = (TextView)v.findViewById(R.id.b4);
        b5 = (TextView)v.findViewById(R.id.b5);
        b6 = (TextView)v.findViewById(R.id.b6);
        b7 = (TextView)v.findViewById(R.id.b7);

        //第3週目
        c1 = (TextView)v.findViewById(R.id.c1);
        c2 = (TextView)v.findViewById(R.id.c2);
        c3 = (TextView)v.findViewById(R.id.c3);
        c4 = (TextView)v.findViewById(R.id.c4);
        c5 = (TextView)v.findViewById(R.id.c5);
        c6 = (TextView)v.findViewById(R.id.c6);
        c7 = (TextView)v.findViewById(R.id.c7);

        //第4週目
        d1 = (TextView)v.findViewById(R.id.d1);
        d2 = (TextView)v.findViewById(R.id.d2);
        d3 = (TextView)v.findViewById(R.id.d3);
        d4 = (TextView)v.findViewById(R.id.d4);
        d5 = (TextView)v.findViewById(R.id.d5);
        d6 = (TextView)v.findViewById(R.id.d6);
        d7 = (TextView)v.findViewById(R.id.d7);

        //第5週目
        e1 = (TextView)v.findViewById(R.id.e1);
        e2 = (TextView)v.findViewById(R.id.e2);
        e3 = (TextView)v.findViewById(R.id.e3);
        e4 = (TextView)v.findViewById(R.id.e4);
        e5 = (TextView)v.findViewById(R.id.e5);
        e6 = (TextView)v.findViewById(R.id.e6);
        e7 = (TextView)v.findViewById(R.id.e7);

        //第6週目
        f1 = (TextView)v.findViewById(R.id.f1);
        f2 = (TextView)v.findViewById(R.id.f2);
        f3 = (TextView)v.findViewById(R.id.f3);
        f4 = (TextView)v.findViewById(R.id.f4);
        f5 = (TextView)v.findViewById(R.id.f5);
        f6 = (TextView)v.findViewById(R.id.f6);
        f7 = (TextView)v.findViewById(R.id.f7);
    }

    /**
     * めんどくさいことしてます。
     */
    private void setWeekSchedule1() {
        //第１週目
        a1_1 = (TextView)v.findViewById(R.id.a1_1);
        a2_1 = (TextView)v.findViewById(R.id.a2_1);
        a3_1 = (TextView)v.findViewById(R.id.a3_1);
        a4_1 = (TextView)v.findViewById(R.id.a4_1);
        a5_1 = (TextView)v.findViewById(R.id.a5_1);
        a6_1 = (TextView)v.findViewById(R.id.a6_1);
        a7_1 = (TextView)v.findViewById(R.id.a7_1);

        //第2週目
        b1_1 = (TextView)v.findViewById(R.id.b1_1);
        b2_1 = (TextView)v.findViewById(R.id.b2_1);
        b3_1 = (TextView)v.findViewById(R.id.b3_1);
        b4_1 = (TextView)v.findViewById(R.id.b4_1);
        b5_1 = (TextView)v.findViewById(R.id.b5_1);
        b6_1 = (TextView)v.findViewById(R.id.b6_1);
        b7_1 = (TextView)v.findViewById(R.id.b7_1);

        //第3週目
        c1_1 = (TextView)v.findViewById(R.id.c1_1);
        c2_1 = (TextView)v.findViewById(R.id.c2_1);
        c3_1 = (TextView)v.findViewById(R.id.c3_1);
        c4_1 = (TextView)v.findViewById(R.id.c4_1);
        c5_1 = (TextView)v.findViewById(R.id.c5_1);
        c6_1 = (TextView)v.findViewById(R.id.c6_1);
        c7_1 = (TextView)v.findViewById(R.id.c7_1);

        //第4週目
        d1_1 = (TextView)v.findViewById(R.id.d1_1);
        d2_1 = (TextView)v.findViewById(R.id.d2_1);
        d3_1 = (TextView)v.findViewById(R.id.d3_1);
        d4_1 = (TextView)v.findViewById(R.id.d4_1);
        d5_1 = (TextView)v.findViewById(R.id.d5_1);
        d6_1 = (TextView)v.findViewById(R.id.d6_1);
        d7_1 = (TextView)v.findViewById(R.id.d7_1);

        //第5週目
        e1_1 = (TextView)v.findViewById(R.id.e1_1);
        e2_1 = (TextView)v.findViewById(R.id.e2_1);
        e3_1 = (TextView)v.findViewById(R.id.e3_1);
        e4_1 = (TextView)v.findViewById(R.id.e4_1);
        e5_1 = (TextView)v.findViewById(R.id.e5_1);
        e6_1 = (TextView)v.findViewById(R.id.e6_1);
        e7_1 = (TextView)v.findViewById(R.id.e7_1);

        //第6週目
        f1_1 = (TextView)v.findViewById(R.id.f1_1);
        f2_1 = (TextView)v.findViewById(R.id.f2_1);
        f3_1 = (TextView)v.findViewById(R.id.f3_1);
        f4_1 = (TextView)v.findViewById(R.id.f4_1);
        f5_1 = (TextView)v.findViewById(R.id.f5_1);
        f6_1 = (TextView)v.findViewById(R.id.f6_1);
        f7_1 = (TextView)v.findViewById(R.id.f7_1);
    }
}