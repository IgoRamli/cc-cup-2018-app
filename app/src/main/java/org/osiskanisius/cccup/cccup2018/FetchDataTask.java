package org.osiskanisius.cccup.cccup2018;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Created by inigo on 12/12/17.
 */

public class FetchDataTask extends AsyncTask<URL, Void, String[]> {
    @Override
    public String[] doInBackground(URL... url){
        URL request = url[0];
        try{
            String result;
           result = NetworkUtil.getResponse(request);

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
