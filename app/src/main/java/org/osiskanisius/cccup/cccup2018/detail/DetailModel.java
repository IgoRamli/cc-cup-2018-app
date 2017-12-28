package org.osiskanisius.cccup.cccup2018.detail;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.data.DataPeserta;
import org.osiskanisius.cccup.cccup2018.model.ModelContract;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseContract;
import org.osiskanisius.cccup.cccup2018.model.database.DatabaseHelper;
import org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager;

/**
 * Created by inigo on 28/12/17.
 */

class DetailModel implements ModelContract {
    DatabaseHelper mHelper;
    PreferenceManager mPref;
    DetailContract.Presenter mPresenter;

    DetailModel(Context context, DetailContract.Presenter presenter){
        mPresenter = presenter;
        mPref = new PreferenceManager(context);
        mHelper = new DatabaseHelper(this, context);
    }

    @Override
    public void notifyPresenter() {}

    @Override
    public void setDBLoadedKey(Boolean value) {
        mPref.writeData(PreferenceManager.DB_LOADED_KEY, value);
    }

    @Override
    public Boolean isDatabaseLoaded() {
        return mPref.isDatabaseLoaded();
    }

    public DataPeserta getInfoPesertaFromDB(Integer pesertaID){
        return mHelper.getInfoPeserta(pesertaID);
    }

    public DataLomba getLombaFromDB(Integer lombaID){
        return mHelper.getLomba(lombaID);
    }
}
