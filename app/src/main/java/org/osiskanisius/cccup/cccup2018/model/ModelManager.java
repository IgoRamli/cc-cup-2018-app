package org.osiskanisius.cccup.cccup2018.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalPresenter;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseHelper;
import org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager;

/**
 * Kelas untuk mengatur semua kegiatan internet dan database
 * Presenter harus menggunakan keas ini untuk kegiatan back end
 *
 * Created by inigo on 26/12/17.
 */

public class ModelManager {
    private DatabaseHelper mDatabase;
    private PreferenceManager mPref;
    private JadwalPresenter mPresenter;

    //Notify flag
    private Boolean notifyPresenter = false;

    public ModelManager(JadwalPresenter presenter, Context context){
        mPresenter = presenter;
        mPref = new PreferenceManager(context);
        mDatabase = new DatabaseHelper(this, context);
    }

    public void setDBLoadedKey(Boolean value){
        mPref.writeData(PreferenceManager.DB_LOADED_KEY, value);
        Log.d("ModelManager", "DB Loaded = "+value);
    }

    public Boolean isDatabaseLoaded(){
        return mPref.isDatabaseLoaded();
    }

    public void loadDatabase(){
        mDatabase.populateData();
    }

    public void setNotifyPresenter(Boolean flag){
        notifyPresenter = flag;
    }

    public void notifyPresenter(){
        if(!notifyPresenter) return;
        mPresenter.updateSpinner();
        mPresenter.displayJadwal();
        notifyPresenter = false;
    }

    public String[] getListBidangFromDB(){
        return mDatabase.getListLombaFromDB();
    }

    public DataLomba[] getListLomba(Integer bidangID){
        return mDatabase.getListLomba(bidangID);
    }
}
