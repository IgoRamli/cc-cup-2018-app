package org.osiskanisius.cccup.cccup2018.service;

import android.app.Service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.osiskanisius.cccup.cccup2018.model.ModelContract;
import org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager;

import java.util.Map;

import static org.osiskanisius.cccup.cccup2018.model.preferences.PreferenceManager.DB_LOADED_KEY;

/**
 * Created by inigo on 12/01/18.
 */

public class BackgroundNotificationService extends FirebaseMessagingService {
    PreferenceManager mPref;
    ServiceModel mModel = new ServiceModel(this);

    @Override
    public void onMessageReceived(RemoteMessage message){
        mPref = new PreferenceManager(this);
        if(message.getData().size() > 0){
            if(message.getData().containsKey(DB_LOADED_KEY)){
                mPref.writeData(DB_LOADED_KEY, false);
                mModel.populateData();
            }
        }
    }
}
