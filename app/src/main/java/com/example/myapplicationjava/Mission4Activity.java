package com.example.myapplicationjava;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivityMission4Binding;

public class Mission4Activity extends AppCompatActivity {

    private ActivityMission4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mission4);

        onClicked();
        textChanged();

    }

    public void onClicked() {

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String inputMessage = binding.edtMessage.getText().toString();
                Toast.makeText(Mission4Activity.this, inputMessage, Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void textChanged() {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                byte[] bytes = null;
                try {
                    bytes = charSequence.toString().getBytes("KSC5601");    // 한글->byte 변환
                    int strCount = bytes.length;
                    binding.txtCount.setText(strCount + "/ 80바이트");
                } catch (Exception e) {
                    Log.d("txtChanged", "Exception : " + e);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edit = editable.toString();
                try {
                    byte[] editBytes = edit.getBytes("KSC5601");    // byte 변환 (한글 2바이트)
                    if (editBytes.length > 80) {
                        editable.delete(edit.length() - 2, edit.length() - 1);  //길이 잘라주기   //delete(int st, int en)
                    }
                } catch (Exception e) {
                    Log.d("txtChanged", "Exception : " + e);
                }
            }
        };

        binding.edtMessage.addTextChangedListener(textWatcher);
    }


}