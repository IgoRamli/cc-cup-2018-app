package org.osiskanisius.cccup.cccup2018.jadwal;

import android.util.Log;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;

public class JadwalPresenter implements JadwalContract.Presenter{
    private JadwalContract.View mView;
    private JadwalModel mManager;
    private int currentBidang = 0;

    JadwalPresenter(JadwalContract.View view){
        mView = view;
        mManager = new JadwalModel(this, view.getViewContext());
    }

    @Override
    public String[] getListBidang(){
        if(!mManager.isDatabaseLoaded()){
            mManager.setNotifyPresenter(true);
            showLoadingState();
            mManager.loadDatabase();
            return new String[0];
        }else{
            Log.d("JadwalPresenter", "Jummlah Bidang = " + mManager.getListBidangFromDB().length);
            return mManager.getListBidangFromDB();
        }
    }

    @Override
    public void changeJadwalType(int i){
        currentBidang = i+1;
        showLoadingState();
        Log.d("JadwalPresenter", "changeJadwalType("+i+")");
        if(!mManager.isDatabaseLoaded()){
            mManager.setNotifyPresenter(true);
            mManager.loadDatabase();
        }else{
            displayJadwal();
        }
    }

    public void displayJadwal(){
        if(mManager.isDatabaseLoaded()) {
            DataLomba[] listLomba = mManager.getListLomba(currentBidang);
            if(listLomba.length > 0){
                showJadwalData();
                mView.setJadwalLomba(listLomba);
            }else {
                showEmptyState();
            }
        }else{
            showErrorState();
        }
    }

    @Override
    public void onItemSelected(int i){
        changeJadwalType(i);
    }

    @Override
    public void onNothingSelected(){
        changeJadwalType(0);
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

    public void updateSpinner(){
        mView.setSpinnerAdapter(mManager.getListBidangFromDB());
    }
}
