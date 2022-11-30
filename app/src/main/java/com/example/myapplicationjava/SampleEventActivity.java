package com.example.myapplicationjava;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivitySampleEventBinding;

public class SampleEventActivity extends AppCompatActivity {

    ActivitySampleEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_event);

        binding.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {    // 터치 이벤트 받기
                int action = motionEvent.getAction();       //모션이벤트 객체선언

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림 : " + curX + "," + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : " + curX + "," + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 뗌 : " + curX + "," + curY);
                }
                return true;
            }
        });

        GestureDetector detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {  // 제스처디텍터 객체선언
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown 호출 됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress 호출 됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                println("onSingTapUp 호출 됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll 호출 됨");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress 호출 됨");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling 호출 됨");
                return true;
            }
        });

        binding.view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "back버튼 눌림", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void println(String data){
        binding.textView.append(data + "\n");
    }
}