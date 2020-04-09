package com.example.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.threadUtils.TestThread;
import com.example.utils.UnparsedData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MyFragment1 extends Fragment {

    TestThread t = new TestThread(this);
    UnparsedData unparsedData = new UnparsedData();

    boolean mIsPrepare = false;        //视图还没准备好
    boolean mIsVisible = false;        //不可见
    boolean mIsFirstLoad = true;    //第一次加载

    public Handler handler;
    public Handler handler1;
    public Handler mHandler;
    private LinearLayout linerAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_page1, container, false);
        return view;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*绑定id*/
        final Button btn = getActivity().findViewById(R.id.btn);
        final TextView DD, TT;
        DD = getActivity().findViewById(R.id.DD);
        TT = getActivity().findViewById(R.id.TT);
        t.test3();

        linerAdd = getActivity().findViewById(R.id.linerAdd);
        t.test2();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.test2();

            }
        });


        /*线程相关*/
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Map<Integer, Object> map;
                map = unparsedData.queryAll(msg.obj.toString());
                String string = msg.obj.toString();
                if (string.equals("1")) {
                    init();
                    Toast.makeText(getActivity(), "联网失败", Toast.LENGTH_SHORT).show();
                } else {
                    add(map);
                    Toast.makeText(getActivity(), "更新数据", Toast.LENGTH_SHORT).show();
                }
            }
        };

        handler1 = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    Toast.makeText(getActivity(), "cs", Toast.LENGTH_SHORT).show();
                }
            }
        };

        /*时间*/
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                long time = System.currentTimeMillis();
                Date date = new Date(time);
                SimpleDateFormat format = new SimpleDateFormat("hh:mm");
                SimpleDateFormat format1 = new SimpleDateFormat("MM-dd");
//                Log.i("更新了时间", date.toString());
                TT.setText(format.format(date));
                DD.setText(format1.format(date));
            }

        };
    }

    /*列表*/
    public void init() {
        linerAdd.removeAllViews();
        for (int i = 0; i < 6; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_linear, null);
            TextView textView1 = view.findViewById(R.id.text_1);
            TextView textView2 = view.findViewById(R.id.text_2);
            textView1.setText("空");
            textView2.setText("空值");
            linerAdd.addView(view);
        }
    }

    public void add(Map map) {
        linerAdd.removeAllViews();
        String[] str = {"O2", "CO2", "PH", "WD", "XI", "GE"};
        String[] str1={"0.2","0.6","0.5","24","0.5","0.3"};//标准值

        for (int i = 0; i < map.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_linear, null);
            TextView textView1 = view.findViewById(R.id.text_1);
            TextView textView2 = view.findViewById(R.id.text_2);
            textView1.setText(str[i]);
            textView2.setText(map.get(i + 1).toString());
            double d = Double.parseDouble(map.get(i + 1).toString());
            //和标准值进行对比，并设置字体颜色
            if (d > Double.parseDouble(str1[i])) {
                textView2.setTextColor(android.graphics.Color.parseColor("#FF0000"));
            }

            linerAdd.addView(view);
        }
    }


}
