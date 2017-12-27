package org.osiskanisius.cccup.cccup2018.model.internet;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseHelper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by inigo on 26/12/17.
 */

public class DatabaseLoader extends AsyncTask<String[], Void, DataPacket[]>{
    DatabaseHelper mHelper;
    public DatabaseLoader(DatabaseHelper helper){
        super();
        mHelper = helper;
    }

    @Override
    protected DataPacket[] doInBackground(String[]... urls) {
        DataPacket[] results = new DataPacket[urls[0].length];
        for(int i = 0; i < urls[0].length; i++) {
            URL url = WebQuery.makeTableQuery(urls[0][i]);
            try {
                String resultString = "";
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String row;
                while ((row = in.readLine()) != null) {
                    resultString += row;
                }
                connection.disconnect();
                results[i] = new DataPacket(urls[0][i],
                        WebParser.parseTableResult(resultString),
                        true);
            } catch (Exception e) {
                e.printStackTrace();
                results[i] = new DataPacket(urls[0][i], null, false);
            }
        }
        return results;
    }

    @Override
    public void onPostExecute(DataPacket[] hasil){
        Log.d("DatabaseLoader", "Loading complete");
        mHelper.onLoadingComplete(hasil);
    }
}
