package org.osiskanisius.cccup.cccup2018.jadwal_lomba;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osiskanisius.cccup.cccup2018.R;

public class JadwalFragment extends Fragment implements JadwalContract.View{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public JadwalFragment() {
        // Required empty public constructor
    }

    public static JadwalFragment newInstance() {
        JadwalFragment fragment = new JadwalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
        return view;
    }
}