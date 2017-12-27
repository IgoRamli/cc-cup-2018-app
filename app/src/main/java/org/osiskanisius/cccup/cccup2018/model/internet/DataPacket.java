package org.osiskanisius.cccup.cccup2018.model.internet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by inigo on 26/12/17.
 */

public class DataPacket {
    private String tableName;
    private ArrayList<HashMap<String, String>> data;
    private Boolean success;
    public DataPacket(String tableName, ArrayList<HashMap<String, String>> data, Boolean success){
        this.tableName = tableName;
        this.data = data;
        this.success = success;
    }

    public String getTableName(){
        return tableName;
    }

    public ArrayList<HashMap<String, String>> getData(){
        return data;
    }

    public Boolean getStatus(){
        return success;
    }
}
