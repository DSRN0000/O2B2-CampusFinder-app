package com.example.campusFinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RoomDetailActivity extends AppCompatActivity {

    private ImageView roomImageView;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    private float scaleFactor = 1.0f;
    private float dx = 0, dy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        // XML에서 뷰 연결
        ImageButton backButton = findViewById(R.id.backButton);
        roomImageView = findViewById(R.id.roomImageView);
        TextView roomNameTextView = findViewById(R.id.roomNameTextView);

        // Intent로 전달된 데이터 가져오기
        String imagePath = getIntent().getStringExtra("imagePath");
        String roomName = getIntent().getStringExtra("roomName");

        // Room Name 표시
        roomNameTextView.setText(roomName);

        // Glide를 사용해 이미지 로드
        Glide.with(this)
                .load(imagePath) // 서버에서 반환된 이미지 경로
//              .placeholder(R.drawable.placeholder) // 로딩 중 표시할 이미지 (필요 시 주석 해제)
//                .error(R.drawable.error_image) // 로드 실패 시 표시할 이미지
                .into(roomImageView);

        // 뒤로 가기 버튼 클릭 시 SearchActivity로 이동
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(RoomDetailActivity.this, SearchActivity.class);
            startActivity(intent);
            finish(); // 현재 액티비티 종료
        });

        // 확대/축소 및 이동 이벤트 리스너 초기화
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        gestureDetector = new GestureDetector(this, new GestureListener());

        // 터치 이벤트를 scaleGestureDetector와 gestureDetector에 전달
        roomImageView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });
    }

    // ScaleGestureDetector의 리스너 (확대/축소)
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)); // 최소 0.1배, 최대 5배

            roomImageView.setScaleX(scaleFactor);
            roomImageView.setScaleY(scaleFactor);
            return true;
        }
    }

    // GestureDetector의 리스너 (상/하/좌/우 이동)
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            dx -= distanceX;
            dy -= distanceY;

            // 이동된 거리만큼 이미지 이동
            roomImageView.setTranslationX(dx);
            roomImageView.setTranslationY(dy);
            return true;
        }
    }
}
