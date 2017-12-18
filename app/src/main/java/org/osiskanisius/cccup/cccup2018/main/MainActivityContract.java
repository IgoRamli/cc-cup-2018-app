package org.osiskanisius.cccup.cccup2018.main;

/**
 * Created by inigo on 18/12/17.
 */

public interface MainActivityContract {
    interface View{
        boolean changeFragment(short state);
    }

    interface Presenter{
        short getCurrentState();
        boolean onClickBottomNavBar(short state);
        void setCurrentState(short state);
    }
}
