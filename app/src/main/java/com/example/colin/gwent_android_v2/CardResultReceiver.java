package com.example.colin.gwent_android_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Colin on 7/16/2017.
 */

public class CardResultReceiver extends BroadcastReceiver {
    // Called when the BroadcastReceiver gets an Intent it's registered to receive
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras().get(Constants.CARD_PAGE_RESULT) != null) {
            CardManager.getInstance().getCardsCallback((String) intent.getExtras().get(Constants.CARD_PAGE_RESULT));
        }
    }
}

