package com.example.campusFinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OutBus extends AppCompatActivity {

    private ImageButton btnBack;  // 뒤로가기 버튼 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_bus);

        // 뒤로가기 버튼 설정
        btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티 종료하고 이전 화면으로 돌아가기
            }
        });
    }
}
