package org.osiskanisius.cccup.cccup2018.service;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.model.ModelContract;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseHelper;
import org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager;

/**
 * Created by inigo on 12/01/18.
 */

public class ServiceModel implements ModelContract {
    PreferenceManager mPref;
    DatabaseHelper mDBHelper;

    public ServiceModel(Context context){
        mPref = new PreferenceManager(context);
        mDBHelper = new DatabaseHelper(this, context);
    }

    @Override
    public void notifyPresenter() {

    }

    @Override
    public void setDBLoadedKey(Boolean value) {
        mPref.writeData(PreferenceManager.DB_LOADED_KEY, value);
    }

    @Override
    public Boolean isDatabaseLoaded() {
        return mPref.isDatabaseLoaded();
    }

    public void populateData(){
        mDBHelper.populateData();
    }
}
