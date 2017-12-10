package org.androidtown.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ErrorDialog extends Dialog {

    TextView title;
    Button errorOk;

    private View.OnClickListener inputClick;
    private String Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_dialog);

        title = (TextView) findViewById(R.id.ErrorTitle);
        errorOk = (Button) findViewById(R.id.ErrorOk);

        title.setText(Title);

        if (errorOk != null) {
            errorOk.setOnClickListener(inputClick);
        }
    }

    public ErrorDialog(Context context, String Title, View.OnClickListener inputClick) {
        super(context);
        this.Title = Title;
        this.inputClick = inputClick;
    }
}