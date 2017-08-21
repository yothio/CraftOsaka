package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

import craftosaka.syukupili.R;

import static android.R.drawable.ic_media_ff;
import static android.R.drawable.ic_media_rew;

/**
 * Created by yocchi on 2017/08/16.
 */

public class CalenderFragment extends BaseFragment {
    ImageButton prevMonth,nextMonth;
    TextView yearMonth;
    TextView a1,a2,a3,a4,a5,a6,a7; //第１週目
    TextView b1,b2,b3,b4,b5,b6,b7; //第２週目
    TextView c1,c2,c3,c4,c5,c6,c7; //第３週目
    TextView d1,d2,d3,d4,d5,d6,d7; //第４週目
    TextView e1,e2,e3,e4,e5,e6,e7; //第５週目
    TextView f1,f2,f3,f4,f5,f6,f7; //第６週目

    private View v;
    private FragmentActivity activity;

    private Calendar calendar;
    private int year,month,day,dayMax,prevMax,dayWeek;

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
        prevMonth = (ImageButton)v.findViewById(R.id.prev_month);
        prevMonth.setImageDrawable(getResources().getDrawable(ic_media_rew,activity.getTheme()));

        //次の月を表示するボタン
        nextMonth = (ImageButton)v.findViewById(R.id.next_month);
        nextMonth.setImageDrawable(getResources().getDrawable(ic_media_ff,activity.getTheme()));

        //年月を表示するTextView
        yearMonth = (TextView)v.findViewById(R.id.year_month);

        setWeek();

        createCalendar();

        return v;
    }

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

    private void createCalendar() {
        calendar = Calendar.getInstance();
        //日付を1日にしておく
        calendar.set(Calendar.DATE,1);
        /**
         * カレンダー上部(年月)の設定
         */
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        yearMonth.setText(year + "年　" + month + "月");
        Log.d("nowMonth", year + " " + month);

        /**
         * カレンダーの表示
         */
        //今月の日数を取得
        dayMax = calendar.getActualMaximum(calendar.DATE);

        //今月の1日の曜日を取得
        dayWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //月を先月にして、先月の日数を取得
        calendar.set(Calendar.MONTH,month-1);
        prevMax = calendar.getActualMaximum(calendar.DATE);

        Log.d("dayMax", String.valueOf(dayMax) + " "+ prevMax + " " + dayWeek);

        calendarView(dayMax,dayWeek,prevMax);
    }

    private void calendarView(int dayMax, int dayWeek, int prevMax) {
        //第１週目の表示から！

        String setText = "";
        //月の始まりが日曜日じゃなかったら,先月分の表示
        if(dayWeek != Calendar.SUNDAY){
            createPrevDays(dayWeek,prevMax);
        }

        int day = 1;
        create1stWeek(dayWeek,day);

    }

    /**
     * 第１週目の日付を入れていきます
     * @param dayWeek　当月の開始曜日
     * @param day　日付。１週目なので貰うときは必ず１
     * @return　２週目へ引き継ぐ日付
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
                day++;
            case Calendar.TUESDAY:
                setText = String.valueOf(day);
                a3.setText(setText);
                day++;
            case Calendar.WEDNESDAY:
                setText = String.valueOf(day);
                a4.setText(setText);
                day++;
            case Calendar.THURSDAY:
                setText = String.valueOf(day);
                a5.setText(setText);
                day++;
            case Calendar.FRIDAY:
                setText = String.valueOf(day);
                a6.setText(setText);
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
        day++;

        if(day > dayMax){
            createNextDays(Calendar.TUESDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e3.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.WEDNESDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e4.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.THURSDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e5.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.FRIDAY,5);
            return;
        }
        setText = String.valueOf(day);
        e6.setText(setText);
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
        day++;

        if(day > dayMax){
            createNextDays(Calendar.TUESDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f3.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.WEDNESDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f4.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.THURSDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f5.setText(setText);
        day++;

        if(day > dayMax){
            createNextDays(Calendar.FRIDAY,6);
            return;
        }
        setText = String.valueOf(day);
        f6.setText(setText);
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

    private void createNextDays(int dayweek, int i) {
        String setText = "";
        int day = 1;
        switch (i){
            case 5:
                switch (dayweek){
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
                        dayweek = 1;
                }
            case 6:
                switch (dayweek){
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
}