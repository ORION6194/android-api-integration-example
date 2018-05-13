package com.sbmg_india.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ISRO on 12/25/2016.
 */

public class NetworkUtils {

    public static String HitURL(JSONObject jsonRequest, String url){
        String res=null;
        try{
/*
            System.setProperty("http.keepAlive", "false");*/
            HttpParams httpParameters = new BasicHttpParams();
            final int timeoutConnection = 10000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            final int timeoutSocket = 15000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new StringEntity(jsonRequest.toString()));
            httppost.setHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse response = httpclient.execute(httppost);
            if (response != null) {
                InputStream is = response.getEntity().getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (Exception e55) {
                    e55.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e56) {
                        e56.printStackTrace();
                    }
                }
                res = sb.toString();
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        return res;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static boolean isNull(String val){
        if(val==null||val.equals(null)||val.trim().equals("")||val.trim().equals("null")|| val.trim()==""||val.trim()=="null")
            return true;
        return false;
    }
}
