package org.osiskanisius.cccup.cccup2018.jadwal;

import android.content.Context;

/**
 * Created by inigo on 18/12/17.
 */

public interface JadwalContract {
    interface View{
        Context getViewContext();
        void showProgressBar();
        void hideProgressBar();
        void showErrorState();
        void hideErrorState();
        void showEmptyState();
        void hideEmptyState();
        void showJadwalLomba();
        void hideJadwalLomba();
        void setJadwalLomba(String[] jadwalLomba);
    }
    interface Presenter{
        String[] getListBidang();
        void changeJadwalType(int i);
        void onPreExecute();
        void onPostExecute(String[] hasilAkhir);
    }
}
