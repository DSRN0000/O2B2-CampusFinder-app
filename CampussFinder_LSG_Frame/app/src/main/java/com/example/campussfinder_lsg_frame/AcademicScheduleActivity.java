package com.example.campussfinder_lsg_frame;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcademicScheduleActivity extends AppCompatActivity {

    private TableLayout academicScheduleTable;
    private TextView currentMonthText;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_schedule);

        // View 연결
        academicScheduleTable = findViewById(R.id.academicScheduleTable);
        currentMonthText = findViewById(R.id.currentMonth);
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());

        // 캘린더 객체 초기화
        calendar = Calendar.getInstance();
        setCurrentMonth();
        loadAcademicSchedule();
    }

    // 현재 달을 설정하는 메서드
    private void setCurrentMonth() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM월", Locale.getDefault());
        String currentMonth = monthFormat.format(calendar.getTime());
        currentMonthText.setText(currentMonth);
    }

    // 이전 달 버튼 클릭 메서드
    public void onPrevMonthClicked(View view) {
        calendar.add(Calendar.MONTH, -1);
        setCurrentMonth();
        loadAcademicSchedule();
    }

    // 다음 달 버튼 클릭 메서드
    public void onNextMonthClicked(View view) {
        calendar.add(Calendar.MONTH, 1);
        setCurrentMonth();
        loadAcademicSchedule();
    }

    // 학사일정 데이터를 로드하여 테이블에 추가하는 메서드
    private void loadAcademicSchedule() {
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<AcademicSchedule>> call = apiService.getAcademicSchedule();

        call.enqueue(new Callback<List<AcademicSchedule>>() {
            @Override
            public void onResponse(Call<List<AcademicSchedule>> call, Response<List<AcademicSchedule>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AcademicSchedule> schedules = response.body();
                    academicScheduleTable.removeAllViews(); // 이전 데이터 제거

                    // 테이블 헤더 추가
                    TableRow headerRow = new TableRow(AcademicScheduleActivity.this);
                    headerRow.addView(createTextView("요일", true));
                    headerRow.addView(createTextView("학사일정", true));
                    academicScheduleTable.addView(headerRow);

                    // 현재 달과 같은 데이터만 필터링
                    for (AcademicSchedule schedule : schedules) {
                        try {
                            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(schedule.getDate());
                            Calendar scheduleCalendar = Calendar.getInstance();
                            scheduleCalendar.setTime(date);

                            // 현재 달과 동일한 데이터만 표시
                            if (scheduleCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                                String dayOfWeek = new SimpleDateFormat("E", Locale.getDefault()).format(date); // 요일 추출
                                TableRow row = new TableRow(AcademicScheduleActivity.this);

                                // 요일과 학사일정 셀 추가
                                row.addView(createTextView(dayOfWeek, false));
                                row.addView(createTextView(schedule.getSchedule(), false));
                                academicScheduleTable.addView(row);
                            }
                        } catch (ParseException e) {
                            Log.e("AcademicSchedule", "Date parsing error: " + e.getMessage());
                        }
                    }
                } else {
                    Toast.makeText(AcademicScheduleActivity.this, "학사일정을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AcademicSchedule>> call, Throwable t) {
                Toast.makeText(AcademicScheduleActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    // 동적으로 텍스트뷰를 생성하는 메서드 (헤더와 데이터 셀 구분)
    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        textView.setGravity(Gravity.CENTER);

        if (isHeader) {
            textView.setTextSize(16);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setBackgroundColor(Color.LTGRAY);
        } else {
            textView.setTextSize(14);
            textView.setBackgroundColor(Color.WHITE);
        }

        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
        ));

        return textView;
    }
}
