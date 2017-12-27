package org.osiskanisius.cccup.cccup2018.model.internet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by inigo on 26/12/17.
 */

public class WebParser {
    public static ArrayList<HashMap<String,String>> parseTableResult(String json)
            throws JSONException{
        JSONArray rowList = new JSONArray(json);
        int numRow = rowList.length();
        ArrayList<HashMap<String,String>> hasil = new ArrayList<>();
        for(int i = 0; i < numRow; i++){
            HashMap<String,String> row = new HashMap<>();
            JSONObject jsonRow = rowList.getJSONObject(i);
            Iterator<String> keys = jsonRow.keys();
            while(keys.hasNext()){
                String key = keys.next();
                row.put(key, jsonRow.getString(key));
            }
            hasil.add(row);
        }
        return hasil;
    }
}
