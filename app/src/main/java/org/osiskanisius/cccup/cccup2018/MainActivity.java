package org.osiskanisius.cccup.cccup2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import org.osiskanisius.cccup.cccup2018.home.HomeFragment;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalFragment;
import org.osiskanisius.cccup.cccup2018.model.JadwalSQLOpenHelper;

public class MainActivity extends FragmentActivity {
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
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment, home)
                            .commit();
                    return true;
                case R.id.navigation_jadwal:
                    if(jadwal == null){
                        jadwal = new JadwalFragment();
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment, jadwal)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
