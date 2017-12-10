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

public class TabFragment_stat extends Fragment {
    Graph graph;


    Button option;
    Button logo;
    Button memo;
    Button stat;
    Button input;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        graph = (Graph) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        graph = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        logo = (Button) rootview.findViewById(R.id.fragement_logo);
        option = (Button) rootview.findViewById(R.id.fragment_option);
        memo = (Button) rootview.findViewById(R.id.fragment_memo);
        stat = (Button) rootview.findViewById(R.id.fragment_stat);
        input = (Button) rootview.findViewById(R.id.fragment_content);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(graph.getApplicationContext(), MainActivity.class);
                startActivity(intent);
                graph.finish();
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(graph.getApplicationContext(), BasicInputOutput.class);
                startActivity(intent);
                graph.finish();
            }
        });
        memo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(graph.getApplicationContext(), MemoActivity.class);
                startActivity(intent);
                graph.finish();
            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(graph.getApplicationContext(), Graph.class);
                startActivity(intent);
                graph.finish();
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(graph.getApplicationContext(), Management.class);
                startActivity(intent);
                graph.finish();
            }
        });

        return rootview;
    }
}