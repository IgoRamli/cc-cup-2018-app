package org.osiskanisius.cccup.cccup2018;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by inigo on 12/12/17.
 */

public class NetworkUtil {
    private static final String CC_CUP_BASE_URL = "http://cccup.osiskanisius.org/admin/test_join";
    private static final String BIDANG_ID_PARAM = "bidangID";

    public static URL makeWebQuery(int bidangID){
        Uri uri = Uri.parse(CC_CUP_BASE_URL).buildUpon()
                .appendQueryParameter(BIDANG_ID_PARAM, Integer.toString(bidangID))
                .build();
        URL url;
        try{
            url = new URL(uri.toString());
            return url;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponse(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
