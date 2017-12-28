package org.osiskanisius.cccup.cccup2018.detail;

import android.content.Context;

/**
 * Created by inigo on 28/12/17.
 */

interface DetailContract {
    interface View{
        void changeTitleView(String text);
        void changeDescriptionView(String text);

        Context getViewContext();
    }

    interface Presenter{
        void setView(Integer lombaID);
    }
}
