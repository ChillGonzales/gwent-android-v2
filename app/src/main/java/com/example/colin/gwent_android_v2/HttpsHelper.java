package com.example.colin.gwent_android_v2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by colin.monroe on 7/7/2017.
 */

public class HttpsHelper {
    public static String getHttpsRequest(String connectionStr) {
        HttpsURLConnection apiConnection = null;
        try {
            URL endPoint = new URL(connectionStr);
            apiConnection = (HttpsURLConnection) endPoint.openConnection();
            apiConnection.setRequestMethod("GET");
            apiConnection.setRequestProperty("Accept","*/*");
            apiConnection.connect();
            if (apiConnection.getResponseCode() != 200) {
                return "An error code of " + apiConnection.getResponseCode() + " was returned by the request.";
            }
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            //JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            return responseStrBuilder.toString();
        } catch (Exception e){
            return e.toString();
        } finally {
            apiConnection.disconnect();
        }
    }
    public static Bitmap getHttpsImage(String connectionStr) {
        HttpsURLConnection apiConnection = null;
        try {
            URL endPoint = new URL(connectionStr);
            apiConnection = (HttpsURLConnection) endPoint.openConnection();
            apiConnection.setRequestMethod("GET");
            apiConnection.setRequestProperty("Accept","*/*");
            apiConnection.connect();
            if (apiConnection.getResponseCode() != 200) {
                return null;
            }
            //BufferedReader streamReader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
            Bitmap image = BitmapFactory.decodeStream(apiConnection.getInputStream());
            return image;
        } catch (Exception e){
            return null;
        } finally {
            apiConnection.disconnect();
        }
    }
}
