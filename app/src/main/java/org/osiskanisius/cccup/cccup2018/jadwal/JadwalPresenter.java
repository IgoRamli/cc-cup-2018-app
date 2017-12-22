package org.osiskanisius.cccup.cccup2018.jadwal;

import org.osiskanisius.cccup.cccup2018.ModelManager;
import org.osiskanisius.cccup.cccup2018.internet.DataLoader;
import org.osiskanisius.cccup.cccup2018.internet.FetchDataTask;
import org.osiskanisius.cccup.cccup2018.internet.NetworkUtil;
import org.osiskanisius.cccup.cccup2018.model.JadwalSQLContract;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

public class JadwalPresenter implements JadwalContract.Presenter{
    private JadwalContract.View mView;
    private ModelManager mModel;
    private FetchDataTask mInternet;

    JadwalPresenter(JadwalContract.View view){
        mView = view;
        mModel = new ModelManager(view.getViewContext(), this);
        mInternet = new FetchDataTask(this);
    }

    @Override
    public String[] getListBidang(){
        if(!mModel.isDataAvailable(JadwalSQLContract.Bidang.TABLE_NAME)){
            mView.loadData(JadwalSQLContract.Bidang.TABLE_NAME,
                    true,
                    mModel.getLoader());
            return null;
        }else {
            return mModel.getWriter().getListBidangString();
        }
    }

    @Override
    public void changeJadwalType(int i){
        mInternet = new FetchDataTask(this);
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

    @Override
    public void notifySQLChange(String tableName){
        if(tableName.equals(JadwalSQLContract.Bidang.TABLE_NAME)){
            //Update spinner
            mView.setSpinnerAdapter(getListBidang());
            changeJadwalType(1);
        }else{
            //Default, do nothing
        }
    }
}
