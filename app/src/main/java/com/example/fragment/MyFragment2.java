package com.example.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;
import com.example.utils.Table;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

public class MyFragment2 extends Fragment {
    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_page2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button btn=getActivity().findViewById(R.id.btn2);


        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"更新",Toast.LENGTH_SHORT).show();

                init();


            }
        });

    }

    public void init(){
        Table table=new Table();
        LineChart chart = getActivity().findViewById(R.id.chart);
        // 制作7个数据点（沿x坐标轴）
        LineData mLineData = table.makeLineData(7);
        table.setChartStyle(chart, mLineData, Color.WHITE);
    }
}
