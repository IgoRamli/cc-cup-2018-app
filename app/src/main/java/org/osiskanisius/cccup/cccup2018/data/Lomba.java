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
    private String namaLomba = "TBA";
    private Date waktuMulai = null;
    private ArrayList<LombaDetails> peserta = new ArrayList<>();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

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
            peserta = in.readArrayList(LombaDetails.class.getClassLoader());
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
        if(this.namaLomba == null || this.namaLomba.equals("")){
            return "TBA";
        }else{
            return this.namaLomba;
        }
    }

    public String getWaktuMulai(){
        if(waktuMulai == null){
            return "TBA";
        }else{
            return formatter.format(waktuMulai);
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
        parcel.writeString(getNamaLomba());
        parcel.writeString(getWaktuMulai());
        parcel.writeList(this.peserta);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getWaktuMulai()+"\n");
        for(LombaDetails row : peserta){
            builder.append("->"+row.toString());
        }
        return builder.toString();
    }
}
