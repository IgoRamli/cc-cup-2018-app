package org.osiskanisius.cccup.cccup2018.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.osiskanisius.cccup.cccup2018.DetailActivity;
import org.osiskanisius.cccup.cccup2018.R;

/**
 * Created by inigo on 13/12/17.
 */

public class JadwalRecyclerViewAdapter
        extends RecyclerView.Adapter<JadwalRecyclerViewAdapter.JadwalViewHolder> {
    String[] jadwalData;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent gotoDetail = new Intent(view.getContext(), DetailActivity.class);
            TextView mTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView mDesc = (TextView) view.findViewById(R.id.tv_jadwal);
            String dataTitle = mTitle.getText().toString();
            String dataDesc = mDesc.getText().toString();
            gotoDetail.putExtra(Intent.EXTRA_TITLE, dataTitle);
            gotoDetail.putExtra(Intent.EXTRA_TEXT, dataDesc);
            view.getContext().startActivity(gotoDetail);
            //Toast.makeText(view.getContext(), "Item selected", Toast.LENGTH_LONG).show();
        }
    };
    
    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.jadwal_list_item, viewGroup, false);
        JadwalViewHolder viewHolder = new JadwalViewHolder(view);
        view.setOnClickListener(mOnClickListener);

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
        TextView jadwalTextView, titleTextView;
        public JadwalViewHolder(View view){
            super(view);
            jadwalTextView = (TextView) view.findViewById(R.id.tv_jadwal);
            titleTextView = (TextView) view.findViewById(R.id.tv_title);
        }

        void bind(String data){
            String[] subData = data.split("\n", 2);
            titleTextView.setText(subData[0]);
            jadwalTextView.setText(subData[1]);
        }
    }

    public void setJadwalData(String[] jadwalData){
        this.jadwalData = jadwalData;
        notifyDataSetChanged();
    }
}
