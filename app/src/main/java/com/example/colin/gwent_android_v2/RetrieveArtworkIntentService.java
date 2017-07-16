package com.example.colin.gwent_android_v2;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Colin on 7/16/2017.
 */

public class RetrieveArtworkIntentService extends IntentService {
    public RetrieveArtworkIntentService(String name) {
        super(name);
    }
    public RetrieveArtworkIntentService() {
        super("retrieveVariationIntent");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        try {
            //Gets data from the incoming intent
            String data = workIntent.getDataString();
            Bitmap returnVal = HttpsHelper.getHttpsImage(data);

            Intent localIntent = new Intent(Constants.BROADCAST_ACTION)
                    // Puts the status into the Intent
                    .putExtra(Constants.ARTWORK_RESULT, returnVal);
            // Broadcasts the Intent to receivers in this app.
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
        catch (Exception e) {
            //TODO: Add error handling
        }
    }
}

