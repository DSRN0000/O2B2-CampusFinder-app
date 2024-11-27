package com.example.campusFinder;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;
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

    private void setCurrentMonth() {
        // 현재 날짜를 "XXXX XX월" 형식으로 포맷
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy MM월", Locale.getDefault());
        String currentMonth = monthFormat.format(calendar.getTime());

        // TextView에 설정
        currentMonthText.setText(currentMonth);
    }

    public void onPrevMonthClicked(View view) {
        calendar.add(Calendar.MONTH, -1); // 이전 달로 이동
        setCurrentMonth(); // 텍스트 업데이트
        loadAcademicSchedule(); // 새로운 일정 로드
    }

    public void onNextMonthClicked(View view) {
        calendar.add(Calendar.MONTH, 1); // 다음 달로 이동
        setCurrentMonth(); // 텍스트 업데이트
        loadAcademicSchedule(); // 새로운 일정 로드
    }

    private void loadAcademicSchedule() {
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<AcademicSchedule>> call = apiService.getAcademicSchedule();

        call.enqueue(new Callback<List<AcademicSchedule>>() {
            @Override
            public void onResponse(Call<List<AcademicSchedule>> call, Response<List<AcademicSchedule>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AcademicSchedule> schedules = response.body();
                    academicScheduleTable.removeAllViews(); // 기존 내용 제거

                    boolean hasScheduleForCurrentMonth = false;

                    for (AcademicSchedule schedule : schedules) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                            String startDateStr = schedule.getStartDate();
                            if (startDateStr == null || startDateStr.isEmpty()) {
                                continue;
                            }
                            Date startDate = sdf.parse(startDateStr);

                            String formattedStartDate = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(startDate);
                            String displayDate;

                            String endDateStr = schedule.getEndDate();
                            if (endDateStr != null && !endDateStr.isEmpty()) {
                                Date endDate = sdf.parse(endDateStr);
                                String formattedEndDate = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(endDate);
                                displayDate = formattedStartDate + " - " + formattedEndDate;
                            } else {
                                displayDate = formattedStartDate;
                            }

                            Calendar scheduleCalendar = Calendar.getInstance();
                            scheduleCalendar.setTime(startDate);
                            if (scheduleCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                                hasScheduleForCurrentMonth = true;
                                addScheduleRow(displayDate, schedule.getSchedule());
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    // 현재 월에 대한 일정이 없는 경우
                    if (!hasScheduleForCurrentMonth) {
                        addNoScheduleRow();
                    }
                } else {
                    Toast.makeText(AcademicScheduleActivity.this, "학사일정을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AcademicSchedule>> call, Throwable t) {
                Toast.makeText(AcademicScheduleActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 일정이 없는 경우 표시할 행 추가
    private void addNoScheduleRow() {
        TableRow row = new TableRow(this);
        row.setPadding(0, 20, 0, 20);

        TextView noScheduleTextView = new TextView(this);
        noScheduleTextView.setText("일정이 없습니다");
        noScheduleTextView.setTextColor(Color.parseColor("#555555"));
        noScheduleTextView.setTextSize(16);
        noScheduleTextView.setGravity(Gravity.CENTER);
        noScheduleTextView.setTypeface(null, Typeface.ITALIC); // 기울임체로 스타일링
        noScheduleTextView.setPadding(16, 16, 16, 16);

        // 행의 크기 조정
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        params.span = 2; // 테이블의 두 열을 모두 차지하도록 설정
        noScheduleTextView.setLayoutParams(params);

        row.addView(noScheduleTextView);
        academicScheduleTable.addView(row);
    }

    private void addScheduleRow(String date, String content) {
        // 테이블 행 생성
        TableRow row = new TableRow(this);
        row.setPadding(0, 10, 0, 10);

        // 날짜
        TextView dateTextView = new TextView(this);
        dateTextView.setText(date);
        dateTextView.setTextColor(Color.parseColor("#333333"));
        dateTextView.setTypeface(null, Typeface.BOLD);
        dateTextView.setTextSize(16);
        dateTextView.setGravity(Gravity.START);
        dateTextView.setPadding(32, 16, 16, 16);
        row.addView(dateTextView);

        // 일정 내용
        TextView contentTextView = new TextView(this);
        contentTextView.setText(content);
        contentTextView.setTextColor(Color.parseColor("#555555"));
        contentTextView.setTextSize(14);
        contentTextView.setGravity(Gravity.START);
        contentTextView.setPadding(32, 16, 16, 16);
        row.addView(contentTextView);

        // Row 스타일
        row.setBackgroundColor(Color.parseColor("#F9F9F9"));

        // Row 추가
        academicScheduleTable.addView(row);

        // 구분선 추가
        View divider = new View(this);
        divider.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                2
        ));
        divider.setBackgroundColor(Color.parseColor("#DDDDDD"));
        academicScheduleTable.addView(divider);
    }
}