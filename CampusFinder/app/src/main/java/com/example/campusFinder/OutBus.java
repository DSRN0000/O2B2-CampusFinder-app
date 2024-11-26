package com.example.campusFinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button btnOutboundRoutes = findViewById(R.id.btn_outbound_routes);
        btnOutboundRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wku.ac.kr/campus-life/school-bus-lines/out-of-town/jeonju.html"));
                startActivity(intent);
            }
        });

        // 두 번째 버튼: 하교 탑승위치 확인하기
        Button btnPickupLocations = findViewById(R.id.btn_pickup_locations);
        btnPickupLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://image.wku.ac.kr/2021/03/tapseungwichi_dongmunseungkangjang.png"));
                startActivity(intent);
            }
        });
        Button hellobusButton = findViewById(R.id.btn_hellobus);

        hellobusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ciel.app.hellobus&hl=ko"));
                startActivity(browserIntent);
            }
        });
    }
}
