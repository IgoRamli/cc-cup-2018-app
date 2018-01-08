package org.osiskanisius.cccup.cccup2018.model.database;

import android.provider.BaseColumns;

/**
 * Created by inigo on 13/12/17.
 */

public final class DatabaseContract {
    //Empty PRIVATE constructor, agar tak ada idiot yang membuat objek baru dgn kelas ini
    private DatabaseContract(){}

    public static final String[] tables = {
            Bidang.TABLE_NAME,
            Sekolah.TABLE_NAME,
            Lokasi.TABLE_NAME,
            Peserta.TABLE_NAME,
            Lomba.TABLE_NAME,
            LombaDetails.TABLE_NAME,
            PoolDetails.TABLE_NAME,
            PencaksilatDetails.TABLE_NAME,
            TaekwondoDetails.TABLE_NAME
    };

    public static class Bidang implements BaseColumns{
        public static final String TABLE_NAME = "bidang";
        public static final String _ID = "bidangID"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA = "nama"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA_DB = "namaDB"+"_"+TABLE_NAME;
    }

    public static class Sekolah implements BaseColumns{
        public static final String TABLE_NAME = "sekolah";
        public static final String _ID = "sekolahID"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA = "nama"+"_"+TABLE_NAME;
    }

    public static class Peserta implements BaseColumns{
        public static final String TABLE_NAME = "peserta";
        public static final String _ID = "pesertaID"+"_"+TABLE_NAME;
        public static final String COLUMN_BIDANG_ID = "bidangID"+"_"+TABLE_NAME;
        public static final String COLUMN_SEKOLAH_ID = "sekolahID"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA = "nama"+"_"+TABLE_NAME;
    }

    public static class Lokasi implements BaseColumns{
        public static final String TABLE_NAME = "lokasi";
        public static final String _ID = "lokasiID"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA = "nama"+"_"+TABLE_NAME;
    }

    public static class Lomba implements BaseColumns{
        public static final String TABLE_NAME = "lomba";
        public static final String _ID = "lombaID"+"_"+TABLE_NAME;
        public static final String COLUMN_BIDANG_ID = "bidangID"+"_"+TABLE_NAME;
        public static final String COLUMN_LOKASI_ID = "lokasiID"+"_"+TABLE_NAME;
        public static final String COLUMN_NAMA = "nama"+"_"+TABLE_NAME;
        public static final String COLUMN_DATE = "date"+"_"+TABLE_NAME;
        public static final String COLUMN_WAKTU = "waktuMulai"+"_"+TABLE_NAME;
        public static final String COLUMN_KETERANGAN = "keterangan"+"_"+TABLE_NAME;
    }

    public static class LombaDetails implements BaseColumns{
        public static final String TABLE_NAME = "lombaDetails";
        public static final String _ID = "lombaDetailsID"+"_"+TABLE_NAME;
        public static final String COLUMN_PESERTA_ID = "pesertaID"+"_"+TABLE_NAME;
        public static final String COLUMN_LOMBA_ID = "lombaID"+"_"+TABLE_NAME;
        public static final String COLUMN_SKOR_PESERTA = "skorPeserta"+"_"+TABLE_NAME;
        public static final String COLUMN_STATUS_PESERTA = "statusPeserta"+"_"+TABLE_NAME;
    }

    public static class PoolDetails implements BaseColumns{
        public static final String TABLE_NAME = "poolDetails";
        public static final String _ID = "pesertaID"+"_"+TABLE_NAME;
        public static final String COLUMN_POOL = "poolID"+"_"+TABLE_NAME;
    }

    public static class PencaksilatDetails implements BaseColumns{
        public static final String TABLE_NAME = "pencaksilatDetails";
        public static final String _ID = "pesertaID"+"_"+TABLE_NAME;
        public static final String COLUMN_KELAS = "kelas"+"_"+TABLE_NAME;
    }

    public static class TaekwondoDetails implements BaseColumns{
        public static final String TABLE_NAME = "taekwondoDetails";
        public static final String _ID = "pesertaID"+"_"+TABLE_NAME;
        public static final String COLUMN_KELAS = "kelas"+"_"+TABLE_NAME;
    }
}
