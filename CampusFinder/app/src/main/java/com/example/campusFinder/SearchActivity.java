package com.example.campusFinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private TableLayout roomInfoTable;
    private EditText searchField;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // View 연결
        roomInfoTable = findViewById(R.id.roomInfoTable);
        searchField = findViewById(R.id.searchField);
        searchButton = findViewById(R.id.searchButton);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());

        // 버튼 클릭 이벤트 설정
        searchButton.setOnClickListener(view -> onSearchClicked());
    }

    // 검색 버튼 클릭 시 호출되는 메서드
    public void onSearchClicked() {
        String query = searchField.getText().toString().trim();

        // 검색어가 비어 있는지 확인
        if (query.isEmpty()) {
            Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrofit API 호출
        ApiService apiService = RetrofitInstance.getApiService();
        Call<List<RoomInfo>> call = apiService.getRoomInfo(query);

        call.enqueue(new Callback<List<RoomInfo>>() {
            @Override
            public void onResponse(Call<List<RoomInfo>> call, Response<List<RoomInfo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RoomInfo> rooms = response.body();
                    roomInfoTable.removeViews(1, roomInfoTable.getChildCount() - 1); // 이전 검색 결과 제거

                    if (rooms.isEmpty()) {
                        Toast.makeText(SearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // 검색 결과 추가
                    for (RoomInfo room : rooms) {
                        TableRow row = new TableRow(SearchActivity.this);

                        // 건물명 열
                        TextView buildingText = createCell(room.getBuilding_name());
                        // 장소 열
                        TextView roomText = createCell(room.getRoom_name());
                        // 층 열
                        TextView floorText = createCell(room.getRoom_floor());

                        // '지도 보기' 버튼 추가
                        Button mapButton = new Button(SearchActivity.this);
                        mapButton.setText("지도 보기");
                        mapButton.setOnClickListener(view -> {
                            Intent intent = new Intent(SearchActivity.this, RoomDetailActivity.class);
                            intent.putExtra("imagePath", room.getImage_path());
                            intent.putExtra("buildingName",room.getBuilding_name());
                            intent.putExtra("roomFloor",room.getRoom_floor());
                            intent.putExtra("roomName", room.getRoom_name());
                            startActivity(intent);
                        });

                        // 행에 열 및 버튼 추가
                        row.addView(buildingText);
                        row.addView(roomText);
                        row.addView(floorText);
                        row.addView(mapButton); // 지도 보기 버튼 추가

                        roomInfoTable.addView(row);
                    }

                } else {
                    Toast.makeText(SearchActivity.this, "검색에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RoomInfo>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "네트워크 오류: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private TextView createCell(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.WHITE);
        textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)); // 고정된 열 넓이 설정
        return textView;
    }
}
