package com.example.utils;

import com.example.entity.Detection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
}
