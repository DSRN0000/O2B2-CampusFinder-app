package com.example.campusFinder;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FragmentGonghakFloor4 extends Fragment {

    private ImageView inmoonFloorImageView;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    private float scaleFactor = 1.0f;
    private float dx = 0, dy = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gonghak_floor_4, container, false);

        inmoonFloorImageView = view.findViewById(R.id.gongHak_floor4);

        // ScaleGestureDetector 초기화
        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

        // GestureDetector로 터치 이벤트를 처리하여 이미지 이동 구현
        gestureDetector = new GestureDetector(getContext(), new GestureListener());

        // 터치 이벤트를 scaleGestureDetector와 gestureDetector에 전달
        inmoonFloorImageView.setOnTouchListener((v, event) -> {
            scaleGestureDetector.onTouchEvent(event);
            gestureDetector.onTouchEvent(event);
            return true;
        });

        return view;
    }

    // ScaleGestureDetector의 리스너 (확대/축소)
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)); // 최소 0.1배, 최대 5배

            inmoonFloorImageView.setScaleX(scaleFactor);
            inmoonFloorImageView.setScaleY(scaleFactor);
            return true;
        }
    }

    // GestureDetector의 리스너 (상하좌우 이동)
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            dx -= distanceX;
            dy -= distanceY;

            // 이동된 거리만큼 이미지 이동
            inmoonFloorImageView.setTranslationX(dx);
            inmoonFloorImageView.setTranslationY(dy);
            return true;
        }
    }
}