package org.osiskanisius.cccup.cccup2018.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.osiskanisius.cccup.cccup2018.model.database.DatabaseContract;

/**
 * Created by inigo on 22/12/17.
 */

public class DataLombaDetails {
    private DataPeserta peserta;
    private Integer skorPeserta = -1;

    public DataLombaDetails(){}

    public DataLombaDetails(Cursor cursor){
        peserta = new DataPeserta(cursor);
        if(peserta.isDataExists()){
            if(cursor.getColumnIndex(DatabaseContract.LombaDetails.COLUMN_SKOR_PESERTA) != -1){
                skorPeserta = cursor.getInt(cursor.getColumnIndex(DatabaseContract.LombaDetails.COLUMN_SKOR_PESERTA));
            }
        }
    }

    /**
     * Memberikan data peserta
     * @return data peserta yang tersimpan
     */
    public DataPeserta getPeserta() {
        return peserta;
    }

    /**
     * Memberikan skor peserta dalam Integer
     * @return skor peserta dalam Integer
     */
    public Integer getSkorPesertaInteger() {
        return skorPeserta;
    }

    /**
     * Memberikan skor peserta
     * @return skor peserta, atau "TBA" bila skor belum diketahui
     */
    public String getSkorPeserta() {
        if(skorPeserta == -1){
            return "TBA";
        }else{
            return skorPeserta.toString();
        }
    }

    /**
     * Memberikan data peserta yang baru, semua data lain akan direset
     * @param peserta Data peserta yang baru
     */
    public void setPeserta(DataPeserta peserta) {
        this.peserta = peserta;
        this.skorPeserta = -1;
    }

    /**
     * Mengubah skor peserta, hanya bila ada data peserta yang tersimpan
     * @param skorPeserta skor peserta yang baru
     * @return true apabila berhasil megubah data, false bila tidak berhasil
     */
    public Boolean setSkorPeserta(Integer skorPeserta) {
        if(this.peserta.isDataExists()) {
            this.skorPeserta = skorPeserta;
            return true;
        }else{
            return false;
        }
    }
}
