package org.osiskanisius.cccup.cccup2018.model;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by inigo on 18/12/17.
 */

public class JadwalWriter{
    private Context context;
    private JadwalSQLOpenHelper dbHelper;
    private SQLiteDatabase db;

    public Context getContext(){
        return context;
    }

    private String setLeftJoin(String leftTable, String rightTable){
        return "("+leftTable+") LEFT JOIN ("+rightTable+")";
    }

    public JadwalWriter(Context context){
        this.context = context;
        dbHelper = new JadwalSQLOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Mendapatkan semua lomba dengan bidang yang sama
     * @param bidangID ID dari bidang yang lombanya ingin dicari
     * @return Cursor yang berisi semua lomba yang diinginkan (Tidak berisi peserta)
     */
    public Cursor getAllLombaID(int bidangID){
        return db.query(
                JadwalSQLContract.Lomba.TABLE_NAME,
                new String[]{JadwalSQLContract.Lomba._ID},
                JadwalSQLContract.Bidang._ID+" = "+bidangID,
                null,
                null,
                null,
                JadwalSQLContract.Lomba.COLUMN_WAKTU
        );
    }

    /**
     * Mendapatkan semua peserta yang akan mengikuti suatu lomba tertentu
     * @param lombaID ID lomba yang hendak dicari pesertanya
     * @return Cursor yang berisi semua ID dan skor peserta yang mengikuti lomba tersebut
     */
    public Cursor getAllPeserta(int lombaID){
        return db.query(
                JadwalSQLContract.LombaDetails.TABLE_NAME,
                new String[]{
                        JadwalSQLContract.LombaDetails.COLUMN_PESERTA_ID,
                        JadwalSQLContract.LombaDetails.COLUMN_SKOR_PESERTA
                },
                JadwalSQLContract.LombaDetails.COLUMN_LOMBA_ID+" = "+lombaID,
                null,
                null,
                null,
                null
        );
    }

    /**
     * Mendapatkan profil peserta yang diinginkan,
     * termasuk nama sekolah, kode pool, dan kelasnya di pencak silat/tae kwon do
     * @param pesertaID ID peserta yang diinginkan
     * @return Cursor yang berisi profil peserta
     */
    public Cursor getPesertaProfile(int pesertaID){
        String query = "SELECT * FROM " + setLeftJoin(
                setLeftJoin(
                        setLeftJoin(
                                setLeftJoin(
                                        JadwalSQLContract.Peserta.TABLE_NAME,
                                        JadwalSQLContract.Sekolah.TABLE_NAME),
                                JadwalSQLContract.PoolDetails.TABLE_NAME),
                        JadwalSQLContract.PencaksilatDetails.TABLE_NAME),
                JadwalSQLContract.TaekwondoDetails.TABLE_NAME
        )
                +" WHERE "+ JadwalSQLContract.Peserta._ID+" = "+pesertaID;
        return db.rawQuery(query, null);
    }

    public Cursor getListBidang(){
        return db.query(
                JadwalSQLContract.Bidang.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                JadwalSQLContract.Bidang._ID
        );
    }

    public String[] getListBidangString(){
        Cursor result = getListBidang();
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
        Log.v("Presenter", "Total List = "+listBidang.size());
        return listBidang.toArray(new String[0]);
    }

    public void insertToTable(String tableName, HashMap<String,String> data){
        ContentValues values = new ContentValues();
        Set<String> keys = data.keySet();
        for(String key : keys){
            values.put(key, data.get(key));
        }
        db.insert(JadwalSQLContract.Bidang.TABLE_NAME, null, values);
    }
}
