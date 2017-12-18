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
    }
    interface Presenter{
        String[] getListBidang();
    }
}
