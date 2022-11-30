package com.example.myapplicationjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.myapplicationjava.databinding.ActivityScrollViewBinding;

public class ScrollViewActivity extends AppCompatActivity {

    ActivityScrollViewBinding binding;
    int check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scroll_view);

        binding.scrollView.setHorizontalScrollBarEnabled(true);

//        주석1
//        Resources res = getResources();
//        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);   //형변환하기
//        int bitmapWidth = bitmap.getIntrinsicWidth();
//        int bitmapHeight = bitmap.getIntrinsicHeight();
//
//        binding.imageView.setImageDrawable(bitmap);
//        binding.imageView.getLayoutParams().width = bitmapWidth;
//        binding.imageView.getLayoutParams().height = bitmapHeight;

//        주석2
//        binding.imageView.setImageDrawable(getDrawable(R.drawable.image01));

    }

    public void onButtonClicked(View view) {
        changeImage();
    }

    private void changeImage(){
//        Resources res = getResources();
//
//        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);   //형변환하기
//        int bitmapWidth = bitmap.getIntrinsicWidth();
//        int bitmapHeight = bitmap.getIntrinsicHeight();
//
//        binding.imageView.setImageDrawable(bitmap);
//        binding.imageView.getLayoutParams().width = bitmapWidth;
//        binding.imageView.getLayoutParams().height = bitmapHeight;

        switch(check){
            case 1:
                binding.imageView.setImageDrawable(getDrawable(R.drawable.image01));
                check = 2;
                break;
            case 2:
                binding.imageView.setImageDrawable(getDrawable(R.drawable.image02));
                check = 1;
                break;
        }

//        binding.imageView.setImageDrawable(getDrawable(R.drawable.image02));
    }
}