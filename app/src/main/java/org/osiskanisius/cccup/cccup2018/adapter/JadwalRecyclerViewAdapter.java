package org.osiskanisius.cccup.cccup2018.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osiskanisius.cccup.cccup2018.R;

/**
 * Created by inigo on 13/12/17.
 */

public class JadwalRecyclerViewAdapter
        extends RecyclerView.Adapter<JadwalRecyclerViewAdapter.JadwalViewHolder> {
    String[] jadwalData;

    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.jadwal_list_item, viewGroup, false);
        JadwalViewHolder viewHolder = new JadwalViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder viewHolder, int i){
        viewHolder.bind(jadwalData[i]);
    }

    @Override
    public int getItemCount(){
        if(jadwalData == null) return 0;
        else return jadwalData.length;
    }

    class JadwalViewHolder extends RecyclerView.ViewHolder{
        TextView jadwalTextView;
        public JadwalViewHolder(View view){
            super(view);
            jadwalTextView = (TextView) view.findViewById(R.id.tv_jadwal);
        }

        void bind(String data){
            jadwalTextView.setText(data);
        }
    }

    public void setJadwalData(String[] jadwalData){
        this.jadwalData = jadwalData;
        notifyDataSetChanged();
    }
}
