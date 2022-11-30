package com.example.myapplicationjava;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivitySampleThreadBinding;

public class SampleThreadActivity2 extends AppCompatActivity {
    private ActivitySampleThreadBinding binding;
    Handler handler = new Handler();                    // 기본 핸들러객체 생성, 멤버변수로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_thread);

        binding.buttonThread.setOnClickListener(view -> {
            BackgroundThread thread = new BackgroundThread();           // 스레드 객체 생성
            thread.start();                                             //시작 (스레드 시작시키면 내부의 run 메소드 실행)
        });

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

                handler.post(new Runnable() {                           // Runnable 객체를 핸들러의 post 메서드로 전달-> 객체에 정의된 run 메서드 안의 코드 메인스레드에서 실행
                    @Override                                           // runOnUiThread 가능
                    public void run() {
                        binding.textThread.setText("value 값 : " + value);
                    }
                });
            }
        }
    }
}
