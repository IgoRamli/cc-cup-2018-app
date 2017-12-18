package org.osiskanisius.cccup.cccup2018.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by inigo on 18/12/17.
 */

public class JadwalWriter {
    private String setLeftJoin(String leftTable, String rightTable){
        return "("+leftTable+") LEFT JOIN ("+rightTable+")";
    }

    /**
     * Mendapatkan semua lomba dengan bidang yang sama
     * @param db SQLiteDatabase untuk mengakses database
     * @param bidangID ID dari bidang yang lombanya ingin dicari
     * @return Cursor yang berisi semua lomba yang diinginkan (Tidak berisi peserta)
     */
    public Cursor getAllLombaID(SQLiteDatabase db, int bidangID){
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
     * @param db SQLiteDatabase untuk menakses database
     * @param lombaID ID lomba yang hendak dicari pesertanya
     * @return Cursor yang berisi semua ID dan skor peserta yang mengikuti lomba tersebut
     */
    public Cursor getAllPeserta(SQLiteDatabase db, int lombaID){
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
     * Mendapatkan prifol peserta yang diinginkan,
     * termasuk nama sekolah, kode pool, dan kelasnya di pencak silat/tae kwon do
     * @param db SQLiteDatabase untuk mengakses database
     * @param pesertaID ID peserta yang diinginkan
     * @return Cursor yang berisi profil peserta
     */
    public Cursor getPesertaProfile(SQLiteDatabase db, int pesertaID){
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
}
