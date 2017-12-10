package org.androidtown.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by A35X on 2017-12-10.
 */

public class TabFragment_input extends Fragment {
    Management management;

    Button option;
    Button logo;
    Button memo;
    Button stat;
    Button input;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        management = (Management) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
       management = null;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);

        logo = (Button)rootview.findViewById(R.id.fragement_logo);
        option = (Button)rootview.findViewById(R.id.fragment_option);
        memo = (Button)rootview.findViewById(R.id.fragment_memo);
        stat = (Button)rootview.findViewById(R.id.fragment_stat);
        input = (Button)rootview.findViewById(R.id.fragment_content);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(management.getApplicationContext(),MainActivity.class);
                startActivity(intent);
                management.finish();
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(management.getApplicationContext(),BasicInputOutput.class);
                startActivity(intent);
                management.finish();
            }
        });
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(management.getApplicationContext(),MemoActivity.class);
                startActivity(intent);
                management.finish();
            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(management.getApplicationContext(),Graph.class);
                startActivity(intent);
                management.finish();
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(management.getApplicationContext(),Management.class);
                startActivity(intent);
                management.finish();
            }
        });

        return rootview;
    }
}
