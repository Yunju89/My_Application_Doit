package com.example.myapplicationjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        onClickView();

    }

    public void onClickView() {
        binding.buttonFrameLayout.setOnClickListener(this);
        binding.buttonScrollView.setOnClickListener(this);
        binding.buttonMission4.setOnClickListener(this);
        binding.buttonSampleDrawable.setOnClickListener(this);
        binding.buttonSampleEvent.setOnClickListener(this);
        binding.buttonSampleThread.setOnClickListener(this);
        binding.buttonSampleThread2.setOnClickListener(this);
        binding.buttonHandlerDelayed.setOnClickListener(this);
        binding.buttonSampleSocket.setOnClickListener(this);
        binding.buttonSampleHttp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {            // -> 바인딩 아이디 바로 넣을 수 없나?
            case R.id.button_frameLayout:
                intent = new Intent(getApplicationContext(), FrameLayoutActivity.class);
                break;

            case R.id.button_ScrollView:
                intent = new Intent(getApplicationContext(), ScrollViewActivity.class);
                break;

            case R.id.button_mission4:
                intent = new Intent(getApplicationContext(), Mission4Activity.class);
                break;

            case R.id.button_sampleDrawable:
                intent = new Intent(getApplicationContext(), SampleDrawableActivity.class);
                break;

            case R.id.button_sampleEvent:
                intent = new Intent(MainActivity.this, SampleEventActivity.class);
                break;

            case R.id.button_sampleOrientation:
                intent = new Intent(this, SampleOrientationActivity.class);
                break;

            case R.id.button_sampleThread:
                intent = new Intent(this, SampleThreadActivity.class);
                break;

            case R.id.button_sampleThread2:
                intent = new Intent(this, SampleThreadActivity2.class);
                break;

            case R.id.button_Handler_Delayed:
                intent = new Intent(this, HandlerDelayedActivity.class);
                break;

            case R.id.button_Sample_Socket:
                intent = new Intent(this, SampleSocketActivity.class);
                break;

            case R.id.button_Sample_Http:
                intent = new Intent(this, SampleHttpActivity.class);
                break;

            default:
                intent = new Intent();
        }
        startActivity(intent);
    }

}