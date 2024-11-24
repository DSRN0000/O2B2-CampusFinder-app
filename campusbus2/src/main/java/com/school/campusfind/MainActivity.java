package com.school.campusfind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.school.campusfind.R;
import com.school.campusfind.unitbus;

public class MainActivity extends AppCompatActivity {

    private Button moveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // MainActivity의 레이아웃 설정

        // 버튼 설정
        moveButton = findViewById(R.id.moveButton);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, unitbus.class);
                startActivity(intent);
            }
        });
    }
}