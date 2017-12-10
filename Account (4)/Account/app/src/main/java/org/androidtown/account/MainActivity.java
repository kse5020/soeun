package org.androidtown.account;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DataBase db;
    SqlStatement sqlstatement = new SqlStatement();

    Date nowTime=null;
    Date nowlessTime=null;



    SQLiteDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase("account",this);
        this.db.dropTable(SqlStatement.drop_sql);
        this.db.dropTable(SqlStatement.drop_sql02);
        this.db.dropTable(SqlStatement.drop_sql03);
        this.db.dropTable(SqlStatement.drop_sqlBalance);
        db.createTable(SqlStatement.sql_01);
        db.createTable(SqlStatement.sql_02);
        db.createTable(SqlStatement.sql_03);
        db.createTable(SqlStatement.sql_balance);
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
        ImageButton basicInput = (ImageButton)findViewById(R.id.Initial);
        ImageButton basicGraph = (ImageButton)findViewById(R.id.stat);

        basicInput.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent =  new Intent(getApplicationContext(),BasicInputOutput.class);
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


        //PrintData();

    }

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
