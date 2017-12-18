package org.osiskanisius.cccup.cccup2018.jadwal;

import org.osiskanisius.cccup.cccup2018.internet.FetchDataTask;
import org.osiskanisius.cccup.cccup2018.internet.NetworkUtil;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

public class JadwalPresenter implements JadwalContract.Presenter{
    private JadwalContract.View mView;
    private JadwalWriter mWriter;
    private FetchDataTask mInternet;

    JadwalPresenter(JadwalContract.View view){
        mView = view;
        mWriter = new JadwalWriter(view.getViewContext());
        mInternet = new FetchDataTask(this);
    }

    @Override
    public String[] getListBidang(){
        return mWriter.getListBidangString();
    }

    @Override
    public void changeJadwalType(int i){
        mInternet.execute(NetworkUtil.makeWebQuery(i));
    }

    @Override
    public void onPreExecute(){
        mView.showProgressBar();
    }

    @Override
    public void onPostExecute(String[] hasilAkhir){
        mView.hideProgressBar();
        if(hasilAkhir == null){
            mView.hideEmptyState();
            mView.hideJadwalLomba();
            mView.showErrorState();
        }else if(hasilAkhir.length == 0){
            mView.hideErrorState();
            mView.hideJadwalLomba();
            mView.showEmptyState();
        }else{
            mView.hideEmptyState();
            mView.hideErrorState();
            mView.showJadwalLomba();
            mView.setJadwalLomba(hasilAkhir);
        }
    }
}
