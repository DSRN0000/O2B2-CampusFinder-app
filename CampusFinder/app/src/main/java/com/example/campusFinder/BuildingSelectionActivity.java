package com.example.campusFinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BuildingSelectionActivity extends AppCompatActivity {
    private ImageButton btnGonghak;
    private ImageButton btnPrime;
    private ImageButton btnSe;
    private ImageButton btnInmoon;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_selection);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnGonghak = findViewById(R.id.btnGonghak);
        btnPrime = findViewById(R.id.btnPrime);
        btnSe = findViewById(R.id.btnSe);
        btnInmoon = findViewById(R.id.btnInmoon);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupClickListeners() {
        // 뒤로가기 버튼 클릭 리스너
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티 종료하고 메인으로 돌아가기
            }
        });

        // 건물 선택 버튼 클릭 리스너
        View.OnClickListener buildingClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingSelectionActivity.this, SelectFloorActivity.class);
                String buildingName = "";

                if (v.getId() == R.id.btnGonghak) {
                    buildingName = "공학관";
                } else if (v.getId() == R.id.btnPrime) {
                    buildingName = "프라임관";
                } else if (v.getId() == R.id.btnSe) {
                    buildingName = "새천년관";
                } else if (v.getId() == R.id.btnInmoon) {
                    buildingName = "인문관";
                }

                intent.putExtra("building_name", buildingName);
                startActivity(intent);
            }
        };

        btnGonghak.setOnClickListener(buildingClickListener);
        btnPrime.setOnClickListener(buildingClickListener);
        btnSe.setOnClickListener(buildingClickListener);
        btnInmoon.setOnClickListener(buildingClickListener);
    }
}
