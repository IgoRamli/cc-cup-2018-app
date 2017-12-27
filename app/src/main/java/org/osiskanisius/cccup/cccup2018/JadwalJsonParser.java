package org.osiskanisius.cccup.cccup2018;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osiskanisius.cccup.cccup2018.data.Lomba;
import org.osiskanisius.cccup.cccup2018.data.LombaDetails;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by inigo on 12/12/17.
 */

public class JadwalJsonParser {
    static final String CHECKER = "safetyFlag";
    static final String NAMA_LOMBA = "namaLomba";
    static final String TANGGAL_LOMBA = "date";
    static final String WAKTU_LOMBA = "waktuMulai";
    static final String DAFTAR_PESERTA = "arrPeserta";
    static final String NAMA_LOKASI = "namaLokasi";
    static final String NAMA_PESERTA = "namaPeserta";
    static final String NAMA_SEKOLAH = "namaSekolah";
    static final String SKOR_PESERTA = "skorPeserta";

    /**
     * Mengambil data dari JSON yang dihasilkan setelah memanggil test_json.php
     * Susunan String: Nama_Lomba Tanggal-Waktu_Mulai (Peserta-Sekolah-Skor, Peserta-Sekolah-Skor) Lokasi
     * @param json string yang ingin diambil datanya
     * @return array of String. Setiap elemen dalam array berisi data satu lomba
     * @throws JSONException apabila terjadi kesalahan dalam mengambil JSON
     */
    public static Lomba[] parseSimpleJadwal(String json) throws JSONException, ParseException {
        JSONObject daftarLomba = new JSONObject(json);
        Iterator<String> keys = daftarLomba.keys();
        int jumlahLomba = daftarLomba.length()-1;
        if(jumlahLomba < 0) return null;

        Lomba[] hasilAkhir = new Lomba[jumlahLomba];
        for(int idx = 0; keys.hasNext();){
            String key = keys.next();
            if(key.equals(CHECKER)) continue;
            Lomba result = new Lomba();
            JSONObject dataLomba = daftarLomba.getJSONObject(key);
            result.setNamaLomba(dataLomba.getString(NAMA_LOMBA));
            String waktu = dataLomba.getString(TANGGAL_LOMBA)+" "+dataLomba.getString(WAKTU_LOMBA);
            result.setWaktuMulai(waktu);

            JSONArray daftarPeserta = dataLomba.getJSONArray(DAFTAR_PESERTA);
            int jumlahPeserta = daftarPeserta.length();
            for(int i = 0; i < jumlahPeserta; i++){
                JSONObject dataPeserta = daftarPeserta.getJSONObject(i);
                LombaDetails res = new LombaDetails(dataPeserta.getString(NAMA_PESERTA),
                                                    dataPeserta.getString(NAMA_SEKOLAH),
                                                    dataPeserta.getInt(SKOR_PESERTA));
                result.addPeserta(res);
            }
            hasilAkhir[idx] = result;
            idx++;//Fuck you...
        }
        return hasilAkhir;
    }

    public static ArrayList<HashMap<String, String>> parseTable(String json) throws JSONException{
        JSONArray rows = new JSONArray(json);
        ArrayList<HashMap<String, String>> hasilAkhir = new ArrayList<>();
        for(int i = 0; i < rows.length(); i++){
            JSONObject row = rows.getJSONObject(i);
            Iterator<String> keys = row.keys();
            HashMap<String, String> rowResult = new HashMap<>();
            while(keys.hasNext()){
                String key = keys.next();
                rowResult.put(key, row.getString(key));
            }
            hasilAkhir.add(rowResult);
        }
        return hasilAkhir;
    }
}
