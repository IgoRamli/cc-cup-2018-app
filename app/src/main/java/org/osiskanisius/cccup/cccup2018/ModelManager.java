package org.osiskanisius.cccup.cccup2018;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.internet.WebLoader;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

/**
 * Created by inigo on 20/12/17.
 */

public class ModelManager {
    private Context mContext;

    private WebLoader mLoader;
    private JadwalWriter mWriter;

    public ModelManager(Context context){
        mContext = context;
        mLoader = new WebLoader(this);
        mWriter = new JadwalWriter(this);
    }

    public Context getContext(){
        return mContext;
    }

    public WebLoader getLoader(){
        return mLoader;
    }

    public JadwalWriter getWriter(){
        return mWriter;
    }
}