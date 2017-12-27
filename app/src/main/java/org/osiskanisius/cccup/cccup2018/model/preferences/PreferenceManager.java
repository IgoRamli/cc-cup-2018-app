package org.osiskanisius.cccup.cccup2018.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Kelas ini mengatur urusan preferences
 * Hanya boleh diakses ModelManager
 *
 * Konten data di SharedPreferences:
 * 1. DB_LOADED_KEY (Boolean) => Apakah populasi data di SQLite sudah dijalankan dengan baik
 *
 * Created by inigo on 26/12/17.
 */

public class PreferenceManager {
    private Context mContext;
    private SharedPreferences mPreferences;

    //Filename
    private static final String PREFERENCE_DB_KEY = "org.osiskanisius.cccup.cccup2018.DATABASE_INFO";

    //Preferences key
    public static final String DB_LOADED_KEY = "isDBLoaded";

    public PreferenceManager(Context context){
        mContext = context;
        mPreferences = context.getSharedPreferences(PREFERENCE_DB_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Mengubah nilai dari data di SharedPreferences
     * @param key key dari data yang ingin diubah
     * @param value value yang ingin dimasukkan
     */
    public void writeData(String key, Boolean value){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Memberitahukan apakah Database SQLite sudah terisi DAN up to date
     * @return true apabila Database up to date, false bila tidak
     */
    public Boolean isDatabaseLoaded(){
        return mPreferences.getBoolean(DB_LOADED_KEY, false);
    }
}
