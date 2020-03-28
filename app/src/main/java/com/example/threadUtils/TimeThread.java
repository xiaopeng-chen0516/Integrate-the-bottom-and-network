package com.example.threadUtils;

import android.os.Message;
import android.util.Log;

import com.example.fragment.MyFragment1;



public class TimeThread extends Thread {
    private MyFragment1 myFragment1;


    public TimeThread(MyFragment1 myFragment1) {
        this.myFragment1 =myFragment1;
    }
    public volatile boolean running=true;

    @Override
    public void run() {

        while (running){

            try {
                Message msg = new Message();
                msg.what = 1;
                myFragment1.handler1.sendMessage(msg);
                Log.i("执行", "线程");
//                if (this.isInterrupted()) break;
                Log.i("TimeThread中", String.valueOf(this.isInterrupted()));
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


