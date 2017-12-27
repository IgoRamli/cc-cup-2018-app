package org.osiskanisius.cccup.cccup2018.jadwal;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.data.Lomba;

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
        void setJadwalLomba(Lomba[] jadwalLomba);
    }
    interface Presenter{
        String[] getListBidang();
        void changeJadwalType(int i);
        void onItemSelected(int i);
        void onNothingSelected();
        void showErrorState();
        void showEmptyState();
        void showJadwalData();
        void showLoadingState();
    }
}
