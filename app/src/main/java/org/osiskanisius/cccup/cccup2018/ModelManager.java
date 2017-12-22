package org.osiskanisius.cccup.cccup2018;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.internet.DataPacket;
import org.osiskanisius.cccup.cccup2018.internet.WebLoader;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalContract;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

import java.util.HashMap;

/**
 * Created by inigo on 20/12/17.
 */

public class ModelManager {
    private Context mContext;
    private JadwalContract.Presenter mPresenter;
    private DataPacket mData = null;

    private WebLoader mLoader;
    private JadwalWriter mWriter;

    public ModelManager(Context context, JadwalContract.Presenter presenter){
        mContext = context;
        mPresenter = presenter;
        mLoader = new WebLoader(this);
        mWriter = new JadwalWriter(this);
    }

    public Context getContext(){
        return mContext;
    }

    public WebLoader getLoader(){
        return mLoader;
    }

    public JadwalContract.Presenter getPresenter(){
        return mPresenter;
    }

    public JadwalWriter getWriter(){
        return mWriter;
    }

    public void saveData(DataPacket data){
        mData = data;
    }

    public void insertData(DataPacket data){
        String tableName = data.getTableName();
        for(HashMap<String, String> row : data.getMainPacket()){
            mWriter.insertToTable(tableName, row);
        }
    }

    public void saveAndInsertData(DataPacket data){
        saveData(data);
        insertData(data);
    }

    public boolean isDataAvailable(String tableName){
        return mWriter.getTableCount(tableName) > 0;
    }

    public DataPacket getCachedData(){
        return mData;
    }
}