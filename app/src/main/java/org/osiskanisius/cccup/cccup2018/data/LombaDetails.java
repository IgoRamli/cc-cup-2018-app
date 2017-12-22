package org.osiskanisius.cccup.cccup2018.data;

/**
 * Created by inigo on 22/12/17.
 */

public class LombaDetails {
    private String namaPeserta = null;
    private String namaSekolah = null;
    private int skorPeserta = -1;

    public LombaDetails(String namaPeserta, String namaSekolah, int skorPeserta){
        this.namaPeserta = namaPeserta;
        this.namaSekolah = namaSekolah;
        this.skorPeserta = skorPeserta;
    }

    public String getNamaPeserta(){
        if(namaPeserta == null){
            return "TBA";
        }else{
            return namaPeserta;
        }
    }
    public String getNamaSekolah(){
        if(namaSekolah == null){
            return "TBA";
        }else{
            return namaSekolah;
        }
    }

    public String getSkorPeserta(){
        if(skorPeserta == -1){
            return "TBA";
        }else{
            return Integer.toString(skorPeserta);
        }
    }

    public LombaDetails setNamaPeserta(String namaPeserta){
        this.namaPeserta = namaPeserta;
        return this;
    }

    public LombaDetails setNamaSekolah(String namaSekolah){
        this.namaSekolah = namaSekolah;
        return this;
    }

    public LombaDetails setSkorPeserta(int skorPeserta){
        this.skorPeserta = skorPeserta;
        return this;
    }
}
