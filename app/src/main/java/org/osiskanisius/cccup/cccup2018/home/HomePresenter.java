package org.osiskanisius.cccup.cccup2018.home;

import android.util.Log;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;

/**
 * Created by inigo on 12/01/18.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private HomeModel mModel;

    public HomePresenter(HomeContract.View view){
        mView = view;
        mModel = new HomeModel(view.getViewContext());
    }

    @Override
    public DataLomba[] getUpcomingLomba(){
        DataLomba[] result =  mModel.getUpcomingLomba(3);
        return result;
    }
}
