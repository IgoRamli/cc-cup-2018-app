package org.osiskanisius.cccup.cccup2018.jadwal;

/**
 * Created by inigo on 18/12/17.
 */

public class JadwalPresenter implements JadwalContract.Presenter{
    JadwalContract.View mView;

    public JadwalPresenter(JadwalContract.View view){
        mView = view;
    }

    @Override
    public String[] getListBidang(){
        return null;
    }
}
