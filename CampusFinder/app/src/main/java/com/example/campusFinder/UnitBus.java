package com.example.campusFinder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UnitBus extends AppCompatActivity {

    private Button moveButton;
    private Button moveButton2;
    private ImageButton btnBack;  // 뒤로가기 버튼 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_bus); // UnitBus의 레이아웃 설정

        // 시내버스 버튼 설정
        moveButton = findViewById(R.id.moveButton);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitBus.this, IksanBus.class);
                startActivity(intent);
            }
        });

        // 시외버스 버튼 설정
        moveButton2 = findViewById(R.id.moveButton2);
        moveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnitBus.this, OutBus.class);
                startActivity(intent);
            }
        });

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