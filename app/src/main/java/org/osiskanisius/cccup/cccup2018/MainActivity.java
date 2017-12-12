package org.osiskanisius.cccup.cccup2018;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.osiskanisius.cccup.cccup2018.home.HomeFragment;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalFragment;

public class MainActivity extends AppCompatActivity {
    Fragment mainFragment;
    FragmentManager fragmentManager;

    HomeFragment home;
    JadwalFragment jadwal;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(home == null){
                        home = new HomeFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.main_fragment, home).commit();
                    return true;
                case R.id.navigation_jadwal:
                    if(jadwal == null){
                        jadwal = new JadwalFragment();
                    }
                    fragmentManager.beginTransaction().replace(R.id.main_fragment, jadwal).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
