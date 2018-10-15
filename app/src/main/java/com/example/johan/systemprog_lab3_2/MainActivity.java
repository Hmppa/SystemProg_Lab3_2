package com.example.johan.systemprog_lab3_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements FetchThread.ThreadNotifier {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void fetchStuff(View view)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
        EditText editText = (EditText) findViewById(R.id.editText);
//        String urlString = editText.toString();
        FetchThread fetchThread = new FetchThread();
        fetchThread.setListener(this);
        fetchThread.uString = editText.getText().toString();
        fetchThread.start();

    }

    @Override
    public void fetchingData(final String message) {

//        Log.d("Lecture3", message);

        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textView = findViewById(R.id.textView);
                textView.append(message + "\n");
            }
        });
    }
}
