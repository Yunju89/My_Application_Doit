package com.example.myapplicationjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivityHandlerDelayedBinding;

public class HandlerDelayedActivity extends AppCompatActivity {
    private ActivityHandlerDelayedBinding binding;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_handler_delayed);

        binding.buttonRequest.setOnClickListener(view -> {
            request();
        });

    }

    private void request() {
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
        AlertDialog alertDialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        alertDialog.show();
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo) {
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                binding.textResult.setText("5초 후 결과 표시");                   // 버튼 클릭 시 텍스트 세팅

                handler.postDelayed(new Runnable() {                            // 핸들러 사용 지연시키기 (5초)
                    @Override
                    public void run() {
                        binding.textResult.setText("요청완료");                 // 5초 후 요청완료 텍스트 셋팅
                    }
                }, 5000);
            }
        });
        requestDialog.setNegativeButton(titleButtonNo, null);
        return requestDialog.create();
    }


}