package org.osiskanisius.cccup.cccup2018.home;


import android.content.Context;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.model.ModelContract;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseHelper;
import org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager;

/**
 * Created by inigo on 12/01/18.
 */

public class HomeModel implements ModelContract {
    PreferenceManager mPref;
    DatabaseHelper mDBHelper;

    public HomeModel(Context context){
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

    public DataLomba[] getUpcomingLomba(int size){
        return mDBHelper.getUpcomingLomba(size);
    }
}
