package org.osiskanisius.cccup.cccup2018.internet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import org.osiskanisius.cccup.cccup2018.JadwalJsonParser;
import org.osiskanisius.cccup.cccup2018.model.JadwalSQLOpenHelper;

import java.net.URL;
import java.util.HashMap;

public class WebLoader implements LoaderManager.LoaderCallbacks<DataPacket> {
    Context mContext;

    public static final String TABLE_NAME_KEY = "tableName";

    public WebLoader(Context context){
        mContext = context;
    }

    @Override
    public Loader<DataPacket> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<DataPacket>(mContext) {
            @Override
            public DataPacket loadInBackground() {
                String tableName = args.getString(TABLE_NAME_KEY);
                try{
                    URL url = NetworkUtil.makeTableQuery(tableName);
                    String result = NetworkUtil.getResponse(url);
                    return new DataPacket(tableName, JadwalJsonParser.parseTable(result));
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<DataPacket> loader, DataPacket data) {
        JadwalSQLOpenHelper dbHelper = new JadwalSQLOpenHelper(mContext);
        for(HashMap<String, String> row : data.getMainPacket()){
            dbHelper.insertToTable(data.getTableName(), row);
        }
    }

    @Override
    public void onLoaderReset(Loader<DataPacket> loader) {

    }
}
