package org.androidtown.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class DeleteBasic extends  Dialog  {

    Button deleteOk;
    Button deleteNo;
    private View.OnClickListener inputClick;
    private View.OnClickListener cancelClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_basic);

        deleteOk = (Button)findViewById(R.id.deleteOK);
        deleteNo = (Button)findViewById(R.id.deleteNO);


        //확인,취소 버튼 받는거
        if(deleteOk != null && deleteNo != null)
        {
            deleteOk.setOnClickListener(inputClick);
            deleteNo.setOnClickListener(cancelClick);
        }
        else if (deleteOk != null && deleteNo == null)
        {
            deleteOk.setOnClickListener(inputClick);
        }
        else if (deleteOk == null && deleteNo != null)
        {
            deleteNo.setOnClickListener(cancelClick);
        }
    }
    //딜리트 확인 취소 눌려졌을떄
    public DeleteBasic(Context context,View.OnClickListener inputClick, View.OnClickListener exitClick) {
        super(context);
        this.inputClick = inputClick;
        this.cancelClick = exitClick;
    }
}