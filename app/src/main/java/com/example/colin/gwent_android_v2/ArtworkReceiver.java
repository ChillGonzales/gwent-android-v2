package com.example.colin.gwent_android_v2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Created by Colin on 7/16/2017.
 */

public class ArtworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras().get(Constants.ARTWORK_RESULT) != null) {
            CardManager.getInstance().getArtworkCallback((Bitmap) intent.getExtras().get(Constants.ARTWORK_RESULT));
        }
    }
}
