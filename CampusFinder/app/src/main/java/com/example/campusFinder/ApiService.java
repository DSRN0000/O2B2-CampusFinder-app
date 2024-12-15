package com.example.campusFinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // 학사일정 데이터 가져오기
    @GET("/academic-schedule")
    Call<List<AcademicSchedule>> getAcademicSchedule();

    // 강의실 정보 검색
    @GET("/room-info")
    Call<List<RoomInfo>> getRoomInfo(@Query("search") String searchQuery);
    
}
