package org.osiskanisius.cccup.cccup2018.jadwal;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import org.osiskanisius.cccup.cccup2018.internet.DataPacket;

/**
 * Created by inigo on 18/12/17.
 */

public interface JadwalContract {
    interface View{
        Context getViewContext();
        void setSpinnerAdapter(String[] listBidang);
        void showProgressBar();
        void hideProgressBar();
        void showErrorState();
        void hideErrorState();
        void showEmptyState();
        void hideEmptyState();
        void showJadwalLomba();
        void hideJadwalLomba();
        void setJadwalLomba(String[] jadwalLomba);

        void loadData(String tableName,
                      Boolean forceLoad,
                      LoaderManager.LoaderCallbacks<DataPacket> callback);
    }
    interface Presenter{
        String[] getListBidang();
        void changeJadwalType(int i);
        void onPreExecute();
        void onPostExecute(String[] hasilAkhir);
        void notifySQLChange(String tableName);
    }
}
