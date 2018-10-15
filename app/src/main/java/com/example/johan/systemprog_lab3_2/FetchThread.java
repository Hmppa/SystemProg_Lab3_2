package com.example.johan.systemprog_lab3_2;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchThread extends Thread {

    public interface ThreadNotifier {
        void fetchingData(String message);
    }

    private ThreadNotifier listener = null;

    public void setListener(ThreadNotifier listener) {
        this.listener = listener;
    }

    public String uString;

    @Override
    public void run() {

        NetworkLoader networkLoader = new NetworkLoader(uString);
        try{

            String data = networkLoader.getData();
            if (listener != null) {
                Log.d("Lecture3", "if");
                this.listener.fetchingData(data);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
