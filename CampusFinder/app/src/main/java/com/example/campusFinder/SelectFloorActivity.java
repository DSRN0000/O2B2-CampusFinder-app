package com.example.campusFinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SelectFloorActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnFloor1, btnFloor2, btnFloor3, btnFloor4, btnFloor5;
    private String buildingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_floor);

        // Get the building name passed from the previous activity
        buildingName = getIntent().getStringExtra("building_name");

        // Initialize views and set up click listeners
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnFloor1 = findViewById(R.id.btnFloor1);
        btnFloor2 = findViewById(R.id.btnFloor2);
        btnFloor3 = findViewById(R.id.btnFloor3);
        btnFloor4 = findViewById(R.id.btnFloor4);
        btnFloor5 = findViewById(R.id.btnFloor5);
    }

    private void setupClickListeners() {
        // 뒤로가기 버튼 클릭 리스너
        btnBack.setOnClickListener(v -> finish());

        // 층 버튼 클릭 리스너 설정
        setupFloorButton(btnFloor1, "1층");
        setupFloorButton(btnFloor2, "2층");
        setupFloorButton(btnFloor3, "3층");
        setupFloorButton(btnFloor4, "4층");
        setupFloorButton(btnFloor5, "5층");
    }

    private void setupFloorButton(Button button, String floor) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(SelectFloorActivity.this, RoomInfoActivity.class);
            intent.putExtra("building_name", buildingName); // 건물 이름 전달
            intent.putExtra("floor", floor);               // 선택된 층 정보 전달
            startActivity(intent);
        });
    }
}