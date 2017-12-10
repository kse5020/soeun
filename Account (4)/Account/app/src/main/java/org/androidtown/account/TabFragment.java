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
//탭바를 프레그먼트 형식으로
public class TabFragment extends Fragment {

    MainActivity mainActivity;

    Button option;
    Button logo;
    Button memo;
    Button stat;
    Button input;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
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
        //로고 누르면 메인화면으로 엑티비티 전환
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mainActivity.getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //설정 누르면 기본 수입/지출 값 넣는 엑티비티로 전환
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mainActivity.getApplicationContext(),BasicInputOutput.class);
                startActivity(intent);
            }
        });
        //일정 엑티비티로 전환
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mainActivity.getApplicationContext(),MemoActivity.class);
                startActivity(intent);
            }
        });
        //통계 엑티비티로 전환
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mainActivity.getApplicationContext(),Graph.class);
                startActivity(intent);
            }
        });
        //입출금 엑티비티로 전환
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(mainActivity.getApplicationContext(),Management.class);
                startActivity(intent);
            }
        });


        return rootview;
    }
}
