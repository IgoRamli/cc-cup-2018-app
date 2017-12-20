package org.osiskanisius.cccup.cccup2018.internet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by inigo on 20/12/17.
 */

public class DataPacket {
    private ArrayList<HashMap<String,String>> mainPacket;
    private String tableName;

    public DataPacket(String table, ArrayList<HashMap<String, String>> main){
        tableName = table;
        mainPacket = main;
    }

    public ArrayList<HashMap<String,String>> getMainPacket(){
        return mainPacket;
    }

    public String getTableName(){
        return tableName;
    }
}
