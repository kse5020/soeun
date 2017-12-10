package org.androidtown.account;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Management extends AppCompatActivity implements View.OnClickListener {


    Cursor cursor;
    DataBase db;
    DBAdapter dbAdapter;

    int mYear, mMonth, mDay;
    TextView mTxtDate;
    ListView listView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        button = (Button) findViewById(R.id.button);
        mTxtDate = (TextView)findViewById(R.id.textView);

        TabFragment_input tabFragment = new TabFragment_input();
       // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
       // ft.replace(R.id.manage_container, tabFragment);
       // ft.commit();

        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        listView = (ListView) findViewById(R.id.listView);

        listAdapter adapter = new listAdapter();

        listView.setAdapter(adapter);

        db = new DataBase("money",this);
        //cursor = db.selectData("select * from Budget");
        //dbAdapter = new DBAdapter(this,cursor);
        //listView.setAdapter(dbAdapter);

        //UpdateNow();

    }

    class listAdapter extends BaseAdapter{
        ArrayList<DBAdapter> items = new ArrayList<DBAdapter>();

        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(DBAdapter item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            itemView view1 = new itemView(getApplicationContext());

            DBAdapter item = items.get(i);
            view1.setlocation(item.getLocation());
            view1.setlike(item.getLike());
            view1.setname(item.getName());
            view1.setprice(item.getPrice());
            view1.setplace(item.getPlace());
            view1.setdate(item.getDate());

            return view1;
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case  R.id.button:
                // startActivity(new Intent(this, PopupActivity.class));
                break;

            default:
                break;
        }
    }


    public void mOnPopupClick(View v){
        // Intent intent = new Intent(this, PopupActivity.class);
        // startActivity(intent);
    }


    public void mOnClick(View v){
        switch(v.getId()) {


            case R.id.button3:


                new DatePickerDialog(Management.this, mDateSetListener, mYear, mMonth, mDay).show();

                break;
        }



    }

    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;

                    //UpdateNow();
                }
            };

    //   void UpdateNow(){
    //       mTxtDate.setText(String.format("%d/%d/%d",mYear,mMonth+1,mDay));
    //   }

}