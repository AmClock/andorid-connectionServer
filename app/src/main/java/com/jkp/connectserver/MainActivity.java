package com.jkp.connectserver;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    protected final String TAG = "MainActivity";

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.se_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SecondeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here
                try {
                    // Create URL
                    URL githubEndpoint = new URL("https://api.github.com/");
                    // Create connection
                    HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();

                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    myConnection.setRequestProperty("Contact-Me", "hathibelagal@example.com");

                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        // Further processing here
                        Log.i(TAG, "run: success");
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject(); // Start processing the JSON object

                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            Log.i(TAG, "run to key ==>"+ key);

                            if (key.equals("authorizations_url")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                // Do something with the value
                                // ...

                                //break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }

                        }

                        //reader close
                        jsonReader.close();

                        //myConnection.disconnect();

                    } else {
                        // Error handling code goes here
                        Log.i(TAG, "run: error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }//try~catch end
            }});//AsyncTask end

    }//onCreate end

}//MainActivity end
