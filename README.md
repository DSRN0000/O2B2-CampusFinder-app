**2024-11-10 2차수정** <br/><br/>
**학사일정에서 일정이 여러 날짜에 걸쳐있 을 시 출력할 수 없는 에러 수정**<br/>
**학사일정 모든 데이터 삽입 완료**<br/>
1. AcademicSchedule, AcademicSAcheduleActivity,DatabaseHelper.java 수정<br/>
2. server.js 수정<br/>
3. AcademicSchdule 테이블 속성 수정{ start_date(DATE) , end_date(DATE), schedule(VARCHAR 255) }<br/><br/>
4. .env 추가

**2024-11-06 1차수정** <br/><br/>
1.뒤로가기 버튼 무응답 오류 수정.<br/>
2.nodejs, retrofit2(RESTAPI) 이용한 데이터베이스 get/set 연결 작업 완료 <br/>
3.강의실 검색, 학사일정 결과 테이블(표) 디자인 1차 수정 <br/>
4.학사일정 이전월,다음월 무응답 오류 수정<br/>
5.학사일정 모든 데이터가 처음 화면에 한번에 출력되는 오류 수정<br/>
**로고 디자인 (크기 및 위치), 표 디자인, 뒤로가기 버튼 디자인(크기를 축소할 경우 이미지가 짤리는 현상) 수정 및 보완 필요**<br/>

<br/><br/><br/>



**History**<br/>
**O2B2projectmap** - 강의실 검색 기능 프로토타입<br/>
검색정보: 강의실 번호, 층, 위치 (수정가능) <- 실제 강의실 데이터를 어떻게 표현할지 고민해봐야함.
