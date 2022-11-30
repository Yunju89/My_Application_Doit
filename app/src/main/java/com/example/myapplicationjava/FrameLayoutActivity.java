package com.example.myapplicationjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.myapplicationjava.databinding.ActivityFrameLayoutBinding;

public class FrameLayoutActivity extends AppCompatActivity {

    ActivityFrameLayoutBinding binding;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_frame_layout);

        onButtonClicked();
    }

    public void onButtonClicked(){
        binding.button.setOnClickListener(new View.OnClickListener() {  //new OnclickListener 생성
            @Override
            public void onClick(View view) {
                changeImage();
            }
        });
    }

    private void changeImage(){
        if(imageIndex == 0){
            binding.image1.setVisibility(View.VISIBLE);
            binding.image2.setVisibility(View.INVISIBLE);
            binding.button.setText("2번 그림 보기");
            imageIndex = 1;
        }else if(imageIndex == 1){
            binding.image1.setVisibility(View.INVISIBLE);
            binding.image2.setVisibility(View.VISIBLE);
            binding.button.setText("그림지우기");
            imageIndex = 2;
        }else{
            binding.image1.setVisibility(View.INVISIBLE);
            binding.image2.setVisibility(View.INVISIBLE);
            binding.button.setText("1번그림 보기");
            imageIndex = 0;
        }
    }

}