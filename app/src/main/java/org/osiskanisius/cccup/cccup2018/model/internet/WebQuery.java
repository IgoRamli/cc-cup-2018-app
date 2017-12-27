package org.osiskanisius.cccup.cccup2018.model.internet;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by inigo on 26/12/17.
 */

public class WebQuery {
    private static final String GET_LIST_URL =
            "http://cccup.osiskanisius.org/app_service/get_list";

    public static URL makeTableQuery(String tableName){
        Uri uri = Uri.parse(GET_LIST_URL).buildUpon()
                .appendQueryParameter("table", tableName)
                .build();
        try{
            return new URL(uri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
}
