package org.osiskanisius.cccup.cccup2018.internet;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import org.osiskanisius.cccup.cccup2018.JadwalJsonParser;
import org.osiskanisius.cccup.cccup2018.model.JadwalSQLOpenHelper;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
/*
public class DataLoader
        implements LoaderManager.LoaderCallbacks<DataLoader.Composite> {
    JadwalSQLOpenHelper mWriter;

    private static final int LOADER_ID = 1;
    private static final String TABLE_NAME_KEY = "tableName";

    public DataLoader(JadwalSQLOpenHelper writer){
        mWriter = writer;
    }

    @Override
    public Loader<DataLoader.Composite> onCreateLoader(int id,final Bundle args) {
        return new AsyncTaskLoader<DataLoader.Composite>(mWriter.getContext()) {
            @Override
            public DataLoader.Composite loadInBackground() {
                String tableName = args.getString(TABLE_NAME_KEY);
                try{
                    URL url = NetworkUtil.makeTableQuery(tableName);
                    String result = NetworkUtil.getResponse(url);
                    Composite hasil = new Composite(args, JadwalJsonParser.parseTable(result));
                    return hasil;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Composite> loader, Composite hasil) {
        String tableName = hasil.args.getString(TABLE_NAME_KEY);
        for(HashMap<String,String> row : hasil.data) {
            mWriter.insertToTable(tableName, row);
        }
    }

    @Override
    public void onLoaderReset(Loader<Composite> loader) {

    }

    public class Composite{
        public Bundle args;
        public ArrayList<HashMap<String, String>> data;
        public Composite(Bundle args, ArrayList<HashMap<String, String>> data){
            this.args = args;
            this.data = data;
        }
    }
}*/

public class DataLoader extends AsyncTask<String, Void, DataLoader.Composite>{
    JadwalSQLOpenHelper mWriter;

    public DataLoader(JadwalSQLOpenHelper writer){
        mWriter = writer;
    }

    @Override
    protected Composite doInBackground(String... strings) {
        String tableName = strings[0];
        try{
            URL url = NetworkUtil.makeTableQuery(tableName);
            String result = NetworkUtil.getResponse(url);
            return new Composite(tableName, JadwalJsonParser.parseTable(result));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onPostExecute(Composite hasil){
        String tableName = hasil.args;
        Log.v("Presenter", "Hasil data = "+hasil.data.size());
        for(HashMap<String,String> row : hasil.data) {
            mWriter.insertToTable(tableName, row);
        }
    }

    public class Composite{
        public String args;
        public ArrayList<HashMap<String, String>> data;
        public Composite(String args, ArrayList<HashMap<String, String>> data){
            this.args = args;
            this.data = data;
        }
    }
}