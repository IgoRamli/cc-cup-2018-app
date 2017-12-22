package org.osiskanisius.cccup.cccup2018.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by inigo on 22/12/17.
 */

public class Lomba {
    private String namaLomba = null;
    private Date waktuMulai = null;
    private ArrayList<LombaDetails> peserta = new ArrayList<>();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

    public Lomba(String namaLomba, String waktuMulai){
        this.namaLomba = namaLomba;
        try {
            this.waktuMulai = formatter.parse(waktuMulai);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getNamaLomba(){
        if(namaLomba == null){
            return "TBA";
        }else{
            return namaLomba;
        }
    }

    public String getWaktuMulai(){
        if(waktuMulai == null){
            return "TBA";
        }else{
            return waktuMulai.toString();
        }
    }

    public ArrayList<LombaDetails> getPeserta(){
        return peserta;
    }

    public void setNamaLomba(String namaLomba){
        this.namaLomba = namaLomba;
    }

    public void setWaktuMulai(String waktuMulai) throws Exception{
        this.waktuMulai = formatter.parse(waktuMulai);
    }
}
