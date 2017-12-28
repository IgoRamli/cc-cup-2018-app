package org.osiskanisius.cccup.cccup2018.model;

/**
 * Created by inigo on 28/12/17.
 */

public interface ModelContract {
    void notifyPresenter();
    void setDBLoadedKey(Boolean value);
    Boolean isDatabaseLoaded();
}
