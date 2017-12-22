package org.osiskanisius.cccup.cccup2018.jadwal;

import android.util.Log;

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

    private int bidangLoaded = 0;

    JadwalPresenter(JadwalContract.View view){
        mView = view;
        mModel = new ModelManager(view.getViewContext(), this);
        mInternet = new FetchDataTask(this);
        bidangLoaded = 0;
    }

    @Override
    public String[] getListBidang(){
        if(!mModel.isDataAvailable(JadwalSQLContract.Bidang.TABLE_NAME)){
            mView.loadData(JadwalSQLContract.Bidang.TABLE_NAME,
                    true,
                    mModel.getLoader());
            return new String[]{};
        }else {
            Log.v(getClass().getName(), "Data already exist");
            bidangLoaded = 1;
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
            showErrorState();
        }else if(hasilAkhir.length == 0){
            showEmptyState();
        }else{
            showJadwalData();
            mView.setJadwalLomba(hasilAkhir);
        }
    }

    @Override
    public void notifySQLChange(String tableName){
        if(tableName.equals(JadwalSQLContract.Bidang.TABLE_NAME)){
            //Update spinner
            if(!mModel.isDataAvailable(JadwalSQLContract.Bidang.TABLE_NAME)){
                //It no data still exist, error
                bidangLoaded = -1;
                mView.setSpinnerAdapter(new String[]{"Data not available"});
            }else {
                bidangLoaded = 1;
                mView.setSpinnerAdapter(getListBidang());
                changeJadwalType(1);
            }
        }else{
            //Default, do nothing
        }
    }

    @Override
    public int isBidangLoaded(){
        return bidangLoaded;
    }

    @Override
    public void onItemSelected(int i){
        if(bidangLoaded == 1){
            showJadwalData();
            changeJadwalType(i+1);
            Log.v(getClass().getName(), "changeJadwalType = "+(i+1));
        }else if(bidangLoaded == 0) {
            showLoadingState();
        }else{
            showErrorState();
        }
    }

    @Override
    public void onNothingSelected(){
        if(bidangLoaded == 1){
            showJadwalData();
            changeJadwalType(1);
        }else if(bidangLoaded == 0) {
            showLoadingState();
        }else{
            showErrorState();
        }
    }

    @Override
    public void showErrorState(){
        mView.hideProgressBar();
        mView.hideEmptyState();
        mView.hideJadwalLomba();
        mView.showErrorState();
    }

    @Override
    public void showEmptyState(){
        mView.hideProgressBar();
        mView.hideErrorState();
        mView.hideJadwalLomba();
        mView.showEmptyState();
    }

    @Override
    public void showJadwalData(){
        mView.hideProgressBar();
        mView.hideEmptyState();
        mView.hideErrorState();
        mView.showJadwalLomba();
    }

    @Override
    public void showLoadingState(){
        mView.hideEmptyState();
        mView.hideErrorState();
        mView.hideJadwalLomba();
        mView.showProgressBar();
    }
}
