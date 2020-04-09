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

import org.json.JSONException;

import java.util.List;
import java.util.Map;

public class MyFragment2 extends Fragment {
    public Handler dataHandler;
    public Handler O2Handler;
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
        final Button btnO2=getActivity().findViewById(R.id.O2);
        t.test4();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"更新",Toast.LENGTH_SHORT).show();
                t.test4();

            }
        });

        btnO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.query7O2();
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
                    t.query7O2();
                    Toast.makeText(getActivity(), "更新图表", Toast.LENGTH_SHORT).show();
                }
            }

        };

        O2Handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String string=msg.obj.toString();
                char a='\"';

                String str1=removeCharAt(string,0);
                String str2=removeCharAt(str1,str1.length()-1);
                String str3=deleteString0(str2,a);
                String[] strings=str3.split(",");
                    Log.i("msg",msg.obj.toString());
                    Log.i("strings",strings[0].toString());
//                    Toast.makeText(getActivity(),msg.obj.toString(),Toast.LENGTH_SHORT).show();

                //转换为float类型
                float[] O2=new float[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    O2[i]=Float.parseFloat(strings[i]);
                }

                init2(O2);

            }
        };

    }

    public void init(float[] floats){

        String[] title11={"O2","CO2"};
        Table table=new Table();
        LineChart chart = getActivity().findViewById(R.id.chart);
        LineChart chart1 = getActivity().findViewById(R.id.chart1);
        // 制作7个数据点（沿x坐标轴）
        float[] floats1={0.02f,0.01f,0.03f,0.05f};

//        for (int i = 0; i < title11.length; i++) {
//            table.title(title11[i]);
//        }



        LineData mLineData = table.makeLineData(floats.length,floats,title11[0]);
        LineData mLineData1 = table.makeLineData(floats1.length,floats1,title11[1]);

        table.setChartStyle(chart, mLineData, Color.WHITE);
        table.setChartStyle(chart1, mLineData1, Color.WHITE);
    }







    public void init2(float[] O2){

        String[] title11={"O2","CO2"};
        Table table=new Table();
        LineChart chart1 = getActivity().findViewById(R.id.chart1);
        // 制作7个数据点（沿x坐标轴）

        LineData mLineData1 = table.makeLineData(O2.length,O2,title11[1]);


        table.setChartStyle(chart1, mLineData1, Color.WHITE);
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    /**
     * 删除方法一
     * @param str
     * @param delChar
     * @return
     */
    public static String deleteString0(String str, char delChar){
        String delStr = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != delChar){
                delStr += str.charAt(i);
            }
        }
        return delStr;
    }
}
