package org.osiskanisius.cccup.cccup2018.main;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.home.HomeFragment;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalFragment;

public class MainActivity extends FragmentActivity implements MainActivityContract.View {
    private HomeFragment home;
    private JadwalFragment jadwal;

    private BottomNavigationView navigation;

    private MainActivityContract.Presenter mPresenter;

    private static final Integer CODE_PERMISSIONS = 69;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return mPresenter.onClickBottomNavBar(getItemState(item.getItemId()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestIndoorAtlasPermission();
        setContentView(R.layout.activity_main);

        initializeAll();
        loadSavedState(savedInstanceState);
        loadMainFragment();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState){
        saveState.putShort(MainActivityPresenter.CURRENT_STATE_KEY, mPresenter.getCurrentState());
        super.onSaveInstanceState(saveState);
    }

    /**
     * Mengubah fragment utama
     * @param state ID dari fragment yang ingin ditampilkan. ID harus diambil dari Presenter
     * @return true apabila fragment berhasil diganti,
     *         false apabila fragment tidak berubah
     */
    @Override
    public boolean changeFragment(short state){
        switch (state){
            case MainActivityPresenter.HOME_STATE:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, home)
                        .commit();
                return true;
            case MainActivityPresenter.JADWAL_STATE:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, jadwal)
                        .commit();
                return true;
        }
        return false;
    }

    private short getItemState(int itemID){
        switch (itemID){
            case R.id.navigation_home:
                return MainActivityPresenter.HOME_STATE;
            case R.id.navigation_jadwal:
                return MainActivityPresenter.JADWAL_STATE;
            default:
                return 0;
        }
    }

    /**
     * Menginisialisasi semua variabel
     * Hanya menginisialisasi! Fungsi ini tidak mengutak-atik variabel
     */
    private void initializeAll(){
        mPresenter = new MainActivityPresenter(this);
        home = HomeFragment.newInstance();
        jadwal = JadwalFragment.newInstance();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

    private void loadSavedState(Bundle savedState){
        if(savedState == null){
            return;
        }
        short lastSavedState = savedState.getShort(
                MainActivityPresenter.CURRENT_STATE_KEY,
                MainActivityPresenter.HOME_STATE
        );
        mPresenter.setCurrentState(lastSavedState);
    }

    private void loadMainFragment(){
        short currentState = mPresenter.getCurrentState();
        changeFragment(currentState);
    }

    private void requestIndoorAtlasPermission(){
        String[] neededPermissions = {
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions( this, neededPermissions, CODE_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Handle if any of the permissions are denied, in grantResults
    }
}
