package com.example.johan.systemprog_lab3_2;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkLoader {

    private String allData;

    public NetworkLoader(String urlString) {


        this.loadStuff(urlString);
    }

    private void loadStuff(String urlString) {
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            allData = fromStream(in);


            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }
    public String getData(){
        return allData;
    }
    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
