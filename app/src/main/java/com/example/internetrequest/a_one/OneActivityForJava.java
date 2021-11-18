package com.example.internetrequest.a_one;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internetrequest.ConstantsKt;
import com.example.internetrequest.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Creator: ED
 * Date: 2021/11/17 16:37
 * Mail: salahayo3192@gmail.com
 **/
class OneActivityForJava extends AppCompatActivity {


    Button btnFetchData;
    TextView tvResult;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFetchData = findViewById(R.id.btnFetchData);
        tvResult = findViewById(R.id.tvResult);


        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRemoteData();
            }
        });
    }


    private void fetchRemoteData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getData(ConstantsKt.URL);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(data);
                    }
                });
            }
        }).start();
    }


    private String getData(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.connect();


            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }
}

