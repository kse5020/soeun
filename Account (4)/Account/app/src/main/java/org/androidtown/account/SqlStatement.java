package org.androidtown.account;

/**
 * Created by A35X on 2017-11-24.
 */

public class SqlStatement {
    public static final String drop_sql =  "drop table if exists Memo";
    public static final String drop_sql02 = "drop table if exists Budget";
    public static final String drop_sql03 = "drop table if exists Setting";
    public static final String drop_sql04 = "drop table if exists Accumulation";
    public static final String drop_sqlBalance = "drop table if exists Balance";

    public static final String sql_01 = "create table if not exists "
            + "Memo"
            + " (Day Text, Year integer, Text text, Money Text, Title Text)";

    public static final String sql_02 = "create table if not exists "//0수입 1 지출
            + "Budget"
            + " (id integer PRiMARY KEY, Date timestamp, Value integer , Name text , IO integer,Class text)";
    //식비,의료비,적금,교통비,의류비,기타
//id(배열같은느낌에 순서), 날짜, 값(얼마인지),이름(보람정형외과 이런거),IO(input,output :  0 : 수입,  1 : 지출 구분하기위해서),
//Class 분류하는거.
// 수입 : 월급,적금,기타
// 지출 : 식비,의료비,적금,교통비,의류비,기타  이렇게 분류.(String값으로 지정해놨음.)
//지난번에도 얘기했듯이, id를 primary key로 해둬서 이게 수입 지출인지 구분하는건 사실상 불가능함.. id값 그러니까 배열로 0,1,2, 이런 느낌이긴한데 우리는 사실상 쓰이지는 않음.
//전체를 한꺼번에 보여주는걸 쓰지 않는한? IO값으로 0이면 수입, 1이면 지출. 이런식으로 표현해내는거.

    public static final String sql_03 = "create table if not exists "
            + "Setting"
            + " (Location integer PRiMARY KEY, SetIn text, SetOut text)";

    public static final String sql_04 = "create table if not exists "
            + "Accumulation"
            + " (id integer PRiMARY KEY, Accumulation text)";
    public static final String sql_05 = "create table if not exists "
            + "BasicBudget"
            + " (Location integer PRiMARY KEY, Name text, Price integer,IO integer,Class text ,Date timestamp)";//위치, 이름, 값, 수입 : 0 지출 : 1 , 종류
    public static final String sql_balance = "create table if not exists"
            +"balance"+"(Location integer balance)"; //이번달 잔액만 표시


    public static final String Select_Sql = "select Location, Text, Month from Memo";

    public String insertBudgetSql(String text,int text2,int text3, int location, String text4,String date)
    {
        int position;
        String statement = null;
        String sql;
        String name;
        int price;
        int IO;
        String Date;
        String Class;
        position = location;
        name = text;
        price = text2;
        IO = text3;
        Class = text4;
        Date = date;
        sql = "insert into BasicBudget (Location, Name, Price,IO, Class,Date) values ("+ position +", '"+ name +"', '"+ price +"', '"+IO+"', '"+Class+"', '"+Date+"')";
        statement = sql;

        return statement;
    }
    public String updateBasicBudgetSql(String text,int text2, int location, String text4,String date)
    {
        int position;
        String statement = null;
        String sql;
        String name;
        int price;
        String Date;
        String Class;
        position = location;
        name = text;
        price = text2;
        Class = text4;
        Date = date;

        sql = "UPDATE BasicBudget SET Name ='"+ name +"' , Price =" +price +", Class ='"+ Class +"', Date ='"+Date+ "' WHERE Location ="+ position;
        statement = sql;

        return statement;
    }
    public String insertBudget(int location,String text,int IO,int value, String date, String classType)
    {
        String statement = null;
        String sql;
        String name;
        String Class;
        int price;
        int IOvalue;
        int position;
        String Day;

        name = text;
        price = value;
        IOvalue = IO;
        Day = date;
        Class = classType;
        position=location;

        sql = "insert into Budget (id, Name, Value,IO, Date, Class) values ("+ position +", '"+ name +"', '"+ price +"', '"+IOvalue+"', '"+Day+"', '"+Class+"')";
        statement = sql;

        return statement;
    }
    //메모 insert문
    public String Memo_Insert(String Day, int Year, String Text, String Money, String Title){
        String statement = null;
        String sql = "insert into Memo (Day, Year, Text, Money, Title) values "+
                         "('"+ Day +"', '"+ Year +"', '"+ Text +"', '"+ Money +"', '"+ Title +"')";
        statement = sql;
        return statement;
    }
    //메모 업데이트
    public String Memo_Update(String Day, int Year, String Text, String Money, String Title){
        String statement = null;
        String sql = "update Memo set (Text, Money, Title) = ('"
                + Text +"', '"+ Money +"', '"+ Title +"') where (Day) = '"+ Day +"'";
        //+ Text +"', '"+ Money +"', '"+ Title +"') where (Day, Year) = '"+ Day +"', '"+ Year +"'";
        statement = sql;
        return statement;
    }

    public String SelectBasicInputSql(int Locate)
    {
        String rtValue = null;
        String sql;
        int checkLocate = 0;
        checkLocate = Locate;

        switch (checkLocate)
        {
            case 1:
                sql = "select Location,Name,Price,IO,Class from BasicBudget";
                rtValue = sql;
                break;
        }

        return rtValue;
    }
    //memo 디비 조회
    public String Memo_Select()
    {
        String rtValue = null;
        String sql;

        sql = "select Day, Year, Text, Money, Title from Memo";
        rtValue = sql;

        return rtValue;
    }
    public String Memo_Delete(String Day){
        String sql;

        sql = "delete from Memo where Day = '"+ Day+"'";

        return sql;
    }
    public String InsertBudget(int location,String text,int IO,int value, String date, String classType)
    //public String insertBudget(String text,int IO,int value, String date, String classType)
    {
        String statement = null;
        String sql;
        String name;
        String Class;
        int price;
        int IOvalue;
        int position;
        String Day;

        name = text;
        price = value;
        IOvalue = IO;
        Day = date;
        Class = classType;
        position=location;

        //sql = "insert into Budget (id, Name, Value,IO, Date, Class) values ("+ position +", '"+ name +"', '"+ price +"', '"+IOvalue+"', '"+Day+"', '"+Class+"')";
        sql = "insert into Budget (Name, Value,IO, Date, Class) values (" +"'"+ name +"', '"+ price +"', '"+IOvalue+"', '"+Day+"', '"+Class+"')";
        statement = sql;

        return statement;
    }

}
