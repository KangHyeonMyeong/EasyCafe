package com.example.we25.easycafe;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by we25 on 2017-07-07.
 */

public class InstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "Test";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }
    private  void sendRegistrationToServer(String token){

    }


}
