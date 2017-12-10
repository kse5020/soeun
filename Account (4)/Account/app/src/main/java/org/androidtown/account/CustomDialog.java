package org.androidtown.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by A35X on 2017-11-24.
 */

public class CustomDialog extends Dialog {
    EditText InputEdit;
    EditText dialogTitle;
    TextView dialogDate;
    EditText dialogAccount;
    Button inputbtn;
    Button exitbtn;

    String InputText;
    String dialog_Date;
    String dialog_Title;
    String dialog_Money;

    private View.OnClickListener inputClick;
    private View.OnClickListener exitClick;

    public CustomDialog(Context context, String Date, View.OnClickListener inputClick, View.OnClickListener exitClick) {
        super(context);
        this.dialog_Date = Date;
        this.inputClick = inputClick;
        this.exitClick = exitClick;
    }
    public CustomDialog(Context context, String Date, String Memo, String Money, String Title, View.OnClickListener inputClick, View.OnClickListener exitClick) {
        super(context);
        this.dialog_Date = Date;
        this.dialog_Money = Money;
        this.dialog_Title = Title;
        this.InputText = Memo;
        this.inputClick = inputClick;
        this.exitClick = exitClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        InputEdit = (EditText) findViewById(R.id.Text);
        dialogTitle = (EditText)findViewById(R.id.Title);
        dialogDate = (TextView) findViewById(R.id.Dateview);
        dialogAccount = (EditText) findViewById(R.id.Money);
        inputbtn = (Button)findViewById(R.id.Inputbtn);
        exitbtn = (Button)findViewById(R.id.Exitbtn);

        dialogTitle.setText(dialog_Title);
        dialogDate.setText(dialog_Date);
        dialogAccount.setText(dialog_Money);
        InputEdit.setText(InputText);

        if(inputbtn != null && exitbtn != null)
        {
            inputbtn.setOnClickListener(inputClick);
            exitbtn.setOnClickListener(exitClick);
        }
        else if (inputbtn != null && exitbtn == null)
        {
            inputbtn.setOnClickListener(inputClick);
        }
        else if (inputbtn == null && exitbtn != null)
        {
            exitbtn.setOnClickListener(exitClick);
        }
    }

}
