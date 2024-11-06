package com.example.campusfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NavigationMethodActivity extends AppCompatActivity {
    private FrameLayout btnPinpoint;
    private FrameLayout btnSearchPath;
    private ImageButton btnBack;
    private String buildingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_method);

        buildingName = getIntent().getStringExtra("building_name");

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnPinpoint = findViewById(R.id.btnPinpoint);
        btnSearchPath = findViewById(R.id.btnSearchPath);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupClickListeners() {
        // 뒤로가기 버튼 클릭 리스너
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티 종료하고 건물 선택으로 돌아가기
            }
        });

        btnPinpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationMethodActivity.this,
                        buildingName + " 평면도 보기 준비 중입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSearchPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationMethodActivity.this,
                        buildingName + " 강의실 검색 준비 중입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
