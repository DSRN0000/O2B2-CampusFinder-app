package com.example.campusFinder;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class RoomDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        ImageView roomImageView = findViewById(R.id.roomImageView);
        TextView roomNameTextView = findViewById(R.id.roomNameTextView);

        // Intent로 전달된 데이터 가져오기
        String imagePath = getIntent().getStringExtra("imagePath");
        String roomName = getIntent().getStringExtra("roomName");

        // Room Name 표시
        roomNameTextView.setText(roomName);

        // Glide를 사용해 이미지 로드
        Glide.with(this)
                .load(imagePath) // 서버에서 반환된 이미지 경로
//                .placeholder(R.drawable.placeholder) // 로딩 중 표시할 이미지
//                .error(R.drawable.error_image) // 로드 실패 시 표시할 이미지
                .into(roomImageView);
    }
}
