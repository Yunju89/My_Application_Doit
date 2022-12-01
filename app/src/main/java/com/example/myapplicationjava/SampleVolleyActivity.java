package com.example.myapplicationjava;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplicationjava.databinding.ActivitySampleVolleyBinding;

import java.util.HashMap;
import java.util.Map;

public class SampleVolleyActivity extends AppCompatActivity {
    private ActivitySampleVolleyBinding binding;
    static RequestQueue requestQueue;                           // 요청 큐는 한번 만들어서 계속 사용 (싱글톤), static 변수로 선언 후 할당 (앱 전체에서 사용하는 것이 일반적이라 별도 클래스로)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_volley);

        binding.button.setOnClickListener(v -> {
            makeRequest();
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());         // RequestQueue 객체생성
        }
    }

    public void makeRequest() {
        String url = binding.editText.getText().toString();
//                                                 요청방식(GET, POST, 요청 URL, 응답리스너, 에러리스너)
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {        // StringRequest 객체 생성 (문자열 주고받기 위해 사용되는 객체)
            @Override
            public void onResponse(String response) {
                println("응답 : " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                println("응답 : " + error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {             // POST 방식에서 요청 파라미터 전달할 때 값 넣을 메소드
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청보냄");
    }

    public void println(String data) {
        binding.textView.setText(data);
    }
}