package com.example.myapplicationjava;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivitySampleThreadBinding;

public class SampleThreadActivity extends AppCompatActivity {
    private ActivitySampleThreadBinding binding;
    MainHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_thread);

        binding.buttonThread.setOnClickListener(view -> {
            BackgroundThread thread = new BackgroundThread();           // 스레드 객체 생성
            thread.start();                                             //시작 (스레드 시작시키면 내부의 run 메소드 실행)
        });

        handler = new MainHandler();                                    // 핸들러 객체 생성
    }

    class BackgroundThread extends Thread {                             // 스레드 상속받는 스레드 클래스
        int value = 0;

        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000); // 1/1000 초, -> 1초
                } catch (Exception e) {
                    e.printStackTrace();
                }
                value += 1;
                Log.d("Thread", "value : " + value);

                Message message = handler.obtainMessage();              // 스레드 간의 통신을 전달하는 메시지 객체
                Bundle bundle = new Bundle();                           // Map 형태 데이터 묶음 , 번들 객체생성
                bundle.putInt("value", value);                          // 번들에 key "value"로 int 값 value 담기
                message.setData(bundle);                                // 메시지 객체에 bundle 값 set 하기

                handler.sendMessage(message);                           // 핸들러에서 메시지 보내기
            }
        }
    }

    class MainHandler extends Handler {                                 // 스레드와 메인스레드를 이어주는 핸들러
        @Override
        public void handleMessage(@NonNull Message msg) {               // 메시지 받는 메소드
            super.handleMessage(msg);

            Bundle bundle = msg.getData();                              // 번들 데이터 꺼내와서
            int value = bundle.getInt("value");
            binding.textThread.setText("value 값 : " + value);              // 값 세팅
        }
    }
}