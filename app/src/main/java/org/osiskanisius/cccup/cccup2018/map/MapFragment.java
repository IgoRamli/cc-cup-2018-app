package org.osiskanisius.cccup.cccup2018.map;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

import org.osiskanisius.cccup.cccup2018.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    private static final String TAG = "MapFragment";
    //IndoorAtlas
    private IALocationManager mLocationManager;
    private IALocationListener mIALocationListener = new IALocationListener() {
        // Called when the location has changed.
        @Override
        public void onLocationChanged(IALocation location) {
            Log.d(TAG, "Latitude: " + location.getLatitude());
            Log.d(TAG, "Longitude: " + location.getLongitude());
            Log.d(TAG, "Floor number: " + location.getFloorLevel());

            mLog.append("Latitude: " + location.getLatitude() + "\n");
            mLog.append("Longitude: " + location.getLongitude() + "\n");
            mLog.append("Floor number: " + location.getFloorLevel() + "\n\n");
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };

    private TextView mLog;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager = IALocationManager.create(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_map, container, false);
        mLog = view.findViewById(R.id.log_tv);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), mIALocationListener);
    }

}
