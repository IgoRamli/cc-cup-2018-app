package org.osiskanisius.cccup.cccup2018.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.data.DataLomba;
import org.osiskanisius.cccup.cccup2018.detail.DetailActivity;

import java.util.List;

/**
 * Created by inigo on 11/01/18.
 */

public class CardViewAdapter extends ArrayAdapter<DataLomba> {
    private int size;
    private ListView parent;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent gotoDetail = new Intent(view.getContext(), DetailActivity.class);
            int position = parent.getPositionForView(view);
            gotoDetail.putExtra("lombaID", getItem(position).getLombaID());
            view.getContext().startActivity(gotoDetail);
            //Toast.makeText(view.getContext(), "Item selected", Toast.LENGTH_LONG).show();
        }
    };

    public CardViewAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CardViewAdapter(Context context, int resource, DataLomba[] items) {
        super(context, resource, items);
        size = items.length;
    }

    @Override
    public int getCount(){
        return size;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        this.parent = (ListView) parent;
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.home_event_card_item, null);
        }

        DataLomba data = getItem(position);

        Log.d("CardViewAdapter", "Getting view");

        if(data != null){
            TextView mNamaBidang = v.findViewById(R.id.nama_bidang_tv);
            TextView mNamaLomba = v.findViewById(R.id.nama_lomba_tv);
            TextView mNamaPeserta = v.findViewById(R.id.nama_peserta_tv);
            TextView mWaktuMulai = v.findViewById(R.id.waktu_mulai_tv);
            Log.d("CardViewAdapter", "Generate data (" + position + ")\n" + data.toString());
            //Log.d("CardViewAdapter", "TV init (" + (mNamaBidang != null) + (mNamaLomba != null) + (mNamaPeserta != null) + (mWaktuMulai != null) + ")");

            mNamaLomba.setText(data.getNamaLomba());
            mNamaBidang.setText(data.getNamaBidang());
            mWaktuMulai.setText(data.getWaktuMulaiFromNow());
            mNamaPeserta.setText(data.getPesertaHeadline());
        }

        v.setOnClickListener(mOnClickListener);

        return v;
    }
}
