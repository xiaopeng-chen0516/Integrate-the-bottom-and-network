package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.threadUtils.TestThread;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends Activity {
    public Handler handler;
    final TestThread t=new TestThread(this);

    private LineChart lc;
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

        initView();
        initData();

    }


    private void initView() {
        lc = (LineChart) findViewById(R.id.lc);
    }


    private void initData() {
        // 设置上下左右偏移量
        lc.setExtraOffsets(1f,24f,24f,0f);
//        lc.setBackgroundColor(Color.WHITE);
        setDescription("过去一分钟的气温变化              "); // 设置描述
        lc.animateXY(1000, 1000); // XY动画
        setLegend(); // 设置图例
        setYAxis(); // 设置Y轴
        setXAxis(); // 设置X轴
        setChartData(); // 设置图标数据
    }

    private void setLegend() {
        Legend legend = lc.getLegend();
        legend.setForm(Legend.LegendForm.LINE); // 图形：线
        legend.setFormSize(14f); // 图形大小
        legend.setFormLineWidth(9f); // 线宽小于如下大小绘制出平躺长方形
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // 图例在水平线上的对齐方式：右对齐
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.NONE);
    }

    private void setDescription(String descriptionStr) {
        // 设置描述
        Description description = new Description();
        description.setText(descriptionStr);
        // 计算描述位置
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Paint paint = new Paint();
        paint.setTextSize(16f);
        float x = outMetrics.widthPixels - Utils.convertDpToPixel(12);
        float y =  Utils.calcTextHeight(paint, descriptionStr) + Utils.convertDpToPixel(12);
        description.setPosition(x, y); // 设置描述位置
        lc.setDescription(description);
    }

    private void setYAxis() {
        // 左边Y轴
        final YAxis yAxisLeft = lc.getAxisLeft();
        yAxisLeft.setAxisMaximum(0.03f); // 设置Y轴最大值
        yAxisLeft.setAxisMinimum(0); // 设置Y轴最小值
        yAxisLeft.setGranularity(0.001f); // 设置间隔尺寸
        yAxisLeft.setTextSize(10f); // 文本大小为12dp
        yAxisLeft.setTextColor(Color.BLACK); // 文本颜色为灰色
/*        yAxisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value == yAxisLeft.getAxisMinimum() ? (int) value + "" : (int) value +"";
            }
        });*/
        // 右侧Y轴
        lc.getAxisRight().setEnabled(false); // 不启用
    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = lc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setLabelCount(10); // 设置标签数量
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(12f); // 文本大小为12dp
        xAxis.setGranularity(1f); // 设置间隔尺寸
        xAxis.setAxisMinimum(0f); // 设置X轴最小值
        xAxis.setAxisMaximum(10); // 设置X轴最大值
        // 设置标签的显示格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value == 0 ? "℃" : value == 10 ? "(S)" : value < 10 ? "" + (int) value : (int) value + "";
            }
        });
    }

    public void setChartData() {
        // 1.获取一或多组Entry对象集合的数据
        // 模拟数据1
        List<Entry> yVals1 = new ArrayList<>();
        float[] ys1 = new float[] {
                0.004f, 0.03f, 0.018f, 0.018f, 0.018f, 0.018f, 0.017f, 0.016f, 0.017f, 0.019f};
        for (int i = 0; i < ys1.length; i++) {
            yVals1.add(new Entry(i+1 ,ys1[i]));
        }

        // 2.分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, "温度");
        lineDataSet1.setDrawCircleHole(false); // 不绘制圆洞，即为实心圆点
        lineDataSet1.setColor(Color.BLACK); // 设置为红色
        lineDataSet1.setMode(LineDataSet.Mode.LINEAR); // 设置为贝塞尔曲线
        lineDataSet1.setCubicIntensity(0.15f); // 强度
        lineDataSet1.setCircleColor(Color.BLACK); // 设置圆点为颜色
        lineDataSet1.setCircleRadius(4.5f);
        lineDataSet1.setLineWidth(1.5f); // 设置线宽为2
        // 3.将每一组折线数据集添加到折线数据中
        LineData lineData = new LineData(lineDataSet1);
        lineData.setDrawValues(false);
        // 4.将折线数据设置给图表
        lc.setData(lineData);
    }







}
