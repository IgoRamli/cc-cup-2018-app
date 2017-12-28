package org.osiskanisius.cccup.cccup2018.detail;

import android.util.Log;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.data.DataLombaDetails;
import org.osiskanisius.cccup.cccup2018.data.DataPeserta;

/**
 * Created by inigo on 28/12/17.
 */

public class DetailPresenter implements DetailContract.Presenter {
    private Integer lombaID;

    private DetailContract.View mView;
    private DetailModel mModel;

    private DataLomba mLomba;

    DetailPresenter(DetailContract.View view){
        mView = view;
        mModel = new DetailModel(view.getViewContext(), this);
    }

    @Override
    public void setView(Integer lombaID) {
        this.lombaID = lombaID;
        mLomba = mModel.getLombaFromDB(lombaID);
        Log.d("DetailPresenter", "Data Lomba = " + mLomba.toString());

        DataLombaDetails[] listPeserta = mLomba.getPeserta();
        for(int i = 0; i < listPeserta.length; i++){
            DataPeserta row = listPeserta[i].getPeserta();
            Integer pesertaID = row.getPesertaID();
            listPeserta[i].setPesertaRetainInfo(mModel.getInfoPesertaFromDB(pesertaID));
            mLomba.changePeserta(listPeserta[i], i);
            Log.d("DetailPresenter", "New info peserta = " + listPeserta[i].toString());
        }

        mView.changeTitleView(mLomba.getNamaLomba());
        mView.changeDescriptionView(mLomba.toString());
    }
}
