package com.example.utils;

import android.util.Log;

import com.example.entity.Detection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UnparsedData {

    public Map Unparse(String mapDate){
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try {
            JSONArray jsonArray = new JSONArray(mapDate);
            for (int i = jsonArray.length() - 1; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                map.put(1, jsonObject.getDouble("O2"));
                map.put(2, jsonObject.getDouble("CO2"));
                map.put(3, jsonObject.getDouble("PH"));
                map.put(4, jsonObject.getDouble("WD"));
                map.put(5, jsonObject.getDouble("XI"));
                map.put(6, jsonObject.getDouble("GE"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public  Map queryAll(String mapDate){
        Map<Integer, Object> map = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(mapDate);
            for (int i = jsonArray.length() - 1; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                map.put(1, jsonObject.getDouble("o2"));
                map.put(2, jsonObject.getDouble("co2"));
                map.put(3, jsonObject.getDouble("ph"));
                map.put(4, jsonObject.getDouble("wd"));
                map.put(5, jsonObject.getDouble("xi"));
                map.put(6, jsonObject.getDouble("ge"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public  Map<Object,Object> query7O2All(String mapDate){
        final Date date = new Date();
        final SimpleDateFormat sd = new SimpleDateFormat("dd");//只保留天

        Map<Object, Object> map = new LinkedHashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(mapDate);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
                date.setTime((Long) jsonObject.get("date"));//时间转换
                map.put(sd.format(date), jsonObject.get("o2"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }



    public List query7O2(String listData) throws JSONException {


        List<Object> list=new ArrayList<>();
        JSONArray jsonArray=new JSONArray(listData);
        for (int i = jsonArray.length()-1; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);  //解析JSON数据
            list.add(jsonObject);
        }
        return list;
    }
}
