package org.osiskanisius.cccup.cccup2018.jadwal;

import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

public class JadwalPresenter implements JadwalContract.Presenter{
    JadwalContract.View mView;
    JadwalWriter mWriter;

    public JadwalPresenter(JadwalContract.View view){
        mView = view;
        mWriter = new JadwalWriter(view.getViewContext());
    }

    @Override
    public String[] getListBidang(){
        return mWriter.getListBidangString();
    }
}
