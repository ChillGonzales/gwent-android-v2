package com.example.colin.gwent_android_v2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.GridLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Colin on 7/16/2017.
 */

public class CardManager {
    static CardManager instance;
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    private String baseUri = "https://api.gwentapi.com/v0/";
    private String cardsEndpoint = "cards";
    private Context mContext;

    public CardManager(Context context) {
        instance = this;
        mContext = context;
        IntentFilter resultFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        final CardResultReceiver receiver = new CardResultReceiver();
        final ArtworkReceiver artworkReceiver = new ArtworkReceiver();

        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver, resultFilter);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(artworkReceiver, resultFilter);

        // Start intent service for getting the page of cards
        Intent mServiceIntent = new Intent(mContext, RetrieveCardsIntentService.class);
        mServiceIntent.setData(Uri.parse(baseUri + cardsEndpoint + "?limit=30&offset=40"));
        mContext.startService(mServiceIntent);
    }
    public void getCardsCallback(String cards) {
        try {
            JSONObject cardsJson = new JSONObject(cards);
            JSONArray cardArray = (JSONArray) cardsJson.get("results");

            for (int i = 0; i < cardArray.length(); i++) {
                // Get card info from results array
                JSONObject card = cardArray.getJSONObject(i);
                String[] split = card.get("href").toString().split("/");
                CardInfo ci = new CardInfo(card.get("name").toString(), split[split.length - 1]);
                names.add(ci.name);

                //Read variation data from assets file
                StringBuilder text = new StringBuilder();

                try {
                    InputStream input = mContext.getAssets().open(ci.uuid + ".vrtn");
                    BufferedReader br = new BufferedReader(new InputStreamReader(input));
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                    handleVariationData(text.toString());
                }
                catch (Exception e) {
                    //TODO: Add error handling
                    Toast toast1 = Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG);
                    toast1.show();
                }
            }
            //After all the images are grabbed from online, populate the image adapter

        } catch (Exception ex) {
            //TODO: Add proper error handling
        }
    }
    private void handleVariationData(String variationData) {
        try {
            JSONObject arr = new JSONObject(variationData);
            // Get artwork data from variation
            Intent mArtworkIntent = new Intent(mContext, RetrieveArtworkIntentService.class);
            JSONObject art = new JSONObject(arr.get("Art").toString());
            mArtworkIntent.setData(Uri.parse(art.get("ThumbnailImage").toString()));
            mContext.startService(mArtworkIntent);
        } catch (Exception e) {
            //TODO: Add error handling
            Toast toast = Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void getArtworkCallback(Bitmap img) {
        images.add(img);
    }
    public static CardManager getInstance() {
        return instance;
    }
    public Bitmap[] getCardsArtwork() {
        return images.toArray(new Bitmap[images.size()]);
    }
    public String[] getCardNames() { return names.toArray(new String[names.size()]); }
    public boolean downloadComplete() {
        return names.size() <= images.size();
    }
}
