package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.threadUtils.TestThread;




public class Main2Activity extends Activity {
    public Handler handler;
    final TestThread t=new TestThread(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Button btn=findViewById(R.id.cs_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.test5();
            }
        });




        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(Main2Activity.this,"测试",Toast.LENGTH_SHORT).show();

            }
        };

    }





}
