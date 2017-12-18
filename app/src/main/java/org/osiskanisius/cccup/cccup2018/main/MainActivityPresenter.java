package org.osiskanisius.cccup.cccup2018.main;

/**
 * Created by inigo on 18/12/17.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{
    //State Constants
    public static final short HOME_STATE = 1;
    public static final short JADWAL_STATE = 2;

    //Bundle keys
    public static final String CURRENT_STATE_KEY = "currentState";

    //Saved variable
    private short currentState;

    //View yang dikontrol
    MainActivityContract.View mView;

    public MainActivityPresenter(MainActivityContract.View view){
        currentState = 1;
        mView = view;
    }

    @Override
    public short getCurrentState(){
        return currentState;
    }

    @Override
    public void setCurrentState(short state){
        currentState = state;
    }

    @Override
    public boolean onClickBottomNavBar(short state){
        setCurrentState(state);
        return mView.changeFragment(state);
    }
}
