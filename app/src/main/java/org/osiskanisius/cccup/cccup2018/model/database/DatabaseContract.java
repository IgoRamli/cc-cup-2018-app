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
        public static final String _ID = TABLE_NAME+"."+"bidangID";
        public static final String COLUMN_NAMA = TABLE_NAME+"."+"nama";
        public static final String COLUMN_NAMA_DB = TABLE_NAME+"."+"namaDB";
    }

    public static class Sekolah implements BaseColumns{
        public static final String TABLE_NAME = "sekolah";
        public static final String _ID = TABLE_NAME+"."+"sekolahID";
        public static final String COLUMN_NAMA = TABLE_NAME+"."+"nama";
    }

    public static class Peserta implements BaseColumns{
        public static final String TABLE_NAME = "peserta";
        public static final String _ID = TABLE_NAME+"."+"pesertaID";
        public static final String COLUMN_BIDANG_ID = TABLE_NAME+"."+"bidangID";
        public static final String COLUMN_SEKOLAH_ID = TABLE_NAME+"."+"sekolahID";
        public static final String COLUMN_NAMA = TABLE_NAME+"."+"nama";
    }

    public static class Lokasi implements BaseColumns{
        public static final String TABLE_NAME = "lokasi";
        public static final String _ID = TABLE_NAME+"."+"lokasiID";
        public static final String COLUMN_NAMA = TABLE_NAME+"."+"nama";
    }

    public static class Lomba implements BaseColumns{
        public static final String TABLE_NAME = "lomba";
        public static final String _ID = TABLE_NAME+"."+"lombaID";
        public static final String COLUMN_BIDANG_ID = TABLE_NAME+"."+"bidangID";
        public static final String COLUMN_LOKASI_ID = TABLE_NAME+"."+"lokasiID";
        public static final String COLUMN_NAMA = TABLE_NAME+"."+"nama";
        public static final String COLUMN_DATE = TABLE_NAME+"."+"date";
        public static final String COLUMN_WAKTU = TABLE_NAME+"."+"waktuMulai";
    }

    public static class LombaDetails implements BaseColumns{
        public static final String TABLE_NAME = "lombaDetails";
        public static final String _ID = TABLE_NAME+"."+"lombaDetailsID";
        public static final String COLUMN_PESERTA_ID = TABLE_NAME+"."+"pesertaID";
        public static final String COLUMN_LOMBA_ID = TABLE_NAME+"."+"lombaID";
        public static final String COLUMN_SKOR_PESERTA = TABLE_NAME+"."+"skorPeserta";
    }

    public static class PoolDetails implements BaseColumns{
        public static final String TABLE_NAME = "poolDetails";
        public static final String COLUMN_PESERTA_ID = TABLE_NAME+"."+"pesertaID";
        public static final String COLUMN_POOL = TABLE_NAME+"."+"pool";
    }

    public static class PencaksilatDetails implements BaseColumns{
        public static final String TABLE_NAME = "pencaksilatDetails";
        public static final String COLUMN_KELAS = TABLE_NAME+"."+"kelas";
    }

    public static class TaekwondoDetails implements BaseColumns{
        public static final String TABLE_NAME = "taekwondoDetails";
        public static final String COLUMN_KELAS = TABLE_NAME+"."+"kelas";
    }
}
