package com.school.campusfind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class iksanbus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String url = "https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=489441%2C685903%2C488001%2C678305%2C487363%2C676226%2C488230%2C678770%2C489479%2C685958&rt1=%EC%A0%84%EB%B6%81+%EC%9D%B5%EC%82%B0%EC%8B%9C+%EC%8B%A0%EB%8F%99+272&rt2=%EC%A0%84%EB%B6%81+%EC%9D%B5%EC%82%B0%EC%8B%9C+%EC%A4%91%EC%95%99%EB%8F%991%EA%B0%80+4&rt3=%EC%A0%84%EB%B6%81+%EC%9D%B5%EC%82%B0%EC%8B%9C+%ED%8F%89%ED%99%94%EB%8F%99+236-4&rt4=%EC%A0%84%EB%B6%81+%EC%9D%B5%EC%82%B0%EC%8B%9C+%EC%9D%B5%EC%82%B0%EB%8C%80%EB%A1%9C+168&rt5=%EC%A0%84%EB%B6%81+%EC%9D%B5%EC%82%B0%EC%8B%9C+%EC%8B%A0%EB%8F%99+272&rtIds=%2C%2C%2CN32303323%2C&rtTypes=%2C%2C%2CADDRESS%2C";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }}