package com.example.campusfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnClassroom;
    private ImageButton btnBus;
    private ImageButton btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnClassroom = findViewById(R.id.btnClassroom);
        btnBus = findViewById(R.id.btnBus);
        btnCalendar = findViewById(R.id.btnCalendar);
    }

    private void setupClickListeners() {
        btnClassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuildingSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}