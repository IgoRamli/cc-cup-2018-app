package org.osiskanisius.cccup.cccup2018.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseArray;

import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.data.DataLombaDetails;
import org.osiskanisius.cccup.cccup2018.data.DataPeserta;
import org.osiskanisius.cccup.cccup2018.model.ModelContract;
import org.osiskanisius.cccup.cccup2018.model.internet.DataPacket;
import org.osiskanisius.cccup.cccup2018.model.internet.DatabaseLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cc_cup_2018.db";
    private static final int DATABASE_VERSION = 1;
    private ModelContract mManager;

    public DatabaseHelper(ModelContract manager, Context context){
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
        + DatabaseContract.Lomba.COLUMN_KETERANGAN + " TEXT, "
        + "FOREIGN KEY (" + DatabaseContract.Lomba.COLUMN_BIDANG_ID
        + ") REFERENCES " + DatabaseContract.Bidang.TABLE_NAME
        + "(" + DatabaseContract.Bidang._ID + "), "
        + "FOREIGN KEY (" + DatabaseContract.Lomba.COLUMN_LOKASI_ID
        + ") REFERENCES " + DatabaseContract.Lokasi.TABLE_NAME
        + "(" + DatabaseContract.Lokasi._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.LombaDetails.TABLE_NAME + "("
        + DatabaseContract.LombaDetails._ID + " INTEGER NOT NULL, "
        + DatabaseContract.LombaDetails.COLUMN_LOMBA_ID + " INTEGER NOT NULL, "
        + DatabaseContract.LombaDetails.COLUMN_PESERTA_ID + " INTEGER NOT NULL, "
        + DatabaseContract.LombaDetails.COLUMN_SKOR_PESERTA + " INTEGER, "
        + DatabaseContract.LombaDetails.COLUMN_STATUS_PESERTA + " TEXT, "
        + "FOREIGN KEY (" + DatabaseContract.LombaDetails.COLUMN_LOMBA_ID
        + ") REFERENCES " + DatabaseContract.Lomba.TABLE_NAME
        + "(" + DatabaseContract.Lomba._ID + "), "
        + "FOREIGN KEY (" + DatabaseContract.LombaDetails.COLUMN_PESERTA_ID
        + ") REFERENCES " + DatabaseContract.Peserta.TABLE_NAME
        + "(" + DatabaseContract.Peserta._ID + ")"
        + ");");

        db.execSQL("CREATE TABLE " + DatabaseContract.PoolDetails.TABLE_NAME + "("
        + DatabaseContract.PoolDetails._ID + " INTEGER  PRIMARY KEY NOT NULL, "
        + DatabaseContract.PoolDetails.COLUMN_POOL + " TEXT NOT NULL, "
        + "FOREIGN KEY (" + DatabaseContract.PoolDetails._ID
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

    /**
     * Memasukkan data ke dalam tabel
     * @param data Data yang ingin dimasukkan
     */
    public void insertToTable(DataPacket data){
        for(HashMap<String, String> row : data.getData()){
            ContentValues content = new ContentValues();
            for(String key : row.keySet()){
                Log.d("DatabaseHelper", "Put "+key+" = "+row.get(key));
                content.put(key, row.get(key));
            }
            getWritableDatabase().insertWithOnConflict(data.getTableName(),
                    null,
                    content,
                    SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    /**
     * Mengambil data dari web cccup.osiskanisius.org untuk dimasukkan ke dalam tabel
     * Dengan fungsi ini, semua tabel diupdate
     */
    public void populateData(){
        Boolean allUpToDate = true;
        DatabaseLoader loader = new DatabaseLoader(this);
        loader.execute(DatabaseContract.tables);
    }

    /**
     * Memasukkan data yang telah diambil dari wek ke tabel, sekaligus memberitahukan Presenter
     * bahwa data telah berhasil dimasukkan (Bila notifyPresenter menyala)<br>
     * Selain itu, mengupdate Preference apabila data telah berhasil/gagal diupdate
     * @param hasil Array berisi data yang telah diambil
     */
    public void onLoadingComplete(DataPacket[] hasil){
        Boolean loadingResult = true;
        for(DataPacket tabel : hasil){
            insertToTable(tabel);
            loadingResult &= tabel.getStatus();
        }
        mManager.setDBLoadedKey(loadingResult);
        mManager.notifyPresenter();
    }

    /**
     * Mendapatkan daftar bidang dari SQLite Database
     * @return String array yang berisi daftar semua bidang
     */
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
        result.close();
        return list.toArray(new String[0]);
    }

    private Cursor getListLomba(String condition, String order, int maxSize){
        String tLomba, tLombaDetails, tPeserta, tLokasi, tBidang;
        tLomba = DatabaseContract.Lomba.TABLE_NAME;
        tLombaDetails = DatabaseContract.LombaDetails.TABLE_NAME;
        tPeserta = DatabaseContract.Peserta.TABLE_NAME;
        tLokasi = DatabaseContract.Lokasi.TABLE_NAME;
        tBidang = DatabaseContract.Bidang.TABLE_NAME;
        String query = "SELECT * FROM " +
                leftJoin(
                        leftJoin(
                                leftJoin(
                                        tLombaDetails,
                                        leftJoin(
                                                tLomba,
                                                tBidang,
                                                DatabaseContract.Lomba.COLUMN_BIDANG_ID + "=" +
                                                        DatabaseContract.Bidang._ID),
                                        DatabaseContract.Lomba._ID + "=" +
                                                DatabaseContract.LombaDetails.COLUMN_LOMBA_ID),
                                tPeserta,
                                DatabaseContract.Peserta._ID + "=" +
                                        DatabaseContract.LombaDetails.COLUMN_PESERTA_ID),
                        tLokasi,
                        DatabaseContract.Lokasi._ID + "=" +
                                DatabaseContract.Lomba.COLUMN_LOKASI_ID
                );
        if(condition != null){
            query += " WHERE " + condition;
        }
        if(order != null){
            query += " ORDER BY " + order;
        }
        if(maxSize > 0){
            query += " LIMIT " + maxSize;
        }
        return getReadableDatabase().rawQuery(query, new String[0]);
    }

    /**
     * Memberikan data semua lomba pada bidang tertentu
     * @param bidangID ID dari bidang yang lombanya ingin dicari
     * @return Array yang berisi semua data lomba yang dicari
     */
    public DataLomba[] getListLomba(Integer bidangID){
        Cursor result = getListLomba(DatabaseContract.Bidang._ID + " = " + bidangID, null, -1);

        SparseArray<DataLomba> hasil = new SparseArray<>();
        while(result.moveToNext()){
            DataLombaDetails row = new DataLombaDetails(result);
            Integer id = result.getInt(result.getColumnIndex(DatabaseContract.Lomba._ID));
            if(hasil.get(id) == null){
                DataLomba rowLomba = new DataLomba(result);
                hasil.append(id, rowLomba);
            }
            hasil.get(id).addPeserta(row);
        }

        DataLomba[] hasilAkhir = new DataLomba[hasil.size()];
        for(int i = 0; i < hasilAkhir.length; i++){
            hasilAkhir[i] = hasil.valueAt(i);
        }

        result.close();

        return hasilAkhir;
    }

    private String leftJoin(String left, String right, String condition){
        return "("+left+") LEFT JOIN ("+right+") ON ("+condition+")";
    }

    /**
     * Memberikan data peserta dengan ID yang diinginkan
     * @param pesertaID ID dari peserta yang dicari
     * @return Data peserta dengan ID ygn diberikan
     */
    public DataPeserta getInfoPeserta(Integer pesertaID){
        Cursor result = getReadableDatabase().rawQuery(
                "SELECT * FROM "
                + leftJoin(
                        leftJoin(
                                leftJoin(
                                        leftJoin(
                                                DatabaseContract.Peserta.TABLE_NAME,
                                                DatabaseContract.Sekolah.TABLE_NAME,
                                                DatabaseContract.Peserta.COLUMN_SEKOLAH_ID + "=" +
                                                        DatabaseContract.Sekolah._ID),
                                        DatabaseContract.PoolDetails.TABLE_NAME,
                                        DatabaseContract.Peserta._ID + "=" +
                                                DatabaseContract.PoolDetails._ID),
                                DatabaseContract.PencaksilatDetails.TABLE_NAME,
                                DatabaseContract.Peserta._ID + "=" +
                                        DatabaseContract.PencaksilatDetails._ID),
                        DatabaseContract.TaekwondoDetails.TABLE_NAME,
                        DatabaseContract.Peserta._ID + "=" +
                                DatabaseContract.TaekwondoDetails._ID) +
                        " WHERE " + DatabaseContract.Peserta._ID + " = " + pesertaID,
                new String[0]);
        result.moveToNext();
        DataPeserta returnResult = new DataPeserta(result);
        result.close();
        return returnResult;
    }

    /**
     * Memberikan data umum dari lomba dengan ID yang ditentukan
     * @param lombaID ID dari lomba yang diinginkan
     * @return Data umum lomba yang diinginkan
     */
    public DataLomba getLomba(Integer lombaID){
        String tLomba, tLombaDetails, tPeserta, tLokasi;
        tLomba = DatabaseContract.Lomba.TABLE_NAME;
        tLombaDetails = DatabaseContract.LombaDetails.TABLE_NAME;
        tPeserta = DatabaseContract.Peserta.TABLE_NAME;
        tLokasi = DatabaseContract.Lokasi.TABLE_NAME;
        Cursor result = getReadableDatabase().rawQuery(
                "SELECT * FROM " +
                        leftJoin(
                                leftJoin(
                                        leftJoin(
                                                tLombaDetails,
                                                tLomba,
                                                DatabaseContract.Lomba._ID + "=" +
                                                        DatabaseContract.LombaDetails.COLUMN_LOMBA_ID),
                                        tPeserta,
                                        DatabaseContract.Peserta._ID + "=" +
                                                DatabaseContract.LombaDetails.COLUMN_PESERTA_ID),
                                tLokasi,
                                DatabaseContract.Lokasi._ID + "=" +
                                        DatabaseContract.Lomba.COLUMN_LOKASI_ID
                        ) +
                        " WHERE " + DatabaseContract.Lomba._ID + " = " + lombaID,
                new String[0]
        );
        Log.d("DatabaseHelper", "Jumlah Peserta = " + result.getCount());
        DataLomba hasil = null;
        while(result.moveToNext()){
            if(hasil == null){
                hasil = new DataLomba(result);
            }
            DataLombaDetails peserta = new DataLombaDetails(result);
            Log.d("DatabaseHelper", "Insert " + hasil.addPeserta(peserta).toString());
        }
        result.close();
        return hasil;
    }

    public DataLomba[] getUpcomingLomba(int size){
        String condition = "date('now') < " + DatabaseContract.Lomba.COLUMN_DATE
                + " OR (date('now') = " + DatabaseContract.Lomba.COLUMN_DATE + " AND time('now') < "
                + DatabaseContract.Lomba.COLUMN_WAKTU + ")";
        String order = DatabaseContract.Lomba.COLUMN_DATE + " , "
                + DatabaseContract.Lomba.COLUMN_WAKTU;
        Cursor result = getListLomba(condition, order, size*2);//INGAT! Ada lomba yang jumlah pemainnya 2

        SparseArray<DataLomba> hasil = new SparseArray<>();
        while(result.moveToNext()){
            DataLombaDetails row = new DataLombaDetails(result);
            Integer id = result.getInt(result.getColumnIndex(DatabaseContract.Lomba._ID));
            if(hasil.get(id) == null){
                DataLomba rowLomba = new DataLomba(result);
                hasil.append(id, rowLomba);
            }
            hasil.get(id).addPeserta(row);
        }

        DataLomba[] hasilAkhir = new DataLomba[hasil.size()];
        for(int i = 0; i < hasilAkhir.length; i++){
            hasilAkhir[i] = hasil.valueAt(i);
        }

        result.close();

        return hasilAkhir;
    }
}
