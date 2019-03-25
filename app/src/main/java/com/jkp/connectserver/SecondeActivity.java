package com.jkp.connectserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SecondeActivity extends AppCompatActivity {

    private String TAG = "SecondeActivity";
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button)findViewById(R.id.btn_sec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondeActivity.this , TestActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL httpbinEndpoint = new URL("https://httpbin.org/post");
                    HttpsURLConnection myConnection = (HttpsURLConnection) httpbinEndpoint.openConnection();
                    myConnection.setRequestMethod("POST");
                    // Create the data
                    String myData = "message=Hello";
                    // Enable writing
                    myConnection.setDoOutput(true);
                    // Write the data
                    myConnection.getOutputStream().write(myData.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }});//AsyncTask end

    }//onCreate end

}//SecondeActivity end

