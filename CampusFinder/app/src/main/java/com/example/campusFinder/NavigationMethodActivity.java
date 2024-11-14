package com.example.campusFinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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
                Intent intent = new Intent(NavigationMethodActivity.this, RoomInfoActivity.class);
                startActivity(intent);
            }
        });

        btnSearchPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMethodActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}