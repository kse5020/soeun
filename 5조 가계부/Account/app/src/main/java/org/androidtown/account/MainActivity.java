package org.androidtown.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase("account",this);
       // db.dropTable(SqlStatement.sql_01);
       // db.dropTable(SqlStatement.sql_02);
       // db.dropTable(SqlStatement.sql_03);
        db.createTable(SqlStatement.sql_01);
        db.createTable(SqlStatement.sql_02);
        db.createTable(SqlStatement.sql_03);

        TabFragment tabFragment = new TabFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, tabFragment);
        ft.commit();

        //일정으로
        ImageButton Schedule = (ImageButton)findViewById(R.id.sche);
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                startActivity(intent);
            }
        });
        //기본 입/출금 관리로 이동
        ImageButton basicGraph = (ImageButton)findViewById(R.id.stat);

        ImageButton btnlist = (ImageButton) findViewById(R.id.btnlist);
       btnlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent =  new Intent(getApplicationContext(),Management.class);
               startActivity(intent);
           }
       });

        basicGraph.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent =  new Intent(getApplicationContext(),Graph.class);
                startActivity(intent);
            }
        });

    /*
    public void PrintData()
    {
        int balance=0;//잔액

        String selectSql2 = "select Date, Value, Name, IO, Class from Budget";
        Cursor c2 = db.selectData(selectSql2);
        TextView balanceView = (TextView)findViewById(R.id.balanceView);


        int checkIO;
        int checkPrice;


        for (int i = 0; i < c2.getCount(); i++) {//Budget일때
            c2.moveToNext();
            checkPrice = c2.getInt(1);
            checkIO = c2.getInt(3);


            if (checkIO == 1) {
                //ex) sellSum += checkPrice; //이런식으로.
                balance-=checkPrice;
            } //지출일때
            else  //수입일때
                balance+=checkPrice;

        }//Budget끝.

        String t = "";
        t = ""+balance+"원";


        balanceView.setText(t);

    }*/

    }
}
