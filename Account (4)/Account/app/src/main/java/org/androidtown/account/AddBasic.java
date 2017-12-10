package org.androidtown.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddBasic extends Dialog  //BasicInputOutput(기본입출력) 수입,지출,업데이트 다이얼로그 창.
{

    private View.OnClickListener inputClick; //확인 버튼
    private View.OnClickListener cancelClick; //취소 버튼

    LinearLayout backImage;
    EditText nameWrite;
    EditText priceWrite;
    TextView addTitle;
    Button input;
    Button cancel;
    RadioGroup radioGroup;
    //checkBox는 지출부분 선택창, radioButton은 수입부분 선택창
    RadioButton radioButton0;
    RadioButton radioButton1;
    RadioButton radioButton2;

    CheckBox checkBox0;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;


    private String Title;
    private String Input = "Input";
    private String Output = "Output";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basic);
        nameWrite = (EditText) findViewById(R.id.NameWrite);
        priceWrite = (EditText) findViewById(R.id.PriceWrite);
        input = (Button) findViewById(R.id.Input);
        cancel = (Button) findViewById(R.id.cancel);
        addTitle = (TextView) findViewById(R.id.addTitle);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup0);
        radioButton0 = (RadioButton) findViewById(R.id.radio0);
        radioButton1 = (RadioButton) findViewById(R.id.radio1);
        radioButton2 = (RadioButton) findViewById(R.id.radio2);
        checkBox0 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox6);
        backImage = (LinearLayout)findViewById(R.id.backLayout);
        addTitle.setText(Title);

        //확인,취소 버튼이 눌렸을떄 반응
        if (input != null && cancel != null) {
            input.setOnClickListener(inputClick);
            cancel.setOnClickListener(cancelClick);
        } else if (input != null && cancel == null) {
            input.setOnClickListener(inputClick);
        } else if (input == null && cancel != null) {
            cancel.setOnClickListener(cancelClick);
        }
        //지출부분은 checkBox로 설정한거라서 나머지 부분은 선택해제
        checkBox0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox1.setChecked(false);
                                                         checkBox2.setChecked(false);
                                                         checkBox3.setChecked(false);
                                                         checkBox4.setChecked(false);
                                                         checkBox5.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox0.setChecked(false);
                                                         checkBox2.setChecked(false);
                                                         checkBox3.setChecked(false);
                                                         checkBox4.setChecked(false);
                                                         checkBox5.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox0.setChecked(false);
                                                         checkBox1.setChecked(false);
                                                         checkBox3.setChecked(false);
                                                         checkBox4.setChecked(false);
                                                         checkBox5.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox0.setChecked(false);
                                                         checkBox1.setChecked(false);
                                                         checkBox2.setChecked(false);
                                                         checkBox4.setChecked(false);
                                                         checkBox5.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox0.setChecked(false);
                                                         checkBox1.setChecked(false);
                                                         checkBox2.setChecked(false);
                                                         checkBox3.setChecked(false);
                                                         checkBox5.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                     if (buttonView.isChecked()) {
                                                         checkBox0.setChecked(false);
                                                         checkBox1.setChecked(false);
                                                         checkBox2.setChecked(false);
                                                         checkBox3.setChecked(false);
                                                         checkBox4.setChecked(false);
                                                     }
                                                 }
                                             }
        );
        //업데이트인풋인지, 아웃풋인지. 확인해서 선택된 목록을 알려줌

        if (Title.equals(Input) || Title.equals("UpdateInput")) {

            backImage.setBackgroundResource(R.drawable.basicinputaddpopup);
            radioGroup.setVisibility(View.VISIBLE);
            checkBox0.setVisibility(View.GONE);
            checkBox1.setVisibility(View.GONE);
            checkBox2.setVisibility(View.GONE);
            checkBox3.setVisibility(View.GONE);
            checkBox4.setVisibility(View.GONE);
            checkBox5.setVisibility(View.GONE);

            //Check();
        } else if (Title.equals(Output)|| Title.equals("UpdateOutput")) {
            backImage.setBackgroundResource(R.drawable.basicoutputaddpopup);
            radioGroup.setVisibility(View.GONE);
            checkBox0.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.VISIBLE);
            checkBox2.setVisibility(View.VISIBLE);
            checkBox3.setVisibility(View.VISIBLE);
            checkBox4.setVisibility(View.VISIBLE);
            checkBox5.setVisibility(View.VISIBLE);
            // Check();
        }
        //Check();
    }
    //input click인지 outputclick인지 받아서 처리
    public AddBasic(Context context, String title, View.OnClickListener inputClick, View.OnClickListener exitClick) {
        super(context);
        this.Title = title;
        this.inputClick = inputClick;
        this.cancelClick = exitClick;
        // Check();
    }

}
