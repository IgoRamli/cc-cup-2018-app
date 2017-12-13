package org.osiskanisius.cccup.cccup2018.model;

import android.provider.BaseColumns;

/**
 * Created by inigo on 13/12/17.
 */

public final class JadwalSQLContract {
    //Empty PRIVATE constructor, agar tak ada idiot yang membuat objek baru dgn kelas ini
    private JadwalSQLContract(){}

    public static class Bidang implements BaseColumns{
        public static final String TABLE_NAME = "bidang";
        public static final String COLUMN_NAMA = "nama";
    }

    public static class Sekolah implements BaseColumns{
        public static final String TABLE_NAME = "sekolah";
        public static final String COLUMN_NAMA = "nama";
    }

    public static class Peserta implements BaseColumns{
        public static final String TABLE_NAME = "peserta";
        public static final String COLUMN_BIDANG_ID = "bidangID";
        public static final String COLUMN_SEKOLAH_ID = "sekolahID";
        public static final String COLUMN_NAMA = "nama";
    }

    public static class Lokasi implements BaseColumns{
        public static final String TABLE_NAME = "lokasi";
        public static final String COLUMN_NAMA = "nama";
    }

    public static class Lomba implements BaseColumns{
        public static final String TABLE_NAME = "lomba";
        public static final String COLUMN_BIDANG_ID = "bidangID";
        public static final String COLUMN_LOKASI_ID = "lokasiID";
        public static final String COLUMN_NAMA = "nama";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WAKTU = "waktuMulai";
    }

    public static class LombaDetails implements BaseColumns{
        public static final String TABLE_NAME = "lombaDetails";
        public static final String COLUMN_PESERTA_ID = "pesertaID";
        public static final String COLUMN_LOMBA_ID = "lombaID";
        public static final String COLUMN_SKOR_PESERTA = "skorPeserta";
    }

    public static class PoolDetails implements BaseColumns{
        public static final String TABLE_NAME = "poolDetails";
        public static final String COLUMN_PESERTA_ID = "pesertaID";
        public static final String COLUMN_POOL = "pool";
    }

    public static class PencaksilatDetails implements BaseColumns{
        public static final String TABLE_NAME = "pencaksilatDetails";
        public static final String COLUMN_KELAS = "kelas";
    }

    public static class TaekwondoDetails implements BaseColumns{
        public static final String TABLE_NAME = "taekwondoDetails";
        public static final String COLUMN_KELAS = "kelas";
    }
}
