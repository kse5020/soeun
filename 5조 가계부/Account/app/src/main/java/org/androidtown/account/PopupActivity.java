
package org.androidtown.account;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PopupActivity extends Activity {


    DataBase db;
    SqlStatement sqlite = new SqlStatement();
    DatePickerDialog datePickerDialog;

    String sql;
    int Location;

    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        db = new DataBase("money",this);

        this.db.createTable(sqlite.sql_02);


        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
    }


    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;

                    UpdateNow();
                }
            };


    void UpdateNow(){
        TextView mTxtDate = (TextView)findViewById(R.id.textView9);
        mTxtDate.setText(String.format("%d/%d/%d",mYear,mMonth+1,mDay));
    }

    public void mOnClick(View v){
        switch(v.getId()) {


            case R.id.button3:


                datePickerDialog.show();

                break;
        }



    }

    public void mOnClose(View v) {
        //데이터 전달하기
        //    Intent intent = new Intent();
        //    intent.putExtra("result", "Close Popup");
        //    setResult(RESULT_OK, intent);

        TextView mTxtDate = (TextView)findViewById(R.id.textView9);
        EditText nEditText = (EditText)findViewById(R.id.editText2);
        EditText pEditText = (EditText)findViewById(R.id.editText);

        String note = nEditText.getText().toString();
        int prize= Integer.parseInt(pEditText.getText().toString());
        String date = mTxtDate.getText().toString();


        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        String like = spinner2.getSelectedItem().toString();

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String IO = spinner.getSelectedItem().toString();

        if(IO.equals("지출")) {
            sql = sqlite.InsertBudget(Location, note, 1, prize, date, like);
            db.insertData(sql);
        }else{
            sql = sqlite.InsertBudget(Location, note, 0, prize, date, "입금");
            db.insertData(sql);
        }

        finish();
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }



}