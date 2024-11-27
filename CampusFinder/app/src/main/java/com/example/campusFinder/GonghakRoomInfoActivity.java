package com.example.campusFinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class GonghakRoomInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info_gonghak);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());

        // 층 변경 버튼
        Button floor1Button = findViewById(R.id.floor1Button);
        Button floor2Button = findViewById(R.id.floor2Button);
        Button floor3Button = findViewById(R.id.floor3Button);
        Button floor4Button = findViewById(R.id.floor4Button);
        Button floor5Button = findViewById(R.id.floor5Button);

        // 초기 프래그먼트 (예: 1층)
        loadFragment(new FragmentGonghakFloor1());

        floor1Button.setOnClickListener(v -> loadFragment(new FragmentGonghakFloor1()));
        floor2Button.setOnClickListener(v -> loadFragment(new FragmentGonghakFloor2()));
        floor3Button.setOnClickListener(v -> loadFragment(new FragmentGonghakFloor3()));
        floor4Button.setOnClickListener(v -> loadFragment(new FragmentGonghakFloor4()));
        floor5Button.setOnClickListener(v -> loadFragment(new FragmentGonghakFloor5()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mapFrame, fragment)
                .commit();
    }
}