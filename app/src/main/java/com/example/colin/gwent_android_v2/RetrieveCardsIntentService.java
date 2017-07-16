package com.example.colin.gwent_android_v2;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Colin on 7/16/2017.
 */

public class RetrieveCardsIntentService extends IntentService {
    public RetrieveCardsIntentService(String name) {
        super(name);
    }
    public RetrieveCardsIntentService() {
        super("cardPageIntent");
    }
    @Override
    protected void onHandleIntent(Intent workIntent) {
        try {
            //Gets data from the incoming intent
            String data = workIntent.getDataString();
            String returnVal = HttpsHelper.getHttpsRequest(data);
            /*
             * Creates a new Intent containing a Uri object
             * BROADCAST_ACTION is a custom Intent action
            */
            Intent localIntent = new Intent(Constants.BROADCAST_ACTION)
                            // Puts the status into the Intent
                            .putExtra(Constants.CARD_PAGE_RESULT, returnVal);
            // Broadcasts the Intent to receivers in this app.
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
        catch (Exception e) {
            //TODO: handle error
        }
    }
}
