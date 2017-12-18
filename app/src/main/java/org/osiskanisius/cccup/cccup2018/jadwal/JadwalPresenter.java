package org.osiskanisius.cccup.cccup2018.jadwal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.osiskanisius.cccup.cccup2018.model.JadwalSQLContract;
import org.osiskanisius.cccup.cccup2018.model.JadwalSQLOpenHelper;
import org.osiskanisius.cccup.cccup2018.model.JadwalWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by inigo on 18/12/17.
 */

public class JadwalPresenter implements JadwalContract.Presenter{
    JadwalContract.View mView;
    JadwalWriter mWriter;

    public JadwalPresenter(JadwalContract.View view){
        mView = view;
        mWriter = new JadwalWriter(view.getViewContext());
    }

    @Override
    public String[] getListBidang(){
        Cursor result = mWriter.getListBidang();
        ArrayList<String> listBidang = new ArrayList<>();
        try{
            while(result.moveToNext()){
                listBidang.add(result.getString(
                        result.getColumnIndexOrThrow(JadwalSQLContract.Bidang.COLUMN_NAMA)
                ));
            }
        }finally{
            result.close();
        }
        return listBidang.toArray(new String[0]);
    }
}
