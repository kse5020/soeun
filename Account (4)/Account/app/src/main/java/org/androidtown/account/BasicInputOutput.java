package org.androidtown.account;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BasicInputOutput extends AppCompatActivity {

    Button tabBar;
    Button goMain;
    DataBase db;
    AddBasic addBasic;
    DeleteBasic deleteBasic;
    SqlStatement sqlstatement = new SqlStatement();
    int SelectPart=0;
    String checkClass;
    String WriteInputName;//AddBasic 수입값
    int WriteInputPrice;//AddBasic 수입값
    String WriteInputPriceCheck;
    String WriteInputClass;
    int CheckLocation;
    //insert/update 확인 값
    int ChangeValue;
    int checkLoca;
    String checkDate;
    String checkName;
    int checkPrice;
    int checkIO;
    TextView input1;
    TextView input2;
    TextView input3;
    TextView input4;
    TextView input5;
    TextView output1;
    TextView output2;
    TextView output3;
    TextView output4;
    TextView output5;
    Button addInput;
    Button addoutput;
    Button deleteInput;
    Button deleteInput2;
    Button deleteInput3;
    Button deleteInput4;
    Button deleteInput5;
    Button deleteOutput;
    Button deleteOutput2;
    Button deleteOutput3;
    Button deleteOutput4;
    Button deleteOutput5;
    int checkCount[] = new int[10];
    String Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_input_output);

        TabFragment_set tabFragment = new TabFragment_set();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.bio_container, tabFragment);
        ft.commit();

        addInput = (Button) findViewById(R.id.AddInput);
        addoutput = (Button) findViewById(R.id.AddOutput);
        deleteInput = (Button) findViewById(R.id.DeleteInput1);
        deleteInput2 = (Button) findViewById(R.id.DeleteInput2);
        deleteInput3 = (Button) findViewById(R.id.DeleteInput3);
        deleteInput4 = (Button) findViewById(R.id.DeleteInput4);
        deleteInput5 = (Button) findViewById(R.id.DeleteInput5);

        deleteOutput = (Button) findViewById(R.id.DeleteOutput1);
        deleteOutput2 = (Button) findViewById(R.id.DeleteOutput2);
        deleteOutput3 = (Button) findViewById(R.id.DeleteOutput3);
        deleteOutput4 = (Button) findViewById(R.id.DeleteOutput4);
        deleteOutput5 = (Button) findViewById(R.id.DeleteOutput5);

        input1 = (TextView) findViewById(R.id.Input1);
        input2 = (TextView) findViewById(R.id.Input2);
        input3 = (TextView) findViewById(R.id.Input3);
        input4 = (TextView) findViewById(R.id.Input4);
        input5 = (TextView) findViewById(R.id.Input5);
        output1 = (TextView)findViewById(R.id.Output1);
        output2 = (TextView)findViewById(R.id.Output2);
        output3 = (TextView)findViewById(R.id.Output3);
        output4 = (TextView)findViewById(R.id.Output4);
        output5 = (TextView)findViewById(R.id.Output5);

        //디비 불러오는 곳
        this.db = new DataBase("account", this);
        this.db.dropTable(SqlStatement.drop_sql);
        //this.db.dropTable(Sqlstatement.drop_sql02);
        this.db.dropTable(SqlStatement.drop_sql03);
        //this.db.dropTable(Sqlstatement.drop_sql05);
        this.db.createTable(SqlStatement.sql_01);
        this.db.createTable(SqlStatement.sql_02);
        this.db.createTable(SqlStatement.sql_03);
        this.db.createTable(SqlStatement.sql_05);

        //값이 들어갔는지 확인하는 곳을 처음에 초기화
        for(int i=0;i<10;i++) {
            checkCount[i] = 0;
        }
        //로고버튼 누르면 메인으로 감

        //수입추가버튼 눌렸을때
        addInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectPart=0;
                for(int i=0;i<5;i++) {
                    if(checkCount[i]==0) {
                        SelectPart = i + 1;
                        checkCount[i]=1;
                        Title ="Input";
                        BasicInput(Title,SelectPart);
                        break;
                    }
                }
                if(SelectPart==0 )
                {
                    Toast.makeText(getApplicationContext(),"고정 수입이 꽉 찼습니다. 비워주세요",Toast.LENGTH_LONG).show();
                }
            }
        });
//지출추가 버튼 눌렸을때
        addoutput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectPart=5;
                for(int i=5;i<10;i++) {
                    if(checkCount[i]==0) {
                        SelectPart = i + 1;
                        checkCount[i]=1;
                        Title = "Output";
                        BasicInput(Title,SelectPart);
                        break;
                    }
                }

                if(SelectPart==5)
                {
                    Toast.makeText(getApplicationContext(),"고정 지출이 꽉 찼습니다. 비워주세요",Toast.LENGTH_LONG).show();
                }
            }
        });
//수입,지출 삭제 버튼 눌렸을때 (1-10)
        deleteInput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 1;

                BasicInputDelete(SelectPart);
            }
        });
        deleteInput2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 2;

                BasicInputDelete(SelectPart);
            }
        });
        deleteInput3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 3;

                BasicInputDelete(SelectPart);
            }
        });
        deleteInput4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 4;

                BasicInputDelete(SelectPart);
            }
        });
        deleteInput5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 5;

                BasicInputDelete(SelectPart);
            }
        });
        deleteOutput.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 6;

                BasicInputDelete(SelectPart);
            }
        });
        deleteOutput2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 7;

                BasicInputDelete(SelectPart);
            }
        });
        deleteOutput3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 8;

                BasicInputDelete(SelectPart);
            }
        });deleteOutput4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 9;

                BasicInputDelete(SelectPart);
            }
        });deleteOutput5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 10;

                BasicInputDelete(SelectPart);
            }
        });
        //수입,지출 값이 있는상태에서 눌렸을때 업데이트
        input1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 1;

                String Title="UpdateInput";
                BasicInput(Title,SelectPart);
            }
        });
        input2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 2;

                Title="UpdateInput";
                BasicInput(Title,SelectPart);
            }
        });
        input3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 3;

                Title="UpdateInput";
                BasicInput(Title,SelectPart);
            }
        });
        input4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 4;

                Title="UpdateInput";
                BasicInput(Title,SelectPart);
            }
        });
        input5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 5;

                Title="UpdateInput";
                BasicInput(Title,SelectPart);
            }
        }); output1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 6;

                Title="UpdateOutput";
                BasicInput(Title,SelectPart);
            }
        });output2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 7;

                Title="UpdateOutput";
                BasicInput(Title,SelectPart);
            }
        });output3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 8;

                Title="UpdateOutput";
                BasicInput(Title,SelectPart);
            }
        });output4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 9;

                Title="UpdateOutput";
                BasicInput(Title,SelectPart);
            }
        });output5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int SelectPart = 10;

                Title="UpdateOutput";
                BasicInput(Title,SelectPart);
            }
        });


        PrintData();
    }
    //데이터를 계속 그려주는 함수
    public void PrintData()
    {
        String selectSql = "select Location, Name, Price, IO, Class from BasicBudget";
        Cursor c = db.selectData(selectSql);

        String textSum="";
        for (int i = 0; i < c.getCount(); i++) {
            c.moveToNext();
            checkLoca = c.getInt(0);
            checkName = c.getString(1);
            checkPrice = c.getInt(2);
            checkIO = c.getInt(3);
            checkClass = c.getString(4);
//위치에 따라서 값을 보여줌

            switch (checkLoca)//위치
            {

                case 1:
                    checkCount[0] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    input1.setVisibility(View.VISIBLE); deleteInput.setVisibility(View.VISIBLE);
                    input1.setText(textSum);
                    break;
                case 2:
                    checkCount[1] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    input2.setVisibility(View.VISIBLE); deleteInput2.setVisibility(View.VISIBLE);
                    input2.setText(textSum);
                    break;
                case 3:
                    checkCount[2] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    input3.setVisibility(View.VISIBLE); deleteInput3.setVisibility(View.VISIBLE);
                    input3.setText(textSum);
                    break;
                case 4:
                    checkCount[3] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    input4.setVisibility(View.VISIBLE); deleteInput4.setVisibility(View.VISIBLE);
                    input4.setText(textSum);
                    break;
                case 5:
                    checkCount[4] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    input5.setVisibility(View.VISIBLE); deleteInput5.setVisibility(View.VISIBLE);
                    input5.setText(textSum);
                    break;
                case 6:
                    checkCount[5] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    output1.setVisibility(View.VISIBLE); deleteOutput.setVisibility(View.VISIBLE);
                    output1.setText(textSum);
                    break;
                case 7:
                    checkCount[6] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    output2.setVisibility(View.VISIBLE); deleteOutput2.setVisibility(View.VISIBLE);
                    output2.setText(textSum);
                    break;
                case 8:
                    checkCount[7] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    output3.setVisibility(View.VISIBLE); deleteOutput3.setVisibility(View.VISIBLE);
                    output3.setText(textSum);
                    break;

                case 9:
                    checkCount[8] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    output4.setVisibility(View.VISIBLE); deleteOutput4.setVisibility(View.VISIBLE);
                    output4.setText(textSum);
                    break;
                case 10:
                    checkCount[9] = 1;
                    textSum = checkName+  " : " + checkPrice + " , 분류 : "+ checkClass;
                    output5.setVisibility(View.VISIBLE); deleteOutput5.setVisibility(View.VISIBLE);
                    output5.setText(textSum);
                    break;
            }
        }
    }
    //값 넣어주는 메소드 수입,지출 업데이트 구분해서 보여줌.
    public void BasicInput(String Title,int location) {
        CheckLocation = location;
        addBasic = new AddBasic(BasicInputOutput.this, Title, inputClick, cancelClick);
        addBasic.setCancelable(true);
        addBasic.getWindow().setGravity(Gravity.CENTER);
        addBasic.show();

        if (Title.equals("UpdateInput")||Title.equals("UpdateOutput")) {
            String selectSql = "select Location, Name, Price, IO, Class from BasicBudget";
            Cursor c = db.selectData(selectSql);

            for (int i = 0; i < c.getCount(); i++) {
                c.moveToNext();
                checkLoca = c.getInt(0);
                checkName = c.getString(1);
                checkPrice = c.getInt(2);
                checkIO = c.getInt(3);
                checkClass = c.getString(4);

                if (checkLoca == CheckLocation) {
                    addBasic.nameWrite.setText(checkName);
                    addBasic.priceWrite.setText(""+checkPrice);
                }
            }//for문 안에 값 넣기 위해서.
        }
    }
    //값을 지우는걸 했을떄 값을 구분해서 보여줌.
    public void BasicInputDelete(int location) {
        CheckLocation = location;
        switch(CheckLocation)
        {
            case 1:deleteBasic = new DeleteBasic(BasicInputOutput.this, inputDeleteClick, cancelDeleteClick);
                break;
            case 2:deleteBasic = new DeleteBasic(BasicInputOutput.this, inputDeleteClick2, cancelDeleteClick);
                break;
            case 3:deleteBasic = new DeleteBasic(BasicInputOutput.this, inputDeleteClick3, cancelDeleteClick);
                break;
            case 4:deleteBasic = new DeleteBasic(BasicInputOutput.this, inputDeleteClick4, cancelDeleteClick);
                break;
            case 5:deleteBasic = new DeleteBasic(BasicInputOutput.this, inputDeleteClick5, cancelDeleteClick);
                break;
            case 6:deleteBasic = new DeleteBasic(BasicInputOutput.this, outputDeleteClick, cancelDeleteClick);
                break;
            case 7:deleteBasic = new DeleteBasic(BasicInputOutput.this, outputDeleteClick2, cancelDeleteClick);
                break;
            case 8:deleteBasic = new DeleteBasic(BasicInputOutput.this, outputDeleteClick3, cancelDeleteClick);
                break;
            case 9:deleteBasic = new DeleteBasic(BasicInputOutput.this, outputDeleteClick4, cancelDeleteClick);
                break;
            case 10:deleteBasic = new DeleteBasic(BasicInputOutput.this, outputDeleteClick5, cancelDeleteClick);
                break;
        }
        deleteBasic.setCancelable(true);
        deleteBasic.getWindow().setGravity(Gravity.CENTER);
        deleteBasic.show();
    }

    //수입,지출 삭제 버튼 눌렸을때, 텍스트 값 초기화하고, db문에서 해당 되는것을 지움
    private View.OnClickListener inputDeleteClick = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=1;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            input1.setVisibility(View.GONE); deleteInput.setVisibility(View.GONE);
            db.deleteData(selectSql);
            input1.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener inputDeleteClick2 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=2;
            input2.setVisibility(View.GONE); deleteInput2.setVisibility(View.GONE);
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);

            input2.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener inputDeleteClick3 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=3;

            input3.setVisibility(View.GONE); deleteInput3.setVisibility(View.GONE);
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            input3.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener inputDeleteClick4 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=4;
            input4.setVisibility(View.GONE); deleteInput4.setVisibility(View.GONE);
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            input4.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };

    private View.OnClickListener inputDeleteClick5 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=5;
            input5.setVisibility(View.GONE); deleteInput5.setVisibility(View.GONE);
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            input5.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };

    private View.OnClickListener outputDeleteClick = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=6;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            output1.setVisibility(View.GONE); deleteOutput.setVisibility(View.GONE);
            db.deleteData(selectSql);
            output1.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener outputDeleteClick2 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=7;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            output2.setText("");
            output2.setVisibility(View.GONE); deleteOutput2.setVisibility(View.GONE);
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener outputDeleteClick3 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=8;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            output3.setVisibility(View.GONE); deleteOutput3.setVisibility(View.GONE);
            output3.setText("");
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener outputDeleteClick4 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=9;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            output4.setText("");
            output4.setVisibility(View.GONE);deleteOutput4.setVisibility(View.GONE);
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    private View.OnClickListener outputDeleteClick5 = new View.OnClickListener() {
        public void onClick(View v) {
            CheckLocation=10;
            String selectSql = "DELETE FROM BasicBudget WHERE Location = '"+CheckLocation+"'";
            db.deleteData(selectSql);
            output5.setText("");
            output5.setVisibility(View.GONE);deleteOutput5.setVisibility(View.GONE);
            checkCount[CheckLocation-1]=0;
            PrintData();
            deleteBasic.dismiss();
        }
    };
    //취소 다이얼로그 창 띄워졌을때 취소 버튼
    private View.OnClickListener cancelDeleteClick = new View.OnClickListener() {
        public void onClick(View v) {
            deleteBasic.dismiss();
        }
    };
    //확인 버튼 눌렸을떄 업데이트, 수입,지출 이런거 다 해서 값을 함.
    private View.OnClickListener inputClick = new View.OnClickListener() {
        public void onClick(View v) {
            String sql;
            if (Title.equals("UpdateInput")||Title.equals("UpdateOutput")) {

                WriteInputName = addBasic.nameWrite.getText().toString();
                WriteInputPriceCheck = addBasic.priceWrite.getText().toString();
                WriteInputPrice = Integer.parseInt(addBasic.priceWrite.getText().toString());

                if (addBasic.nameWrite.getText().toString().length() != 0 && addBasic.priceWrite.getText().toString().length() != 0 &&
                        addBasic.radioGroup.getCheckedRadioButtonId() != -1 || (addBasic.checkBox0.isChecked() != false
                        || addBasic.checkBox1.isChecked() != false || addBasic.checkBox2.isChecked() != false || addBasic.checkBox3.isChecked() != false
                        || addBasic.checkBox4.isChecked() != false || addBasic.checkBox5.isChecked() != false))//안의 값이 체크되었을때.
                {
                    if((addBasic.checkBox0.isChecked() != false
                            || addBasic.checkBox1.isChecked() != false || addBasic.checkBox2.isChecked() != false || addBasic.checkBox3.isChecked() != false
                            || addBasic.checkBox4.isChecked() != false || addBasic.checkBox5.isChecked() != false))
                    {
                        if (addBasic.checkBox0.isChecked() == true)
                            WriteInputClass = "식비";
                        else if (addBasic.checkBox1.isChecked() == true)
                            WriteInputClass = "의료비";
                        else if (addBasic.checkBox2.isChecked() == true)
                            WriteInputClass = "적금";
                        else if (addBasic.checkBox3.isChecked() == true)
                            WriteInputClass = "교통비";
                        else if (addBasic.checkBox4.isChecked() == true)
                            WriteInputClass = "의류비";
                        else if (addBasic.checkBox5.isChecked() == true)
                            WriteInputClass = "기타";
                    } else if (addBasic.radioGroup.getCheckedRadioButtonId() != -1) {//입력일때
                        if (addBasic.radioButton0.isChecked() == true)
                            WriteInputClass = "월급";
                        else if (addBasic.radioButton1.isChecked() == true)
                            WriteInputClass = "용돈";
                        else if (addBasic.radioButton2.isChecked() == true)
                            WriteInputClass = "기타";
                    }
                }else
                    addBasic.dismiss();
                //다 입력되고 나면, 시간도 새로 업데이트함
                if (CheckLocation >  0) {
                    for (int i = 1; i < 11; i++) {
                        if (i == CheckLocation) {

                            String Day;
                            Day = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.KOREA).format(new Date());

                            sql = sqlstatement.updateBasicBudgetSql(WriteInputName, WriteInputPrice, CheckLocation, WriteInputClass, Day);
                            db.updateData(sql);
                            PrintData();
                            break;

                        }
                    }

                }
                addBasic.dismiss();
            }else//걍 아무것도 적히지 않은 상태에서 input일때
            {

                if(addBasic.nameWrite.getText().toString().length()!=0&& addBasic.priceWrite.getText().toString().length()!=0 &&
                        (addBasic.radioGroup.getCheckedRadioButtonId()!=-1 || (addBasic.checkBox0.isChecked()!=false
                                ||addBasic.checkBox1.isChecked()!=false ||addBasic.checkBox2.isChecked()!=false ||addBasic.checkBox3.isChecked()!=false
                                ||addBasic.checkBox4.isChecked()!=false ||addBasic.checkBox5.isChecked()!=false))) {
                    WriteInputName = addBasic.nameWrite.getText().toString();
                    WriteInputPriceCheck = addBasic.priceWrite.getText().toString();
                    WriteInputPrice = Integer.parseInt(addBasic.priceWrite.getText().toString());

                    if (addBasic.checkBox0.isChecked() != false
                            || addBasic.checkBox1.isChecked() != false || addBasic.checkBox2.isChecked() != false || addBasic.checkBox3.isChecked() != false
                            || addBasic.checkBox4.isChecked() != false || addBasic.checkBox5.isChecked() != false)
                    { //출력일때
                        if (addBasic.checkBox0.isChecked() == true)
                            WriteInputClass = "식비";
                        else if (addBasic.checkBox1.isChecked() == true)
                            WriteInputClass = "의료비";
                        else if (addBasic.checkBox2.isChecked() == true)
                            WriteInputClass = "적금";
                        else if (addBasic.checkBox3.isChecked() == true)
                            WriteInputClass = "교통비";
                        else if (addBasic.checkBox4.isChecked() == true)
                            WriteInputClass = "의류비";
                        else if (addBasic.checkBox5.isChecked() == true)
                            WriteInputClass = "기타";
                    } else if (addBasic.radioGroup.getCheckedRadioButtonId() != -1) {//입력일때

                        if (addBasic.radioButton0.isChecked() == true)
                            WriteInputClass = "월급";
                        else if (addBasic.radioButton1.isChecked() == true)
                            WriteInputClass = "용돈";
                        else if (addBasic.radioButton2.isChecked() == true)
                            WriteInputClass = "기타";
                    }else
                        addBasic.dismiss();
                    //값에 따라서 시간을 부여하고, 위치에 따라 값을 표시함
                    if (CheckLocation < 6) {
                        for (int i = 1; i < 6; i++) {
                            if (i == CheckLocation) {

                                String Day;
                                Day = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.KOREA).format(new Date());

                                sql = sqlstatement.insertBudgetSql(WriteInputName, WriteInputPrice, 0, CheckLocation, WriteInputClass,Day);
                                db.insertData(sql);
                                switch(i)
                                {
                                    case 2: input2.setVisibility(View.VISIBLE); deleteInput2.setVisibility(View.VISIBLE);
                                        break;
                                    case 3: input3.setVisibility(View.VISIBLE); deleteInput3.setVisibility(View.VISIBLE);
                                        break;
                                    case 4: input4.setVisibility(View.VISIBLE); deleteInput4.setVisibility(View.VISIBLE);
                                        break;
                                    case 5: input5.setVisibility(View.VISIBLE); deleteInput5.setVisibility(View.VISIBLE);
                                        break;

                                }
                                PrintData();
                                break;
                            }
                        }

                    } else if (CheckLocation >= 6) {
                        for (int i = 6; i < 11; i++) {
                            if (i == CheckLocation) {

                                String Day;
                                Day = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss",Locale.KOREA).format(new Date());
                                sql = sqlstatement.insertBudgetSql(WriteInputName, WriteInputPrice, 1, CheckLocation, WriteInputClass, Day);
                                db.insertData(sql);
                                switch(i)
                                {

                                    case 7: output2.setVisibility(View.VISIBLE); deleteOutput2.setVisibility(View.VISIBLE);
                                        break;
                                    case 8: output3.setVisibility(View.VISIBLE); deleteOutput3.setVisibility(View.VISIBLE);
                                        break;
                                    case 9: output4.setVisibility(View.VISIBLE); deleteOutput4.setVisibility(View.VISIBLE);
                                        break;
                                    case 10: output5.setVisibility(View.VISIBLE); deleteOutput5.setVisibility(View.VISIBLE);
                                        break;

                                }
                                PrintData();
                                break;
                            }
                        }

                    }
                }else  {
                    checkCount[SelectPart-1]=0;
                    addBasic.dismiss();
                }
            }
            addBasic.dismiss();

        }
    };
    //취소버튼 눌렀을때. 수입지출추가할떄 or 업데이트했을떄
    private View.OnClickListener cancelClick = new View.OnClickListener() {
        public void onClick(View v) {

            if (Title.equals("UpdateInput")||Title.equals("UpdateOutput")) {
                addBasic.dismiss();
            }else{
                checkCount[SelectPart-1]=0;
                addBasic.dismiss();}
        }
    };


}
