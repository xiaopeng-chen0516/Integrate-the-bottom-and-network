package com.example.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.threadUtils.TestThread;

public class MyFragment1 extends Fragment {

    boolean mIsPrepare = false;		//视图还没准备好
    boolean mIsVisible= false;		//不可见
    boolean mIsFirstLoad = true;	//第一次加载
    TestThread t=new TestThread(this);
    public Handler handler;
    public Handler handler1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_page1,container,false);
        return view;
    }





    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*绑定id*/
        final TextView tv=getActivity().findViewById(R.id.textView);
        final Button btn=getActivity().findViewById(R.id.btn);
        final TextView tv1=getActivity().findViewById(R.id.tv1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.test1();
            }
        });

        /*线程相关*/
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String string=msg.obj.toString();
                if (string.equals("1")){
                    Toast.makeText(getActivity(),"联网失败",Toast.LENGTH_SHORT).show();
                }else {
                    tv1.setText(msg.obj.toString());
//                    Toast.makeText(getActivity(),msg.obj.toString(),Toast.LENGTH_SHORT).show();
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
    }








}
