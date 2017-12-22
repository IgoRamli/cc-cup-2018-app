package org.osiskanisius.cccup.cccup2018.model;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import org.osiskanisius.cccup.cccup2018.internet.DataLoader;

import java.util.HashMap;
import java.util.Set;

public class JadwalSQLOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cc_cup_2018.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public JadwalSQLOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + JadwalSQLContract.Bidang.TABLE_NAME + "("
        + JadwalSQLContract.Bidang._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + JadwalSQLContract.Bidang.COLUMN_NAMA + " TEXT NOT NULL, "
        + JadwalSQLContract.Bidang.COLUMN_NAMA_DB + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.Sekolah.TABLE_NAME + "("
        + JadwalSQLContract.Sekolah._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + JadwalSQLContract.Sekolah.COLUMN_NAMA + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.Lokasi.TABLE_NAME + "("
        + JadwalSQLContract.Lokasi._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + JadwalSQLContract.Lokasi.COLUMN_NAMA + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.Peserta.TABLE_NAME + "("
        + JadwalSQLContract.Peserta._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + JadwalSQLContract.Peserta.COLUMN_SEKOLAH_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.Peserta.COLUMN_BIDANG_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.Peserta.COLUMN_NAMA + " TEXT NOT NULL, "
        + "FOREIGN KEY (" + JadwalSQLContract.Peserta.COLUMN_SEKOLAH_ID
        + ") REFERENCES " + JadwalSQLContract.Sekolah.TABLE_NAME
        + "(" + JadwalSQLContract.Sekolah._ID + "), "
        + "FOREIGN KEY (" + JadwalSQLContract.Peserta.COLUMN_BIDANG_ID
        + ") REFERENCES " + JadwalSQLContract.Bidang.TABLE_NAME
        + "(" + JadwalSQLContract.Bidang._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.Lomba.TABLE_NAME + "("
        + JadwalSQLContract.Lomba._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + JadwalSQLContract.Lomba.COLUMN_BIDANG_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.Lomba.COLUMN_LOKASI_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.Lomba.COLUMN_NAMA + " TEXT NOT NULL, "
        + JadwalSQLContract.Lomba.COLUMN_DATE + " DATE, "
        + JadwalSQLContract.Lomba.COLUMN_WAKTU + " TIME, "
        + "FOREIGN KEY (" + JadwalSQLContract.Lomba.COLUMN_BIDANG_ID
        + ") REFERENCES " + JadwalSQLContract.Bidang.TABLE_NAME
        + "(" + JadwalSQLContract.Bidang._ID + "), "
        + "FOREIGN KEY (" + JadwalSQLContract.Lomba.COLUMN_LOKASI_ID
        + ") REFERENCES " + JadwalSQLContract.Lokasi.TABLE_NAME
        + "(" + JadwalSQLContract.Lokasi._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.LombaDetails.TABLE_NAME + "("
        + JadwalSQLContract.LombaDetails.COLUMN_LOMBA_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.LombaDetails.COLUMN_PESERTA_ID + " INTEGER NOT NULL, "
        + JadwalSQLContract.LombaDetails.COLUMN_SKOR_PESERTA + " INTEGER, "
        + "FOREIGN KEY (" + JadwalSQLContract.LombaDetails.COLUMN_LOMBA_ID
        + ") REFERENCES " + JadwalSQLContract.Lomba.TABLE_NAME
        + "(" + JadwalSQLContract.Lomba._ID + "), "
        + "FOREIGN KEY (" + JadwalSQLContract.LombaDetails.COLUMN_PESERTA_ID
        + ") REFERENCES " + JadwalSQLContract.Peserta.TABLE_NAME
        + "(" + JadwalSQLContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.PoolDetails.TABLE_NAME + "("
        + JadwalSQLContract.PoolDetails.COLUMN_PESERTA_ID + " INTEGER  PRIMARY KEY NOT NULL, "
        + JadwalSQLContract.PoolDetails.COLUMN_POOL + " TEXT NOT NULL, "
        + "FOREIGN KEY (" + JadwalSQLContract.PoolDetails.COLUMN_PESERTA_ID
        + ") REFERENCES " + JadwalSQLContract.Peserta.TABLE_NAME
        + "(" + JadwalSQLContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.PencaksilatDetails.TABLE_NAME + "("
        + JadwalSQLContract.PencaksilatDetails._ID + " INTEGER PRIMARY KEY NOT NULL, "
        + JadwalSQLContract.PencaksilatDetails.COLUMN_KELAS + " TET NOT NULL, "
        + "FOREIGN KEY (" + JadwalSQLContract.PencaksilatDetails._ID
        + ") REFERENCES " + JadwalSQLContract.Peserta.TABLE_NAME
        + "(" + JadwalSQLContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + JadwalSQLContract.TaekwondoDetails.TABLE_NAME + "("
        + JadwalSQLContract.TaekwondoDetails._ID + " INTEGER PRIMARY KEY NOT NULL, "
        + JadwalSQLContract.TaekwondoDetails.COLUMN_KELAS + " TET NOT NULL, "
        + "FOREIGN KEY (" + JadwalSQLContract.TaekwondoDetails._ID
        + ") REFERENCES " + JadwalSQLContract.Peserta.TABLE_NAME
        + "(" + JadwalSQLContract.Peserta._ID + ")"
        + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.TaekwondoDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.PencaksilatDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.PoolDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.LombaDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.Lomba.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.Peserta.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.Lokasi.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.Sekolah.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + JadwalSQLContract.Bidang.TABLE_NAME);

        onCreate(db);
    }

    public void insertToTable(String tableName, HashMap<String,String> data){
        ContentValues values = new ContentValues();
        Set<String> keys = data.keySet();
        for(String key : keys){
            values.put(key, data.get(key));
            Log.v("Presenter", "Put ("+key+", "+data.get(key)+") to values");
        }
        long result = getWritableDatabase()
                .insert(tableName, null, values);
        Log.v("Presenter", "Data insert Result: "+result);
    }

    public void populateData(){
        Log.v("Presenter", "Start populating!");
        DataLoader loader = new DataLoader(this);
        loader.execute(JadwalSQLContract.Bidang.TABLE_NAME);
        Log.v("Presenter", "Data populated");
    }
}
