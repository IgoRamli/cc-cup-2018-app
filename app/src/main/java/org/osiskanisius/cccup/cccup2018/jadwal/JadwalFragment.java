package org.osiskanisius.cccup.cccup2018.jadwal;

import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.osiskanisius.cccup.cccup2018.data.Lomba;
import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.adapter.JadwalRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
    //TODO (5) Poles UI dan dokumentasi

public class JadwalFragment extends Fragment
        implements JadwalContract.View, AdapterView.OnItemSelectedListener {
    //private OnFragmentInteractionListener mListener;\
    private TextView errorText;
    private TextView emptyText;
    private RecyclerView listData;
    private LinearLayout progressBar;
    private Spinner jadwalSpinner;
    private static final String[] emptySpinner = {"Data not available"};

    private String[] mListBidang;

    private JadwalRecyclerViewAdapter adapter;

    private JadwalContract.Presenter mPresenter;

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
        mPresenter = new JadwalPresenter(this);
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
        initializeViews();

        //Set adapter untuk Spinner
        setSpinnerAdapter(mPresenter.getListBidang());

        //Set adapter untuk RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        adapter = new JadwalRecyclerViewAdapter();
        listData.setLayoutManager(layoutManager);
        listData.setHasFixedSize(true);
        listData.setAdapter(adapter);
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

    public void initializeViews(){
        listData = (RecyclerView) getView().findViewById(R.id.rv_jadwal);
        errorText = (TextView) getView().findViewById(R.id.tv_error_msg);
        emptyText = (TextView) getView().findViewById(R.id.tv_empty_msg);
        progressBar = (LinearLayout) getView().findViewById(R.id.pb_loading_bar);
        jadwalSpinner = (Spinner) getView().findViewById(R.id.jadwal_spinner);
    }

    @Override
    public Context getViewContext(){
        return this.getContext();
    }

    @Override
    public void setSpinnerAdapter(String[] listBidang){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listBidang);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalSpinner.setAdapter(spinnerAdapter);
        jadwalSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void showProgressBar(){
        progressBar.setVisibility(LinearLayout.VISIBLE);
    }
    @Override
    public void hideProgressBar(){
        progressBar.setVisibility(LinearLayout.INVISIBLE);
    }
    @Override
    public void showErrorState(){
        errorText.setVisibility(TextView.VISIBLE);
    }
    @Override
    public void hideErrorState(){
        errorText.setVisibility(TextView.INVISIBLE);
    }
    @Override
    public void showEmptyState(){
        emptyText.setVisibility(TextView.VISIBLE);
    }
    @Override
    public void hideEmptyState(){
        emptyText.setVisibility(TextView.INVISIBLE);
    }
    @Override
    public void showJadwalLomba(){
        listData.setVisibility(RecyclerView.VISIBLE);
    }
    @Override
    public void hideJadwalLomba(){
        listData.setVisibility(RecyclerView.INVISIBLE);
    }
    @Override
    public void setJadwalLomba(Lomba[] hasilAkhir){
        adapter.setJadwalData(hasilAkhir);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.onItemSelected(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mPresenter.onNothingSelected();
    }
}
