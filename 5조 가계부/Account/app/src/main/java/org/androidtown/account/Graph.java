//코드정리
package org.androidtown.account;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Graph extends AppCompatActivity {
    Button tabBar;
    ArrayList<Entry> yvalues;
    Button tapBar;
    Button dateSetting;
    CheckDate checkDate;
    TextView dateSettingText;
    TextView showDetail;
    DataBase db;
    Button goMain;
    PieChart pieChart;
    //Button dateSettingClear;
    ArrayList<String> barGxLabels;
    ArrayList<BarEntry> barEntries;
    int checkType;
    int[] chartColors = new int[] { Color.WHITE };
    BarChart barChart;
    String dbcheckDate;
    String checkName;
    int checkPrice;
    int checkIO;
    String checkClass;
    Button O_B_Type;
    ArrayList<String>pieGxLabels;
    int eatPrice = 0;
    int medicalPrice = 0;
    int storePrice = 0;
    int transPrice = 0;
    int dressPrice = 0;
    int etcPrice = 0;
    String checkStartDate="";
    String checkEndDate="";

    Date changeDate=null;
    Date changeStartDate = null;
    Date changeEndDate = null;
    Date nowTime=null;
    String nowDayTimetext2="";
    Date nowTimeMonth=null;
    Date nowlessTime=null;
    //여기까지
    SqlStatement sqlstatement = new SqlStatement();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        dateSetting = (Button) findViewById(R.id.dateSetting);
        dateSettingText = (TextView) findViewById(R.id.dateSettingText);
        showDetail = (TextView) findViewById(R.id.showDetail);
        O_B_Type = (Button) findViewById(R.id.graphType);
        barChart = (BarChart) findViewById(R.id.barChart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setDescription("");
        pieChart = (PieChart)findViewById(R.id.pieChart);

        TabFragment_stat tabFragment = new TabFragment_stat();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.stat_container, tabFragment);
        ft.commit();

//db 불러오는거
        this.db = new DataBase("account", this);
        //this.db.createTable(SqlStatement.sql_01);
        //this.db.createTable(SqlStatement.sql_02);
        //this.db.createTable(SqlStatement.sql_03);
        //this.db.createTable(SqlStatement.sql_04);


//시간설정 버튼 눌렀을떄
        dateSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DateInput();
                setPieDataInfo();
                setPieData();
                setBarData();
                setData();
            }
        });

        //처음에 보여지는 설정. 시간값에 따라서 그래프를 그려줌.
        String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
        Cursor c2 = db.selectData(selectSql2);
        String selectSql3 = "select Name, Price, IO, Class, Date from BasicBudget";
        Cursor c3 = db.selectData(selectSql3);

        String Day2;
        Day2 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(new Date());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String nowDayTimetext = new SimpleDateFormat("dd",Locale.KOREA).format(new Date());
        DateFormat dateFormat2 = new SimpleDateFormat("dd",Locale.KOREA);
        nowDayTimetext2 = new SimpleDateFormat("MM",Locale.KOREA).format(new Date());//월 표시해주는거.
        DateFormat dateFormat3 = new SimpleDateFormat("MM",Locale.KOREA);
        try
        {
            //시간으로 다시 바꾼것.
            nowTime = dateFormat.parse(Day2);//현재 날짜.
            Date nowDayTime = dateFormat2.parse(nowDayTimetext);
            nowTimeMonth = dateFormat3.parse(Day2);

        }catch(ParseException e)
        {
            e.printStackTrace();
        }
        nowDayTimetext = nowDayTimetext.toString();
        nowDayTimetext2 = nowDayTimetext2.toString();
        dateSettingText.setText(" " + nowDayTimetext2+ "월 ");
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowTime);
        cal.add(Calendar.DATE,-(Integer.parseInt(nowDayTimetext)));// -한게 그 현재 날을 구해가지고 뺀 것.
        String lessToday=null;
        lessToday = dateFormat.format(cal.getTime());
        try
        {
            nowlessTime = dateFormat.parse(lessToday);

        }catch(ParseException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < c2.getCount(); i++) {//Budget일때
            c2.moveToNext();
            dbcheckDate = c2.getString(0);
            checkPrice = c2.getInt(1);
            checkName = c2.getString(2);
            checkIO = c2.getInt(3);
            checkClass = c2.getString(4);

            try {
                //시간으로 다시 바꾼것.
                changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

            } catch (ParseException e) {
                e.printStackTrace();
            }

            int compare = changeDate.compareTo(nowlessTime);//기존값이 시작값보다 클때
            int compare2 = changeDate.compareTo(nowTime);//기존값이 끝값보다 작을 때
            if (compare >= 0 && compare2 <= 0) {

                if(checkIO ==1) {
                    if (checkClass.equals("식비")) {
                        eatPrice += checkPrice;
                    } else if (checkClass.equals("의료비")) {
                        medicalPrice += checkPrice;
                    } else if (checkClass.equals("적금")) {
                        storePrice += checkPrice;
                    } else if (checkClass.equals("교통비")) {
                        transPrice += checkPrice;
                    } else if (checkClass.equals("의류비")) {
                        dressPrice += checkPrice;
                    } else if (checkClass.equals("기타")) {
                        etcPrice += checkPrice;
                    }
                }
            }
        }
        for (int i = 0; i < c3.getCount(); i++) {
            c3.moveToNext();

            checkName = c3.getString(0);
            checkPrice = c3.getInt(1);
            checkIO = c3.getInt(2);
            checkClass = c3.getString(3);
            dbcheckDate = c3.getString(4);

            try {
                //시간으로 다시 바꾼것.
                changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

            } catch (ParseException e) {
                e.printStackTrace();
            }

            int compare = changeDate.compareTo(nowlessTime);//기존값이 시작값보다 클때
            int compare2 = changeDate.compareTo(nowTime);//기존값이 끝값보다 작을 때
            if (compare >= 0 && compare2 <= 0) {

                if (checkIO == 1) {
                    if (checkClass.equals("식비")) {
                        eatPrice += checkPrice;
                    } else if (checkClass.equals("의료비")) {
                        medicalPrice += checkPrice;
                    } else if (checkClass.equals("적금")) {
                        storePrice += checkPrice;
                    } else if (checkClass.equals("교통비")) {
                        transPrice += checkPrice;
                    } else if (checkClass.equals("의류비")) {
                        dressPrice += checkPrice;
                    } else if (checkClass.equals("기타")) {
                        etcPrice += checkPrice;
                    }
                }
            }
        }

        barGxLabels = new ArrayList<String>();
        barGxLabels.add("식비");
        barGxLabels.add("의료비");
        barGxLabels.add("적금");
        barGxLabels.add("교통비");
        barGxLabels.add("의류비");
        barGxLabels.add("기타");


        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry((float) eatPrice,0));
        barEntries.add(new BarEntry((float)medicalPrice,1));
        barEntries.add(new BarEntry((float)storePrice,2));
        barEntries.add(new BarEntry((float)transPrice,3));
        barEntries.add(new BarEntry((float)dressPrice,4));
        barEntries.add(new BarEntry((float)etcPrice,5));


        barChart.setMaxVisibleValueCount(60);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawingCacheBackgroundColor(Color.WHITE);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setTextColor(Color.WHITE);


        setData();

        cal.add(Calendar.DATE,Integer.parseInt(nowDayTimetext));
        //바 그래프가 클릭되었을때
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            //뭐가 눌러졌는지 확인하는것.
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

                if (entry == null)
                    return;

                if(checkStartDate.toString().length()!= 0 && checkEndDate.toString().length()!=0)//두개 다 빈칸이 아닐때.
                {
                    String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
                    Cursor c2 = db.selectData(selectSql2);
                    String selectSql3 = "select Name, Price, IO, Class, Date from BasicBudget";
                    Cursor c3 = db.selectData(selectSql3);


                    String textSum="";
                    for (int i2 = 0; i2 < c2.getCount(); i2++) {
                        c2.moveToNext();
                        dbcheckDate = c2.getString(0);
                        checkPrice = c2.getInt(1);
                        checkName = c2.getString(2);
                        checkIO = c2.getInt(3);
                        checkClass = c2.getString(4);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                        try
                        {
                            //시간으로 다시 바꾼것.
                            changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

                        }catch(ParseException e)
                        {
                            e.printStackTrace();
                        }

                        if(checkIO==1) {
                            if (checkClass.equals(barGxLabels.get(entry.getXIndex())))//값이 같으면. 같은 클래스면
                            {
                                int compare = changeDate.compareTo(changeStartDate);//기존값이 시작값보다 클때
                                int compare2 = changeDate.compareTo(changeEndDate);//기존값이 끝값보다 작을 때
                                if(compare>=0 && compare2<=0)
                                    textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                    }//pieGxLabel : 이거는 분류.

                    for (int i3 = 0; i3 < c3.getCount(); i3++) {
                        c3.moveToNext();
                        checkName = c3.getString(0);
                        checkPrice = c3.getInt(1);
                        checkIO = c3.getInt(2);
                        checkClass = c3.getString(3);
                        dbcheckDate = c3.getString(4);

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);


                        if(checkIO==1) {

                            if (checkClass.equals(barGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                        showDetail.setText(textSum);
                    }//basicBudget
                }//시간설정 되었을떄.
                else {

                    dateSettingText.setText(" " + nowDayTimetext2+ "월 ");
                    String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
                    Cursor c2 = db.selectData(selectSql2);
                    String selectSql3 = "select Name, Price, IO, Class, Date from BasicBudget";
                    Cursor c3 = db.selectData(selectSql3);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                    String textSum = "";

                    for (int i2 = 0; i2 < c2.getCount(); i2++) {
                        c2.moveToNext();
                        dbcheckDate = c2.getString(0);
                        checkPrice = c2.getInt(1);
                        checkName = c2.getString(2);
                        checkIO = c2.getInt(3);
                        checkClass = c2.getString(4);
                        try {
                            //시간으로 다시 바꾼것.
                            changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (checkIO == 1) {
                            if (checkClass.equals(barGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                int compare = changeDate.compareTo(nowlessTime);//기존값이 시작값보다 클때
                                int compare2 = changeDate.compareTo(nowTime);//기존값이 끝값보다 작을 때
                                if (compare >= 0 && compare2 <= 0) {
                                    textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                                }//값 비교하는거. 현재 시간에 맞는지 아닌지.
                            }
                        }
                    }//pieGxLabel : 이거는 분류.
                    for (int i3 = 0; i3 < c3.getCount(); i3++) {
                        c3.moveToNext();
                        checkName = c3.getString(0);
                        checkPrice = c3.getInt(1);
                        checkIO = c3.getInt(2);
                        checkClass = c3.getString(3);
                        dbcheckDate = c3.getString(4);


                        if (checkIO == 1) {
                            if (checkClass.equals(barGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                    }//pieGxLabel : 이거는 분류.
                    showDetail.setText(textSum);
                }//시간설정 안되었을때.
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //원 그래프 눌려졌을때
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry entry, int dataSetIndex, Highlight h) {

                if (entry == null)
                    return;

                String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
                Cursor c2 = db.selectData(selectSql2);

                String selectSql3 = "select Name, Price, IO, Class, Date from BasicBudget";
                Cursor c3 = db.selectData(selectSql3);
                String textSum="";

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

                if(checkStartDate.toString().length()!= 0 && checkEndDate.toString().length()!=0)//두개 다 빈칸이 아닐때.
                {
                    for (int i = 0; i < c2.getCount(); i++) {
                        c2.moveToNext();
                        dbcheckDate = c2.getString(0);
                        checkPrice = c2.getInt(1);
                        checkName = c2.getString(2);
                        checkIO = c2.getInt(3);
                        checkClass = c2.getString(4);

                        try
                        {
                            //시간으로 다시 바꾼것.
                            changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

                        }catch(ParseException e)
                        {
                            e.printStackTrace();
                        }

                        if (checkIO == 1) {
                            if (checkClass.equals(pieGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                int compare = changeDate.compareTo(changeStartDate);//기존값이 시작값보다 클때
                                int compare2 = changeDate.compareTo(changeEndDate);//기존값이 끝값보다 작을 때
                                if(compare>=0 && compare2<=0)
                                    textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                    }//pieGxLabel : 이거는 분류.
                    for (int i3 = 0; i3 < c3.getCount(); i3++) {
                        c3.moveToNext();
                        checkName = c3.getString(0);
                        checkPrice = c3.getInt(1);
                        checkIO = c3.getInt(2);
                        checkClass = c3.getString(3);
                        dbcheckDate = c3.getString(4);

                        if (checkIO == 1) {
                            if (checkClass.equals(pieGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                    }//pieGxLabel : 이거는 분류.
                }//시간 값이 들어 왔을때
                else {

                    dateSettingText.setText(" " + nowDayTimetext2+ "월 ");
                    for (int i = 0; i < c2.getCount(); i++) {
                        c2.moveToNext();
                        dbcheckDate = c2.getString(0);
                        checkPrice = c2.getInt(1);
                        checkName = c2.getString(2);
                        checkIO = c2.getInt(3);
                        checkClass = c2.getString(4);

                        try {
                            //시간으로 다시 바꾼것.
                            changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (checkIO == 1) {
                            if (checkClass.equals(pieGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                int compare = changeDate.compareTo(nowlessTime);//기존값이 시작값보다 클때
                                int compare2 = changeDate.compareTo(nowTime);//기존값이 끝값보다 작을 때
                                if (compare >= 0 && compare2 <= 0) {
                                    textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                                }
                            }
                        }//pieGxLabel : 이거는 분류.
                    }//budget일때
                    for (int i3 = 0; i3 < c3.getCount(); i3++) {
                        c3.moveToNext();
                        checkName = c3.getString(0);
                        checkPrice = c3.getInt(1);
                        checkIO = c3.getInt(2);
                        checkClass = c3.getString(3);
                        dbcheckDate = c3.getString(4);



                        if (checkIO == 1) {
                            if (checkClass.equals(pieGxLabels.get(entry.getXIndex())))//값이 같으면.
                            {
                                textSum += checkName + " : " + checkPrice + " , 시간 : " + dbcheckDate + "\n";
                            }
                        }
                    }//basicBudget일때
                }//시간값이 들어오지 않았을때.
                showDetail.setText(textSum);
            }

            @Override
            public void onNothingSelected() {

            }
        });
        //버튼 원,막대 그래프 서로 스위치해서 보여주는것
        O_B_Type.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch(checkType)
                {
                    case 0:

                        barChart.setVisibility(LinearLayout.INVISIBLE);
                        pieChart.setVisibility(LinearLayout.VISIBLE);
                        O_B_Type.setBackgroundResource(R.drawable.chartbarbtn);
                        setPieDataInfo();
                        setPieData();
                        checkType=1;
                        break;
                    case 1:

                        setPieDataInfo();
                        setBarData();
                        setData();
                        O_B_Type.setBackgroundResource(R.drawable.chartpiebtn);
                        barChart.setVisibility(LinearLayout.VISIBLE);
                        pieChart.setVisibility(LinearLayout.INVISIBLE);
                        checkType=0;
                        break;
                }
            }
        });
    }
    //막대 그래프 데이터 셋팅하는것
    public void setBarData()
    {

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry((float) eatPrice,0));
        barEntries.add(new BarEntry((float)medicalPrice,1));
        barEntries.add(new BarEntry((float)storePrice,2));
        barEntries.add(new BarEntry((float)transPrice,3));
        barEntries.add(new BarEntry((float)dressPrice,4));
        barEntries.add(new BarEntry((float)etcPrice,5));

    }
    //막대 그래프 데이터 세팅하는거.
    public void setData() {

        BarDataSet set1 = new BarDataSet(barEntries, "Price");
        set1.setBarSpacePercent(35f);
        //int[] colorArray =  { Color.rgb(207,200,238), Color.rgb(206,224,232), Color.rgb(248,224,244), Color.rgb(22,131,228),Color.rgb(116,104,147),Color.rgb(221,64,126)};
        int[] colorArray =  { Color.rgb(207,200,238), Color.rgb(22,131,228), Color.rgb(248,224,244),Color.rgb(221,64,126), Color.rgb(206,224,232),Color.rgb(128,89,162)};

        set1.setColors(ColorTemplate.createColors(colorArray));
        //set1.setColors(ColorTemplate.COLORFUL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(barGxLabels, dataSets);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getAxisRight().setTextColor(Color.WHITE);
        barChart.setData(data);
    }
    //파이 그래프 데이터 세팅하는거
    public void setPieDataInfo()
    {
        showDetail.setText("");
        eatPrice=0;
        medicalPrice=0;
        storePrice=0;
        transPrice=0;
        dressPrice=0;
        etcPrice=0;

        String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
        Cursor c2 = db.selectData(selectSql2);
        String selectSql3 = "select Name, Price, IO, Class, Date from BasicBudget";
        Cursor c3 = db.selectData(selectSql3);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

        if(checkStartDate.toString().length()!= 0 && checkEndDate.toString().length()!=0)//두개 다 빈칸이 아닐때.
        {
            for (int i = 0; i < c2.getCount(); i++) {
                c2.moveToNext();
                dbcheckDate = c2.getString(0);
                checkPrice = c2.getInt(1);
                checkName = c2.getString(2);
                checkIO = c2.getInt(3);
                checkClass = c2.getString(4);

                try
                {
                    changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.
                }catch(ParseException e)
                {
                    e.printStackTrace();
                }

                if (checkIO == 1) {
                    int compare = changeDate.compareTo(changeStartDate);//기존값이 시작값보다 클때
                    int compare2 = changeDate.compareTo(changeEndDate);//기존값이 끝값보다 작을 때
                    if(compare>=0 && compare2<=0){
                        if (checkClass.equals("식비")) {
                            eatPrice += checkPrice;
                        } else if (checkClass.equals("의료비")) {
                            medicalPrice += checkPrice;
                        } else if (checkClass.equals("적금")) {
                            storePrice += checkPrice;
                        } else if (checkClass.equals("교통비")) {
                            transPrice += checkPrice;
                        } else if (checkClass.equals("의류비")) {
                            dressPrice += checkPrice;
                        } else if (checkClass.equals("기타")) {
                            etcPrice += checkPrice;
                        }
                    }
                }
            }//pieGxLabel : 이거는 분류.
            for (int i3 = 0; i3 < c3.getCount(); i3++) {
                c3.moveToNext();
                checkName = c3.getString(0);
                checkPrice = c3.getInt(1);
                checkIO = c3.getInt(2);
                checkClass = c3.getString(3);
                dbcheckDate = c3.getString(4);
                changeDate=null;

                if (checkIO == 1) {

                    if (checkClass.equals("식비")) {
                        eatPrice += checkPrice;
                    } else if (checkClass.equals("의료비")) {
                        medicalPrice += checkPrice;
                    } else if (checkClass.equals("적금")) {
                        storePrice += checkPrice;
                    } else if (checkClass.equals("교통비")) {
                        transPrice += checkPrice;
                    } else if (checkClass.equals("의류비")) {
                        dressPrice += checkPrice;
                    } else if (checkClass.equals("기타")) {
                        etcPrice += checkPrice;
                    }

                }
            }//pieGxLabel : 이거는 분류.
        }//시간 값이 들어 왔을때
        else {
            for (int i = 0; i < c2.getCount(); i++) {
                c2.moveToNext();
                dbcheckDate = c2.getString(0);
                checkPrice = c2.getInt(1);
                checkName = c2.getString(2);
                checkIO = c2.getInt(3);
                checkClass = c2.getString(4);

                try {
                    //시간으로 다시 바꾼것.
                    changeDate = dateFormat.parse(dbcheckDate);//설정된 디비 데이터 시간을 확인.

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (checkIO == 1) {
                    int compare = changeDate.compareTo(nowlessTime);//기존값이 시작값보다 클때
                    int compare2 = changeDate.compareTo(nowTime);//기존값이 끝값보다 작을 때
                    if(compare>=0 && compare2<=0){
                        if (checkClass.equals("식비")) {
                            eatPrice += checkPrice;
                        } else if (checkClass.equals("의료비")) {
                            medicalPrice += checkPrice;
                        } else if (checkClass.equals("적금")) {
                            storePrice += checkPrice;
                        } else if (checkClass.equals("교통비")) {
                            transPrice += checkPrice;
                        } else if (checkClass.equals("의류비")) {
                            dressPrice += checkPrice;
                        } else if (checkClass.equals("기타")) {
                            etcPrice += checkPrice;
                        }
                    }

                }
            }//pieGxLabel : 이거는 분류.
            for (int i3 = 0; i3 < c3.getCount(); i3++) {
                c3.moveToNext();
                checkName = c3.getString(0);
                checkPrice = c3.getInt(1);
                checkIO = c3.getInt(2);
                checkClass = c3.getString(3);
                dbcheckDate = c3.getString(4);


                if (checkIO == 1) {

                    if (checkClass.equals("식비")) {
                        eatPrice += checkPrice;
                    } else if (checkClass.equals("의료비")) {
                        medicalPrice += checkPrice;
                    } else if (checkClass.equals("적금")) {
                        storePrice += checkPrice;
                    } else if (checkClass.equals("교통비")) {
                        transPrice += checkPrice;
                    } else if (checkClass.equals("의류비")) {
                        dressPrice += checkPrice;
                    } else if (checkClass.equals("기타")) {
                        etcPrice += checkPrice;
                    }


                }
            }//basicBudget일때
        }//시간값이 들어오지 않았을때.
    }
    //파이 그래프 데이터 세팅
    public void setPieData()
    {
        pieGxLabels = new ArrayList<String>();
        pieGxLabels.add("식비");
        pieGxLabels.add("의료비");
        pieGxLabels.add("적금");
        pieGxLabels.add("교통비");
        pieGxLabels.add("의류비");
        pieGxLabels.add("기타");

        int sum = eatPrice+medicalPrice+storePrice+transPrice+dressPrice+etcPrice;

        yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry((float)eatPrice/(float)sum *(100),0));
        yvalues.add(new Entry((float)medicalPrice/(float)sum*(100),1));
        yvalues.add(new Entry((float)storePrice/(float)sum*(100),2));
        yvalues.add(new Entry((float)transPrice/(float)sum*(100),3));
        yvalues.add(new Entry((float)dressPrice/(float)sum*(100),4));
        yvalues.add(new Entry((float)etcPrice/(float)sum*(100),5));


        PieDataSet dataSet = new PieDataSet(yvalues,"Pie chart");
        PieData data = new PieData(pieGxLabels,dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(true);//원형 안에 구멍 뚫는가/안 뚫는가
        pieChart.setTransparentCircleRadius(25f);//구멍 반지름 제어 이게 커지면 투명한부분 증가
        pieChart.setHoleRadius(25f);//구멍 반지름 제어


        int[] colorArray =  { Color.rgb(207,200,238), Color.rgb(22,131,228), Color.rgb(248,224,244),Color.rgb(221,64,126), Color.rgb(206,224,232),Color.rgb(128,89,162)};
        dataSet.setColors(ColorTemplate.createColors(colorArray));
        //dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.DKGRAY);
    }
    //시간 설정버튼 눌려졌을때
    public void DateInput()
    {
        checkDate = new CheckDate(Graph.this,inputClick,cancelClick);
        checkDate.setCancelable(true);
        checkDate.getWindow().setGravity(Gravity.CENTER);
        checkDate.show();
    }
    //시간설정버튼에서 확인 버튼 눌러졌을때
    private View.OnClickListener inputClick = new View.OnClickListener() {
        public void onClick(View v) {

            checkStartDate = checkDate.startDate.getText().toString();
            checkEndDate = checkDate.endDate.getText().toString();
            changeStartDate = null;
            changeEndDate = null;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            try
            {
                changeStartDate = dateFormat.parse(checkStartDate);
                changeEndDate = dateFormat.parse(checkEndDate);

            }catch(ParseException e)
            {
                e.printStackTrace();

            }

            int compare = changeStartDate.compareTo(changeEndDate);

            if(!checkDate.startDate.getText().toString().equals("") && !checkDate.startDate.getText().toString().equals(""))//두개 다 빈칸이 아닐때. 값을 설정 했을때
            {

                if(compare<=0) {

                    setPieDataInfo();
                    setPieData();
                    setBarData();
                    setData();
                    dateSettingText.setText(""+checkStartDate + "  ~  " + checkEndDate );
                    checkDate.dismiss();
                }
                else if(compare>0){
                    InitText();
                    checkDate.dismiss();
                }

            }else if(checkDate.startDate.getText().toString().equals(null) || checkDate.startDate.getText().toString().equals(null))//둘 중 한 칸이 비어있을떄
            {
                InitText();
                checkDate.dismiss();
            }
            checkDate.dismiss();
        }
    };
    //취소버튼 눌려졌을때
    private View.OnClickListener cancelClick = new View.OnClickListener() {
        public void onClick(View v) {
            checkDate.dismiss();
        }
    };
    //시간설정 초기화 할때 쓰는 메소드
    public void InitText()//시간 설정쪽 텍스트 초기화 해주는것.
    {
        checkStartDate="";
        checkEndDate="";
        dateSettingText.setText(" " + nowDayTimetext2+ "월");
        changeStartDate=null;
        changeEndDate=null;
    }

}