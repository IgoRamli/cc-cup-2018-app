package org.osiskanisius.cccup.cccup2018.jadwal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.osiskanisius.cccup.cccup2018.JadwalJsonParser;
import org.osiskanisius.cccup.cccup2018.NetworkUtil;
import org.osiskanisius.cccup.cccup2018.R;

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
    //private OnFragmentInteractionListener mListener;
    private String[][] mockData = {
            {"Sepak Bola 1", "Sepak Bola 2", "Sepak Bola 3", "Sepak Bola 4", "Sepak Bola 5"},
            {"Bola Voli Putra 1", "Bola Voli Putra 2", "Bola Voli Putra 3", "Bola Voli Putra 4", "Bola Voli Putra 5"},
            {"Bola Voli Putri 1", "Bola Voli Putri 2", "Bola Voli Putri 3", "Bola Voli Putri 4", "Bola Voli Putri 5"},
            {"Catur 1", "Catur 2", "Catur 3", "Catur 4", "Catur 5"}
    };
    private TextView listData;
    private Spinner jadwalSpinner;
    private int spinnerPosition = 0;

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
        listData = (TextView) getView().findViewById(R.id.tv_jadwal);
        jadwalSpinner = (Spinner) getView().findViewById(R.id.jadwal_spinner);
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
    }

    void changeJadwalType(int type){
        listData.setText("");
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
            for(String dataLomba : hasilAkhir){
                listData.append(dataLomba+"\n\n\n");
            }
        }
    }
}
