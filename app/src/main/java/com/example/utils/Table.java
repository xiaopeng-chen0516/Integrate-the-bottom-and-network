package com.example.utils;

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

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Table {

    public  void initData(LineChart lc,WindowManager wm,float[] floats,Integer[] listTime,
                          float Maximum,float Minimum,float Granularity) {
        // 设置上下左右偏移量
        lc.setExtraOffsets(1f,24f,24f,0f);
//        lc.setBackgroundColor(Color.WHITE);
        setDescription("过去一分钟的变化              ",wm,lc); // 设置描述
        lc.animateXY(1000, 1000); // XY动画
        setLegend(lc); // 设置图例
        setYAxis(lc,Maximum,Minimum,Granularity); // 设置Y轴
        setXAxis(lc,listTime); // 设置X轴
        setChartData(lc,floats); // 设置图标数据
    }

    public  void setLegend(LineChart lc) {
        Legend legend = lc.getLegend();
        legend.setForm(Legend.LegendForm.LINE); // 图形：线
        legend.setFormSize(14f); // 图形大小
        legend.setFormLineWidth(9f); // 线宽小于如下大小绘制出平躺长方形
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // 图例在水平线上的对齐方式：右对齐
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.NONE);
    }

    public  void setDescription(String descriptionStr, WindowManager wm,LineChart lc) {
        // 设置描述
        Description description = new Description();
        description.setText(descriptionStr);
        // 计算描述位置
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Paint paint = new Paint();
        paint.setTextSize(16f);
        float x = outMetrics.widthPixels - Utils.convertDpToPixel(12);
        float y =  Utils.calcTextHeight(paint, descriptionStr) + Utils.convertDpToPixel(12);
        description.setPosition(x, y); // 设置描述位置
        lc.setDescription(description);
    }

    public  void setYAxis(LineChart lc,float Maximum,float Minimum,float Granularity) {
        // 左边Y轴
        final YAxis yAxisLeft = lc.getAxisLeft();
        yAxisLeft.setAxisMaximum(Maximum); // 设置Y轴最大值
        yAxisLeft.setAxisMinimum(Minimum); // 设置Y轴最小值
        yAxisLeft.setGranularity(Granularity); // 设置间隔尺寸
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

    public  void setXAxis(LineChart lc, final Integer[] listTime) {
        // X轴
        XAxis xAxis = lc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 在底部
        xAxis.setDrawGridLines(false); // 不绘制网格线
        xAxis.setTextColor(Color.BLACK); // 文本颜色为灰色
        xAxis.setTextSize(12f); // 文本大小为12dp
        xAxis.setGranularity(1f); // 设置间隔尺寸

/*        xAxis.setLabelCount(listTime.length+1); // 设置标签数量
        xAxis.setAxisMinimum(listTime[0]); // 设置X轴最小值
        xAxis.setAxisMaximum(listTime[listTime.length-1]); // 设置X轴最大值*/

        xAxis.setLabelCount(listTime.length); // 设置标签数量
        xAxis.setAxisMinimum(0); // 设置X轴最小值
        xAxis.setAxisMaximum(listTime.length-1); // 设置X轴最大值

        // 设置标签的显示格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                return value == listTime[0] ? "℃" : value == listTime[listTime.length-1] ? "(S)" : value < 10 ? "" + (int) value : (int) value + "";
                return value < 10 ? "" + (int) value : (int) value + "";
            }
        });

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                    return String.valueOf(listTime[(int) value]);
            }

        };
//        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        final TMarket tMarket=new TMarket();
        lc.setMarker(tMarket);
        lc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                tMarket.refreshContent(e, h);
            }

            @Override
            public void onNothingSelected() {}


        });
    }




    public void setChartData(LineChart lc,float[] floats) {
        // 1.获取一或多组Entry对象集合的数据
        // 模拟数据1
        List<Entry> yVals1 = new ArrayList<>();
       /* float[] ys1 = new float[] {
                0.004f, 0.03f, 0.018f, 0.018f, 0.018f, 0.018f, 0.017f, 0.016f, 0.017f, 0.019f};*/
        for (int i = 0; i < floats.length; i++) {
            yVals1.add(new Entry(i ,floats[i]));
        }

        // 2.分别通过每一组Entry对象集合的数据创建折线数据集
        LineDataSet lineDataSet1 = new LineDataSet(yVals1, "最近日期（日）");
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
