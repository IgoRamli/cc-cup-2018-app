package org.osiskanisius.cccup.cccup2018.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by inigo on 22/12/17.
 */

public class Lomba implements Parcelable{
    private String namaLomba = null;
    private Date waktuMulai = null;
    private ArrayList<LombaDetails> peserta = new ArrayList<>();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.US);

    public Lomba(){}

    public Lomba(String namaLomba, String waktuMulai){
        this.namaLomba = namaLomba;
        try {
            this.waktuMulai = formatter.parse(waktuMulai);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Lomba(Parcel in) {
        try{
            namaLomba = in.readString();
            waktuMulai = formatter.parse(in.readString());
            peserta = in.createTypedArrayList(LombaDetails.CREATOR);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public static final Creator<Lomba> CREATOR = new Creator<Lomba>() {
        @Override
        public Lomba createFromParcel(Parcel in) {
            return new Lomba(in);
        }

        @Override
        public Lomba[] newArray(int size) {
            return new Lomba[size];
        }
    };

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

    public void addPeserta(LombaDetails peserta){
        this.peserta.add(peserta);
    }

    public ArrayList<LombaDetails> getPeserta(){
        return peserta;
    }

    public void setNamaLomba(String namaLomba){
        this.namaLomba = namaLomba;
    }

    public void setWaktuMulai(String waktuMulai) throws ParseException{
        this.waktuMulai = formatter.parse(waktuMulai);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.namaLomba);
        parcel.writeString(this.waktuMulai.toString());
        parcel.writeList(this.peserta);
    }
}
