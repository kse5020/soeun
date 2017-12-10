package org.androidtown.account;

/**
 * Created by A35X on 2017-12-10.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class itemView extends LinearLayout {

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;

    public itemView(Context context) {
        super(context);

        init(context);
    }

    public itemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listlayout,this,true);


        textView = (TextView) findViewById(R.id.location);
        textView2 = (TextView) findViewById(R.id.like);
        textView3 = (TextView) findViewById(R.id.name);
        textView4 = (TextView) findViewById(R.id.price);
        textView5 = (TextView) findViewById(R.id.place);
        textView6 = (TextView) findViewById(R.id.date);
    }

    public void setlocation(String location){
        textView.setText(location);
    }
    public void setlike(String like){
        textView2.setText(like);
    }
    public void setname(String name){
        textView3.setText(name);
    }
    public void setprice(String price){
        textView4.setText(price);
    }
    public void setplace(String place){
        textView5.setText(place);
    }
    public void setdate(String date){
        textView6.setText(date);
    }
}
