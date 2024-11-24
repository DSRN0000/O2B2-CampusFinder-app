package com.school.campusfind;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class unitbus extends AppCompatActivity {

    private Button moveButton;
    private Button moveButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitbus); // UnitBus의 레이아웃 설정

        // 시내버스 버튼 설정
        moveButton = findViewById(R.id.moveButton);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(unitbus.this, iksanbus.class);
                startActivity(intent);
            }
        });

        // 시외버스 버튼 설정
        moveButton2 = findViewById(R.id.moveButton2);
        moveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(unitbus.this, outbus.class);
                startActivity(intent);
            }
        });
    }
}