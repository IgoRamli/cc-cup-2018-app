package org.osiskanisius.cccup.cccup2018.data;

import android.database.Cursor;

import org.osiskanisius.cccup.cccup2018.model.database.DatabaseContract;

/**
 * Kelas untuk menympan data peserta (Nama peserta, Nama Sekolah, dan Info Spesial<br>
 * Kelas ini sudah diberi pengaman sehingga tidak akan memberikan nilai null saat mengambil data<br>
 * Created by inigo on 27/12/17.
 */

public class DataPeserta {
    private Integer pesertaID;
    private String namaPeserta = null;
    private String namaSekolah = null;
    private String infoSpesial = null;

    public DataPeserta(){}

    public DataPeserta(Cursor cursor){
        String colNama, colSekolah, colPool, colPencaksilat, colTaekwondo;
        colNama = DatabaseContract.Peserta.COLUMN_NAMA;
        colSekolah = DatabaseContract.Sekolah.COLUMN_NAMA;
        colPool = DatabaseContract.PoolDetails.COLUMN_POOL;
        colPencaksilat = DatabaseContract.PencaksilatDetails.COLUMN_KELAS;
        colTaekwondo = DatabaseContract.TaekwondoDetails.COLUMN_KELAS;
        if(cursor.getColumnIndex(DatabaseContract.Peserta._ID) != -1){
            pesertaID = cursor.getInt(cursor.getColumnIndex(DatabaseContract.Peserta._ID));
        }
        if(cursor.getColumnIndex(colNama) != -1) {
            namaPeserta = cursor.getString(cursor.getColumnIndex(colNama));
        }
        if(cursor.getColumnIndex(colSekolah) != -1) {
            namaSekolah = cursor.getString(cursor.getColumnIndex(colSekolah));
        }
        if(cursor.getColumnIndex(colPool) != -1){
            infoSpesial = cursor.getString(cursor.getColumnIndex(colPool));
        }
        if(cursor.getColumnIndex(colPencaksilat) != -1){
            infoSpesial = cursor.getString(cursor.getColumnIndex(colPencaksilat));
        }
        if(cursor.getColumnIndex(colTaekwondo) != -1){
            infoSpesial = cursor.getString(cursor.getColumnIndex(colTaekwondo));
        }

        if(namaPeserta != null){
            if(namaSekolah == null) namaSekolah = "";
            if(infoSpesial == null) infoSpesial = "";
        }
    }

    @Override
    public String toString(){
        if(namaPeserta == null){
            return "TBA";
        }else{
            String baseString = namaPeserta+"/"+namaSekolah;
            if(infoSpesial.equals("")){
                return baseString;
            }else{
                return baseString+" ("+infoSpesial+")";
            }
        }
    }

    /**
     * Memberikan ID peserta
     * @return ID peserta
     */
    public Integer getPesertaID(){
        return pesertaID;
    }

    /**
     * Memberikan nama peserta
     * @return nama peserta, atau "TBA" apabila tidak ada data peserta yang tersimpan
     */
    public String getNamaPeserta(){
        if(namaPeserta == null){
            return "TBA";
        }else{
            return namaPeserta;
        }
    }

    /**
     * Memberikan nama sekolah
     * @return nama sekolah asal peserta<br>
     *         "Unknown" apabila nama sekolah asal peserta tidak diketahui<br>
     *         "TBA" apabila tidak ada data peserta yang tersimpan di sini
     */
    public String getNamaSekolah() {
        if(namaPeserta == null){
            return "TBA";
        }else if(namaSekolah.equals("")){
            return "Unknown";
        }else{
            return namaSekolah;
        }
    }

    /**
     * Memberikan info tambahan dari peserta (Kode pool tim/kelas peserta di Pencak Silat/Tae Kwon Do)
     * @return info spesial peserta, atau "" (Empty String) apabila tidak ada info spesial
     */
    public String getInfoSpesial() {
        if(namaPeserta == null){
            return "TBA";
        }else{
            return "";
        }
    }

    /**
     * Mengubah data info spesial, hanya bila memang ada data peserta yang tersimpan
     * @param infoSpesial info yang hendak disimpan
     * @return true apabila data berhasil diubah, false bila tidak
     */
    public Boolean setInfoSpesial(String infoSpesial) {
        if(isDataExists()) {
            this.infoSpesial = infoSpesial;
            return true;
        }
        return false;
    }

    /**
     * Mengubah nama peserta. Semua data lain akan direset
     * @param namaPeserta nama peserta yang baru
     */
    public void setNamaPeserta(String namaPeserta) {
        this.namaPeserta = namaPeserta;
        this.namaSekolah = "";
        this.infoSpesial = "";
    }

    /**
     * Mengubah nama sekolah asal peserta, hanya bila memang ada data peserta yang disimpan
     * @param namaSekolah nama sekolah asal yang baru
     * @return true apabila data berhasil diubah, false bila tidak
     */
    public Boolean setNamaSekolah(String namaSekolah) {
        if(isDataExists()) {
            this.namaSekolah = namaSekolah;
            return true;
        }
        return false;
    }

    /**
     * Memberitahukan apakah ada data yang tersimpan dalam variabel ini
     * @return true apabila ada data, false bila tidak
     */
    public Boolean isDataExists(){
        return (namaPeserta != null);
    }
}