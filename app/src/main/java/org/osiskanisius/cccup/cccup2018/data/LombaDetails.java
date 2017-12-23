package org.osiskanisius.cccup.cccup2018.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by inigo on 22/12/17.
 */

public class LombaDetails implements Parcelable{
    private String namaPeserta = null;
    private String namaSekolah = null;
    private int skorPeserta = -1;

    public LombaDetails(){}

    public LombaDetails(Parcel in){
        this.namaPeserta = in.readString();
        this.namaSekolah = in.readString();
        this.skorPeserta = in.readInt();
    }

    public LombaDetails(String namaPeserta, String namaSekolah, int skorPeserta){
        this.namaPeserta = namaPeserta;
        this.namaSekolah = namaSekolah;
        this.skorPeserta = skorPeserta;
    }

    public static final Creator<LombaDetails> CREATOR = new Creator<LombaDetails>() {
        @Override
        public LombaDetails createFromParcel(Parcel in) {
            return new LombaDetails(in);
        }

        @Override
        public LombaDetails[] newArray(int size) {
            return new LombaDetails[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.namaPeserta);
        parcel.writeString(this.namaSekolah);
        parcel.writeInt(this.skorPeserta);
    }
}
