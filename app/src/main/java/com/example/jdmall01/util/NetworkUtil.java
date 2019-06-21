package com.example.jdmall01.util;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NetworkUtil {
    public  static String doGet(String urlPath, HashMap<String,String> params) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String paramStr = ""; //数据来源
            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
            for (HashMap.Entry<String, String> entry: entrySet) {
                paramStr += ("&" + entry.getKey() + "=" + entry.getValue());
            }
            paramStr = paramStr.substring(1);
            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());

            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                return buf.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";


    }

    public static String doPost(String urlPath, HashMap<String,String> params) {
        // 使用 HttpURLConnection Volley AsyncHttpClient
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            String paramStr = ""; //数据来源
            Set<HashMap.Entry<String, String>> entrySet = params.entrySet();
            for (HashMap.Entry<String, String> entry: entrySet) {
                paramStr += ("&" + entry.getKey() + "=" + entry.getValue());
            }
            paramStr = paramStr.substring(1);
            conn.setDoOutput(true);
            conn.getOutputStream().write(paramStr.getBytes());

            if (conn.getResponseCode()==200) {
                InputStream is = conn.getInputStream();
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                Log.e("Network", "ok");
                return buf.readLine();
            } else {
                Log.e("Network", "error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }
}
