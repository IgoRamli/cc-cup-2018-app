package org.osiskanisius.cccup.cccup2018.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.osiskanisius.cccup.cccup2018.model.ModelManager;
import org.osiskanisius.cccup.cccup2018.model.internet.DataPacket;
import org.osiskanisius.cccup.cccup2018.model.internet.DatabaseLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cc_cup_2018.db";
    private static final int DATABASE_VERSION = 1;
    private ModelManager mManager;

    public DatabaseHelper(ModelManager manager, Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mManager = manager;
        if(!mManager.isDatabaseLoaded()){
            populateData();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + DatabaseContract.Bidang.TABLE_NAME + "("
        + DatabaseContract.Bidang._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DatabaseContract.Bidang.COLUMN_NAMA + " TEXT NOT NULL, "
        + DatabaseContract.Bidang.COLUMN_NAMA_DB + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.Sekolah.TABLE_NAME + "("
        + DatabaseContract.Sekolah._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DatabaseContract.Sekolah.COLUMN_NAMA + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.Lokasi.TABLE_NAME + "("
        + DatabaseContract.Lokasi._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DatabaseContract.Lokasi.COLUMN_NAMA + " TEXT NOT NULL"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.Peserta.TABLE_NAME + "("
        + DatabaseContract.Peserta._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DatabaseContract.Peserta.COLUMN_SEKOLAH_ID + " INTEGER NOT NULL, "
        + DatabaseContract.Peserta.COLUMN_BIDANG_ID + " INTEGER NOT NULL, "
        + DatabaseContract.Peserta.COLUMN_NAMA + " TEXT NOT NULL, "
        + "FOREIGN KEY (" + DatabaseContract.Peserta.COLUMN_SEKOLAH_ID
        + ") REFERENCES " + DatabaseContract.Sekolah.TABLE_NAME
        + "(" + DatabaseContract.Sekolah._ID + "), "
        + "FOREIGN KEY (" + DatabaseContract.Peserta.COLUMN_BIDANG_ID
        + ") REFERENCES " + DatabaseContract.Bidang.TABLE_NAME
        + "(" + DatabaseContract.Bidang._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.Lomba.TABLE_NAME + "("
        + DatabaseContract.Lomba._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + DatabaseContract.Lomba.COLUMN_BIDANG_ID + " INTEGER NOT NULL, "
        + DatabaseContract.Lomba.COLUMN_LOKASI_ID + " INTEGER NOT NULL, "
        + DatabaseContract.Lomba.COLUMN_NAMA + " TEXT NOT NULL, "
        + DatabaseContract.Lomba.COLUMN_DATE + " DATE, "
        + DatabaseContract.Lomba.COLUMN_WAKTU + " TIME, "
        + "FOREIGN KEY (" + DatabaseContract.Lomba.COLUMN_BIDANG_ID
        + ") REFERENCES " + DatabaseContract.Bidang.TABLE_NAME
        + "(" + DatabaseContract.Bidang._ID + "), "
        + "FOREIGN KEY (" + DatabaseContract.Lomba.COLUMN_LOKASI_ID
        + ") REFERENCES " + DatabaseContract.Lokasi.TABLE_NAME
        + "(" + DatabaseContract.Lokasi._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.LombaDetails.TABLE_NAME + "("
        + DatabaseContract.LombaDetails.COLUMN_LOMBA_ID + " INTEGER NOT NULL, "
        + DatabaseContract.LombaDetails.COLUMN_PESERTA_ID + " INTEGER NOT NULL, "
        + DatabaseContract.LombaDetails.COLUMN_SKOR_PESERTA + " INTEGER, "
        + "FOREIGN KEY (" + DatabaseContract.LombaDetails.COLUMN_LOMBA_ID
        + ") REFERENCES " + DatabaseContract.Lomba.TABLE_NAME
        + "(" + DatabaseContract.Lomba._ID + "), "
        + "FOREIGN KEY (" + DatabaseContract.LombaDetails.COLUMN_PESERTA_ID
        + ") REFERENCES " + DatabaseContract.Peserta.TABLE_NAME
        + "(" + DatabaseContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.PoolDetails.TABLE_NAME + "("
        + DatabaseContract.PoolDetails.COLUMN_PESERTA_ID + " INTEGER  PRIMARY KEY NOT NULL, "
        + DatabaseContract.PoolDetails.COLUMN_POOL + " TEXT NOT NULL, "
        + "FOREIGN KEY (" + DatabaseContract.PoolDetails.COLUMN_PESERTA_ID
        + ") REFERENCES " + DatabaseContract.Peserta.TABLE_NAME
        + "(" + DatabaseContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.PencaksilatDetails.TABLE_NAME + "("
        + DatabaseContract.PencaksilatDetails._ID + " INTEGER PRIMARY KEY NOT NULL, "
        + DatabaseContract.PencaksilatDetails.COLUMN_KELAS + " TET NOT NULL, "
        + "FOREIGN KEY (" + DatabaseContract.PencaksilatDetails._ID
        + ") REFERENCES " + DatabaseContract.Peserta.TABLE_NAME
        + "(" + DatabaseContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.TaekwondoDetails.TABLE_NAME + "("
        + DatabaseContract.TaekwondoDetails._ID + " INTEGER PRIMARY KEY NOT NULL, "
        + DatabaseContract.TaekwondoDetails.COLUMN_KELAS + " TET NOT NULL, "
        + "FOREIGN KEY (" + DatabaseContract.TaekwondoDetails._ID
        + ") REFERENCES " + DatabaseContract.Peserta.TABLE_NAME
        + "(" + DatabaseContract.Peserta._ID + ")"
        + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TaekwondoDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.PencaksilatDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.PoolDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.LombaDetails.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Lomba.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Peserta.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Lokasi.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Sekolah.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Bidang.TABLE_NAME);

        onCreate(db);
    }

    public void insertToTable(DataPacket data){
        for(HashMap<String, String> row : data.getData()){
            ContentValues content = new ContentValues();
            for(String key : row.keySet()){
                content.put(key, row.get(key));
            }
            getWritableDatabase().insertWithOnConflict(data.getTableName(),
                    null,
                    content,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public void populateData(){
        Boolean allUpToDate = true;
        DatabaseLoader loader = new DatabaseLoader(this);
        loader.execute(DatabaseContract.tables);
    }

    public void onLoadingComplete(DataPacket[] hasil){
        Boolean loadingResult = true;
        for(DataPacket tabel : hasil){
            insertToTable(tabel);
            loadingResult &= tabel.getStatus();
        }
        mManager.setDBLoadedKey(loadingResult);
        mManager.notifyPresenter();
    }

    public String[] getListLombaFromDB(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(DatabaseContract.Bidang.TABLE_NAME,
                new String[]{DatabaseContract.Bidang.COLUMN_NAMA},
                null,
                null,
                null,
                null,
                DatabaseContract.Bidang._ID);
        ArrayList<String> list = new ArrayList<>();
        while(result.moveToNext()){
            list.add(result.getString(
                    result.getColumnIndex(DatabaseContract.Bidang.COLUMN_NAMA)
            ));
        }
        return list.toArray(new String[0]);
    }
}
