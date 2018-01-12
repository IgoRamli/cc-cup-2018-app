package org.osiskanisius.cccup.cccup2018.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.adapter.CardViewAdapter;
import org.osiskanisius.cccup.cccup2018.data.DataLomba;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContract.View{
    private ListView mUpcomingEventsListView;

    private HomeContract.Presenter mPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUpcomingEventsListView = view.findViewById(R.id.card_list);
        setUpcomingEventsListViewData(mPresenter.getUpcomingLomba());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    public void setUpcomingEventsListViewData(DataLomba[] dataLomba){
        CardViewAdapter upcomingEventsAdapter = new CardViewAdapter(getActivity(),
                R.layout.home_event_card_item, dataLomba);
        mUpcomingEventsListView.setAdapter(upcomingEventsAdapter);
    }
}
