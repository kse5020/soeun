package org.androidtown.account;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class CheckDate extends Dialog { //시간체크하는 다이얼로그

    Button startDatebtn;
    Button endDatebtn;
    Button btndateOK;
    Button btndateNo;
    TextView startDate;
    TextView endDate;
    int id;
    private View.OnClickListener cancelClick;
    private View.OnClickListener inputClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_date);
        startDatebtn = (Button) findViewById(R.id.startDatebtn);
        endDatebtn = (Button) findViewById(R.id.endDatebtn);
        btndateOK=(Button)findViewById(R.id.dateOK);
        startDate = (TextView)findViewById(R.id.startDate);
        endDate = (TextView)findViewById(R.id.endDate);
        btndateNo = (Button)findViewById(R.id.dateNo);

        //시작 날짜 눌렀을때
        startDate.setText("");
        endDate.setText("");
        startDatebtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                id=0;
                DialogDatePicker(id);
            }
        });
        //끝나는 날짜 눌렀을때
        endDatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id=1;
                DialogDatePicker(id);
            }
        });

        //확인,취소 버튼 눌렸을때
        if (btndateOK != null && btndateNo != null) {
            btndateOK.setOnClickListener(inputClick);
            btndateNo.setOnClickListener(cancelClick);
        } else if (btndateOK != null && btndateNo == null) {
            btndateOK.setOnClickListener(inputClick);
        } else if (btndateOK == null && btndateNo != null) {
            btndateNo.setOnClickListener(cancelClick);
        }

    }

    //체크데이터 확인,취소 버튼 눌렀을때
    public CheckDate(Context context, View.OnClickListener inputClick, View.OnClickListener exitClick) {
        super(context);
        this.inputClick = inputClick;
        this.cancelClick = exitClick;
    }
    //달력 불러오는 로그
    public void DialogDatePicker(int id) {
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);
        final int checkId=id;
        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    // onDateSet method
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_selected =
                                String.valueOf(year) + "-" + String.valueOf(monthOfYear + 1) + "-"
                                        + String.valueOf(dayOfMonth);

                        if (checkId == 0) {
                            startDate.setText(date_selected);
                        } else
                            endDate.setText(date_selected);

                    }
                };
        new DatePickerDialog(getContext(), mDateSetListener, cyear, cmonth, cday).show();

    }
}