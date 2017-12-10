package org.androidtown.account;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

//메모 쓰려면 달력의 날짜를 클릭하고 하단의 +버튼을 클릭하면 자동으로 날짜가 입력된 메모 입력 화면이 뜹니다.

public class MemoActivity extends AppCompatActivity {

    DataBase db;
    CustomDialog myDialog;
    SqlStatement sqlstatement = new SqlStatement();
    /*
   내용 보이는 텍스트뷰
    */
    TextView textView;
    Button memoModify;
    Button memoDelete;

    CalendarView calendar;

    Button addBtn;

    //달력 클릭 시 년,월,날짜 받을 변수
    int ClickYear;
    int ClickMonth;
    int Clickday;
    String ClickToday;
    String Today;
    String Memo;
    String Money;

    //수정인지 내용 삽입인지
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        db = new DataBase("account", this);

        textView = (TextView) findViewById(R.id.memoviewer);
        calendar = (CalendarView)findViewById(R.id.CalendarView);
        addBtn = (Button)findViewById(R.id.addButton);
        memoModify = (Button)findViewById(R.id.memomodify);
        memoDelete = (Button)findViewById(R.id.memoDelete);

        TabFragment_memo tabFragment = new TabFragment_memo();
//탭바를 프레그먼트 형식으로 만들어서 프레그먼트 띄우는 코드
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.meme_container, tabFragment);
        ft.commit();
//달력 클릭 이벤트 확인 받는 리스너
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfmonth) {
                String sql;
                Cursor cursor;

                Memo = null;
                Money = null;

                ClickYear = year;
                ClickMonth = month+1;
                Clickday = dayOfmonth;
                ClickToday = String.valueOf(ClickMonth)+"월"+String.valueOf(Clickday)+"일";
                Today = String.valueOf(ClickYear)+"년"+String.valueOf(ClickMonth)+"월"+String.valueOf(Clickday)+"일";
                /*db에 그 날짜에 값이 있는지 확인하는 코드
                **메모가 있으면 하단에 메모 표시 없으면 하단에 날짜만 표시
                 */
                cursor = db.selectData(sqlstatement.Memo_Select());

                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToNext();
                        if (cursor.getString(0).equals(ClickToday) && cursor.getInt(1) == ClickYear) {
                            Memo = cursor.getString(2);
                            Money = cursor.getString(3);
                        }

                    }
                cursor.close();

                if(Memo != null || Money != null) {
                    println("\n\n" + "                " +ClickToday+
                            "\n" + "                " + Money +
                            "\n" + "                " + Memo);
                }
                else {
                    println("");
                }

            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 1;//어떤 버튼인지 확인 해서 db에 인설트할지 업데이트 할지 확인하는 코드
                Dialog(Today);
            }

        });
        //메모 업데이트
        memoModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor;
                String update_Memo = null;
                String update_Money = null;
                String update_Title = null;

                check = 2; //어떤 버튼인지 확인 해서 db에 인설트할지 업데이트 할지 확인하는 코드

                cursor = db.selectData(sqlstatement.Memo_Select());

                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    if (cursor.getString(0).equals(ClickToday) && cursor.getInt(1) == ClickYear) {
                        update_Memo = cursor.getString(2);
                        update_Money = cursor.getString(3);
                        update_Title = cursor.getString(4);
                    }
                }
                cursor.close();
                //기존에 있는 메모 내용 불러와서 다이어로그에 값 표시해주는 코드
                myDialog = new CustomDialog(MemoActivity.this,
                        Today, update_Memo, update_Money, update_Title, inputClick, exitClick);

                myDialog.setCancelable(true);
                myDialog.getWindow().setGravity(Gravity.CENTER);
                myDialog.show();
            }

        });
        memoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql;
                sql = sqlstatement.Memo_Delete(ClickToday);
                db.deleteData(sql);
                println("");
            }
        });


    }
    public void println(String Data)
    {
        textView.setText(Data+"\n");
    }

    public void Dialog(String Date){
        Date = Today;
        myDialog = new CustomDialog(MemoActivity.this,
                Date, inputClick, exitClick);

        myDialog.setCancelable(true);
        myDialog.getWindow().setGravity(Gravity.CENTER);
        myDialog.show();
    }
    private View.OnClickListener inputClick = new View.OnClickListener() {

        public void onClick(View v) {

            String Insert_Title;
            String Insert_Memo;
            String Insert_Money;
            String sql;
            Cursor cursor;

            if(check == 1) {

                Insert_Memo = myDialog.InputEdit.getText().toString();
                Insert_Money = myDialog.dialogAccount.getText().toString();
                Insert_Title = myDialog.dialogTitle.getText().toString();

                sql = sqlstatement.Memo_Insert(ClickToday, ClickYear, Insert_Memo, Insert_Money, Insert_Title);
                db.insertData(sql);
            }
            else if(check == 2){

                Insert_Memo = myDialog.InputEdit.getText().toString();
                Insert_Money = myDialog.dialogAccount.getText().toString();
                Insert_Title = myDialog.dialogTitle.getText().toString();

                sql = sqlstatement.Memo_Update(ClickToday, ClickYear, Insert_Memo, Insert_Money, Insert_Title);
                db.updateData(sql);
            }
            myDialog.dismiss();
        }
    };
    private View.OnClickListener exitClick = new View.OnClickListener(){
            public void onClick(View v){
                myDialog.dismiss();
            }
        };

}
