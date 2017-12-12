package org.osiskanisius.cccup.cccup2018;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by inigo on 12/12/17.
 */

public class JadwalJsonParser {
    final String NAMA_LOMBA = "namaLomba";
    final String TANGGAL_LOMBA = "date";
    final String WAKTU_LOMBA = "waktuMulai";
    final String DAFTAR_PESERTA = "arrPeserta";
    final String NAMA_LOKASI = "namaLokasi";
    final String NAMA_PESERTA = "namaPeserta";
    final String NAMA_SEKOLAH = "namaSekolah";
    final String SKOR_PESERTA = "skorPeserta";

    /**
     * Mengambil data dari JSON yang dihasilkan setelah memanggil test_json.php
     * Susunan String: Nama_Lomba Tanggal-Waktu_Mulai (Peserta-Sekolah-Skor, Peserta-Sekolah-Skor) Lokasi
     * @param json string yang ingin diambil datanya
     * @return array of String. Setiap elemen dalam array berisi data satu lomba
     * @throws JSONException apabila terjadi kesalahan dalam mengambil JSON
     */
    String[] parseSimpleJadwal(String json) throws JSONException{
        JSONObject daftarLomba = new JSONObject(json);
        Iterator<String> keys = daftarLomba.keys();
        int jumlahLomba = daftarLomba.length();

        String[] hasilAkhir = new String[jumlahLomba];

        for(int idx = 0; keys.hasNext(); idx++){//Iterasi semua lomba yang ada
            String key = keys.next();
            String result = "";
            JSONObject dataLomba = daftarLomba.getJSONObject(key);
            String namaLomba = dataLomba.getString(NAMA_LOMBA);
            String namaLokasi = dataLomba.getString(NAMA_LOKASI);
            String tanggalLomba = dataLomba.getString(TANGGAL_LOMBA);
            String waktuMulai = dataLomba.getString(WAKTU_LOMBA);

            result += namaLomba+" "+tanggalLomba+"-"+waktuMulai+" (";

            JSONArray daftarPeserta = dataLomba.getJSONArray(DAFTAR_PESERTA);
            int jumlahPeserta = daftarPeserta.length();
            for(int i = 0; i < jumlahPeserta; i++){
                if(i > 0) result += " ";
                JSONObject dataPeserta = daftarPeserta.getJSONObject(i);
                String namaPeserta = dataPeserta.getString(NAMA_PESERTA);
                String namaSekolah = dataPeserta.getString(NAMA_SEKOLAH);
                String skorPeserta = dataPeserta.getString(SKOR_PESERTA);
                result += namaPeserta+"-"+namaSekolah+"-"+skorPeserta;
            }
            result += ") "+namaLokasi;
            hasilAkhir[idx] = result;
        }
        return hasilAkhir;
    }
}
