package org.osiskanisius.cccup.cccup2018.jadwal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.osiskanisius.cccup.cccup2018.JadwalJsonParser;
import org.osiskanisius.cccup.cccup2018.NetworkUtil;
import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.adapter.JadwalRecyclerViewAdapter;

import java.io.IOException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

    //TODO (3) Perbaiki layout (RecyclerView?)
    //TODO (4) Simpan data di SQLite
    //TODO (5) Poles UI dan dokumentasi

public class JadwalFragment extends Fragment {
    //private OnFragmentInteractionListener mListener;\
    private TextView errorText;
    private TextView emptyText;
    private RecyclerView listData;
    private ProgressBar progressBar;
    private Spinner jadwalSpinner;

    private JadwalRecyclerViewAdapter adapter;

    //String bidang sementara!!
    //TODO: Ganti string array agar sesuai dengan tabel bidang di MySQL
    String[] listBidang = {"Sepak Bola", "Futsal", "Bola Basket", "Bola Voli Putra", "Bola Voli Putri",
                            "Bulu Tangkis Putra", "Bulu Tangkis Putri", "Tenis Meja", "Modern Dance", "Fotografi",
                            "Pencak Silat", "Tae Kwon Do", "Paskibra", "Panjat Tebing Putra", "Panjat Tebing Putri",
                            "Billiard", "Catur", "Band"};

    public JadwalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment JadwalFragment.
     */
    public static JadwalFragment newInstance() {
        JadwalFragment fragment = new JadwalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_jadwal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        listData = (RecyclerView) getView().findViewById(R.id.rv_jadwal);
        errorText = (TextView) getView().findViewById(R.id.tv_error_msg);
        emptyText = (TextView) getView().findViewById(R.id.tv_empty_msg);
        progressBar = (ProgressBar) getView().findViewById(R.id.pb_loading_bar);
        jadwalSpinner = (Spinner) getView().findViewById(R.id.jadwal_spinner);

        //Set adapter untuk Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listBidang);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalSpinner.setAdapter(spinnerAdapter);
        jadwalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeJadwalType(i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeJadwalType(0);
            }
        });

        //Set adapter untuk RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        adapter = new JadwalRecyclerViewAdapter();
        listData.setLayoutManager(layoutManager);
        listData.setHasFixedSize(true);
        listData.setAdapter(adapter);
    }

    /**
     * Mengubah jadwal yang ditampilkan sesuai bidang pada spinner
     * @param type setara bidangID. Bidang yang dipilih di spinner
     */
    void changeJadwalType(int type){
        FetchDataTask internet = new FetchDataTask();
        internet.execute(NetworkUtil.makeWebQuery(type));
    }

    /*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);/*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *
     * Tidak Diperlukan Sekarang
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    public class FetchDataTask extends AsyncTask<URL, Void, String[]> {
        @Override
        public void onPreExecute(){
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        public String[] doInBackground(URL... url){
            URL request = url[0];
            try{
                String result;
                result = NetworkUtil.getResponse(request);
                String[] hasilAkhir = JadwalJsonParser.parseSimpleJadwal(result);
                return  hasilAkhir;
            }catch(IOException e){
                e.printStackTrace();
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(String[] hasilAkhir){
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            if(hasilAkhir == null){
                displayErrorMessage();
            }else if(hasilAkhir.length == 0){
                displayEmptyMessage();
            }else{
                displayJadwalLomba();
                adapter.setJadwalData(hasilAkhir);
            }
        }

        private void displayErrorMessage(){
            errorText.setVisibility(TextView.VISIBLE);
            emptyText.setVisibility(TextView.INVISIBLE);
            listData.setVisibility(TextView.INVISIBLE);
        }

        private void displayEmptyMessage(){
            errorText.setVisibility(TextView.INVISIBLE);
            emptyText.setVisibility(TextView.VISIBLE);
            listData.setVisibility(TextView.INVISIBLE);
        }

        private void displayJadwalLomba(){
            errorText.setVisibility(TextView.INVISIBLE);
            emptyText.setVisibility(TextView.INVISIBLE);
            listData.setVisibility(TextView.VISIBLE);
        }
    }
}
