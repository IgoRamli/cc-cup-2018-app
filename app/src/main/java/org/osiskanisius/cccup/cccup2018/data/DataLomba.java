package org.osiskanisius.cccup.cccup2018.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.osiskanisius.cccup.cccup2018.model.database.DatabaseContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Kelas yang menyimpan data lomba (Nama Lomba, eaktu mulai lomba, dan daftar peserta yang berpartisipasi
 * Untuk lomba dengan sistem kualifikasi, satu performance dari satu tim dianggap satu lomba
 * Jadi, daftar peserta hanya akan berisi maksimal 2 peserta (Untuk sistem duel seperti sepak bola, pencak silat, catur, dll.)
 * Untuk lomba kualifikasi(Modern Dance, Band, Short Movie), hanya ada satu peserta tiap lomba
 * Created by inigo on 22/12/17.
 */

public class DataLomba {
    private String namaLomba = null;
    private Date waktuMulai = null;
    private ArrayList<DataLombaDetails> peserta = new ArrayList<>();
    private static final SimpleDateFormat tableFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    private static final SimpleDateFormat displayFormat =
            new SimpleDateFormat("dd MMM, HH:mm", Locale.US);

    public DataLomba(){}

    /**
     * Membuat objek DataLomba baru dari Cursor
     * Hanya akan mengambil data lomba, Tidak akan memasukkan data peserta
     * Setelah konstruksi, variabel peserta tetap kosong
     * @param cursor Cursur yang berisi data lomba
     */
    public DataLomba(Cursor cursor){
        if(cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_NAMA) != -1){
            this.namaLomba = cursor.getString(cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_NAMA));
        }

        try {
            String date = null, time = null;
            if (cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_DATE) != -1) {
                date = cursor.getString(cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_DATE));
            }
            if (cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_WAKTU) != -1) {
                time = cursor.getString(cursor.getColumnIndex(DatabaseContract.Lomba.COLUMN_WAKTU));
            }
            if (date != null && time != null) {
                this.waktuMulai = tableFormat.parse(date + " " + time);
            }
        }catch(ParseException e){
            Log.w("DataLomba", "Tanggal/waktu mulai lomba di Cursor tidak valid!");
        }
    }

    /**
     * Memberikan nama lomba
     * @return Nama lomba, atau "Unknown" apabila nama lomba belum diketahui
     */
    public String getNamaLomba() {
        if(namaLomba == null){
            return "Unknown";
        }else {
            return namaLomba;
        }
    }

    /**
     * Memberikan waktu mulai lomba dalam format Date
     * @return waktu mulai lomba dalam format Date
     */
    public Date getWaktuMulaiDate() {
        return waktuMulai;
    }

    /**
     * Memberikan waktu mulai lomba dalam format simpel (eg. 21 Jan, 11:00)
     * @return Waktu lomba dalam format simpel, atau "TBA" bila waktu belum diketahui
     */
    public String getWaktuMulai(){
        if(waktuMulai == null){
            return "TBA";
        }else {
            return displayFormat.format(waktuMulai);
        }
    }

    /**
     * Memberikan waktu mulai lomba dalam format full (eg. 2018-01-21 11:00:00)
     * @return Waktu lomba dalam format penuh seperti di database, atau "TBA" bila waktu belum diketahui
     */
    public String getWaktuLombaFull(){
        if(waktuMulai == null){
            return "TBA";
        }else{
            return tableFormat.format(waktuMulai);
        }
    }

    /**
     * Memberikan dafter peserta dalam bentuk array biasa
     * @return Array yang berisi daftar peserta dalam bentuk DaftarLombaDetails
     */
    public DataLombaDetails[] getPeserta() {
        return peserta.toArray(new DataLombaDetails[0]);
    }

    /**
     * Memasukkan peserta baru dalam daftar, hanya bila jumlah peserta di daftar masih rasional (< 2)
     * @param peserta Data peserta yang ingin dimasukkan
     * @return True apabila data berhasil masuk, False bila gagal
     */
    public Boolean addPeserta(DataLombaDetails peserta){
        if(this.peserta.size() >= 2){
            return false;
        }else{
            this.peserta.add(peserta);
            return true;
        }
    }

    /**
     * Mengubah data dari daftar peserta
     * @param peserta Data peserta yang baru
     * @param i Index yang ingin diubah di daftar
     */
    public void changePeserta(DataLombaDetails peserta, Integer i){
        this.peserta.set(i, peserta);
    }

    /**
     * Mengubah nama lomba
     * @param namaLomba nama lomba yang baru
     */
    public void setNamaLomba(String namaLomba) {
        this.namaLomba = namaLomba;
    }

    /**
     * Mengubah daftar pessrta
     * @param peserta Daftar peserta yang baru
     * @return True bila daftar peserta berhasil diubah, False bila tidak
     */
    public Boolean setPeserta(ArrayList<DataLombaDetails> peserta) {
        if(peserta.size() <= 2) {
            this.peserta = peserta;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Mengubah waktu mulai
     * @param waktuMulai waktu mulai yang baru
     */
    public void setWaktuMulai(Date waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    /**
     * Mengubah waktu mulai lomba. Waktu mulai yang diberikan harus sesuai format waktu full (eg. 2018-01-21 11:00:00)
     * @param waktuMulai waktu mulai lomba yang baru
     * @return True apabila berhasil mengubah waktu mulai lomba, False apabila gagal
     */
    public Boolean setWaktuMulai(String waktuMulai) {
        try {
            this.waktuMulai = tableFormat.parse(waktuMulai);
            return true;
        }catch(ParseException e){
            return false;
        }
    }
}
