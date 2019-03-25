package com.jkp.connectserver;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TestActivity extends AppCompatActivity {

    private String TAG ="TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: start server connection");
                try {
                    URL url = new URL("http://172.30.9.36:9090/");
                    HttpsURLConnection connection =  (HttpsURLConnection) url.openConnection();
                    Log.i(TAG, "run: success 1");



                   // connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });//


    }
}
