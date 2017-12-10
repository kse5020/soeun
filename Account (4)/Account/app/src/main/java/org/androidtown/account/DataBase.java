package org.androidtown.account;

/**
 * Created by A35X on 2017-11-24.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DataBase {

    private Context context = null;

    private SQLiteDatabase database = null;
    //DB오픈
    public DataBase(String dbName, Context context) {
        this.context = context;
        this.database = this.context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }
    //테이블 생성
    public void createTable(String sqlStatement) {

        this.database.execSQL(sqlStatement);
    }
    //테이블 없애는 코드
    public void dropTable(String sqlStatement)
    {
        this.database.execSQL(sqlStatement);
    }
    //테이블에 있는 칼럼들 지우는 코드
    public void deleteData(String sqlStatement) { this.database.execSQL(sqlStatement);}
    //테이블 칼럼에 값 입력하는 코드
    public void insertData(String sqlStatement)
    {
        this.database.execSQL(sqlStatement);
    }
    //칼럼에 있는 값들 수정하는 코드
    public void updateData(String sqlStatement) { this.database.execSQL(sqlStatement);}

    //테이블에 있는 값들 확인하는 코드
    public Cursor selectData(String sqlStatement){
        Cursor c = this.database.rawQuery(sqlStatement, null);
        return c;
    }

    public void close() {
        if (null != this.database) {
            this.database.close();
        }
    }
}
