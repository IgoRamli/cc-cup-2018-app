package org.osiskanisius.cccup.cccup2018.home;

import android.content.Context;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;

/**
 * Created by inigo on 12/01/18.
 */

public interface HomeContract {
    interface View{
        Context getViewContext();
        void setUpcomingEventsListViewData(DataLomba[] data);
    }

    interface Presenter{
        DataLomba[] getUpcomingLomba();
    }
}
