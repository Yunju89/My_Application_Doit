package com.example.myapplicationjava;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivitySampleHttpBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SampleHttpActivity extends AppCompatActivity {
    private ActivitySampleHttpBinding binding;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_http);

        binding.button.setOnClickListener(v -> {
            final String urlStr = binding.editText.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    request(urlStr);
                }
            }).start();
        });
    }

    public void request(String urlStr) {
        StringBuffer output = new StringBuffer();

        try {
            URL url = new URL(urlStr);  // URL RuntimeException 발생, try-catch 묶기

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();     // HttpURLConnection 객체 생성
            if (connection != null) {
                connection.setConnectTimeout(10000);                // 타임아웃 10초
                connection.setRequestMethod("GET");                 // GET 방식으로
                connection.setDoInput(true);                        // true 넣어서

                int responseCode = connection.getResponseCode();    //
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));     // 입력 스트림을 읽을 Reader 객체 생성
                String line = null;
                while (true) {
                    line = reader.readLine();                   // 스트림에서 한줄 씩 읽어옴 (BufferedReader 정의)
                    if (line == null) {                         // 라인이 null 이면 while 중단
                        break;
                    }
                    output.append(line + "\n");
                }
                reader.close();                                       // 리더 닫고
                connection.disconnect();                              // 커넥션 끊기
            }
        } catch (Exception e) {
            println("예외발생" + e.toString());
        }
        println("응답 : " + output.toString());
    }

    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                binding.textView.setText(data + "\n");
            }
        });
    }

}