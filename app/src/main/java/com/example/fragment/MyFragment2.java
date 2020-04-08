package com.example.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;
import com.example.threadUtils.TestThread;
import com.example.utils.Table;
import com.example.utils.UnparsedData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.util.Map;

public class MyFragment2 extends Fragment {
    public Handler dataHandler;
    UnparsedData unparsedData = new UnparsedData();
    TestThread t=new TestThread(this);
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
        t.test4();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"更新",Toast.LENGTH_SHORT).show();
                t.test4();

            }
        });


        /*消息处理*/
        dataHandler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Map<Integer, Object> map= unparsedData.queryAll(msg.obj.toString());
                String string = msg.obj.toString();
//                float[] fl={12.2f,13.0f,12.3f,15.2f,25.0f,65.2f,40.2f};

                if (string.equals("1")) {
                    Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
                } else {
                    float[] fl=new float[6];

//
                    for (int i = 0; i < map.size(); i++) {
                        Log.i(String.valueOf((i+1)),map.get(i+1).toString());
                    fl[i]= Float.parseFloat(map.get(i+1).toString());

//                    fl[i]=i;
                    }
                    init(fl);
                    Toast.makeText(getActivity(), "更新图表", Toast.LENGTH_SHORT).show();
                }
            }

        };



;

    }

    public void init(float[] floats){

        Table table=new Table();
        LineChart chart = getActivity().findViewById(R.id.chart);
        // 制作7个数据点（沿x坐标轴）
        LineData mLineData = table.makeLineData(6,floats);
        table.setChartStyle(chart, mLineData, Color.WHITE);
    }
}
