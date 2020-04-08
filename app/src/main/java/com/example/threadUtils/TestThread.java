package com.example.threadUtils;

import android.os.Message;
import android.util.Log;


import com.example.fragment.MyFragment1;
import com.example.fragment.MyFragment2;
import com.example.myapplication.Main2Activity;
import com.example.utils.NetUilts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TestThread {

    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;
    public Main2Activity main2Activity;

    public TestThread(){

    }

    public  TestThread(MyFragment1 myFragment1) {
        this.myFragment1 = myFragment1;
    }
    public  TestThread(MyFragment2 myFragment2) {
        this.myFragment2 = myFragment2;
    }
    public TestThread(Main2Activity main2Activity){
        this.main2Activity=main2Activity;
    }

    public void test1() {
        /**
         * get请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginOfGet("111","111");

                Message msg = new Message();
                if (s!=null){
                    Log.i("线程1--》中的值",s);

                    msg.obj = 2;
                    myFragment1.handler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    myFragment1.handler.sendMessage(msg);
                }
            }
        }).start();
    }

    public void test2() {
        /**
         * POST请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginofPost("111","111");
                Message msg = new Message();
                if (s!=null){
                    Log.i("线程2--》中的值",s);
                    msg.obj = s;
                    myFragment1.handler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    myFragment1.handler.sendMessage(msg);
                }

            }
        }).start();
    }

    public void test3() {
        /**
         * 时间
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = 1;
                        myFragment1.mHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            }).start();
    }


    public void test4() {
        /**
         * 表格数据
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginofPost("111","111");
                Message msg = new Message();
                if (s!=null){
                    Log.i("线程4--》中的值",s);
                    msg.obj = s;
                    myFragment2.dataHandler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    myFragment2.dataHandler.sendMessage(msg);
                }

            }
        }).start();
    }



    public void test5() {
        /**
         * get请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String  s= NetUilts.loginofPost("111","111");
                Log.i("线程1--》中的值",s);
                Message msg = new Message();
                if (s!=null){


                    msg.obj = 2;
                    main2Activity.handler.sendMessage(msg);
                }else {
                    msg.obj =1;
                    main2Activity.handler.sendMessage(msg);
                }
            }
        }).start();
    }



}


