# 🏫 **CampusFinder**
<div align="center">
  <img src="img/CampusFinderLogo.png" alt="CampusFinder Logo" width="300">
</div>

---

## **📌 기획 배경**  
대학교는 많은 건물들로 이루어져 있으며, 강의실을 찾기 위해 종이로 만들어진 위치 안내도나 1층에 부착된 도면을 참고해야 하는 번거로움이 있습니다.<br>

그래서 다음과 같은 문제점들이 존재합니다  
- 건물 내부 안내도가 부착되지 않은 경우가 많음  
- 특정 위치에만 도면이 안내되어 있어 다른 출입구로 들어오는 사용자들이 어려움을 겪음  

이로 인해 학생들뿐만 아니라 외부 방문객들도 강의실이나 목적지를 찾는 데 불편함을 느끼는 경우가 많습니다.  

**CampusFinder**는 사용자가 **어느 위치에 있더라도**, 핸드폰 앱을 통해 **강의실을 검색**하고 위치 정보를 얻을 수 있도록 제작되었습니다.

---

## **📂 실행 위치**  
- **`SplashActivity.java`**에서 실행  

---

## **📋 실행 화면**  

<div align="center">

### **스플래쉬 화면**
<img src="img/splashPage.gif" alt="스플래쉬 화면" width="300">

### **학사일정 페이지 & 건물 세부 평면도**
<img src="img/academicCalendar.gif" alt="학사일정 페이지" width="300">
<img src="img/findClassroom.gif" alt="프라임관 강의실 찾기 페이지" width="300">

### **건물 층별 평면도**
<img src="img/primeSelect.gif" alt="프라임관 선택 페이지" width="300">  
<img src="img/prime.gif" alt="프라임관 평면도 페이지" width="300">

### **시내 통학버스 & 시외 통학버스**
<img src="img/bus.gif" alt="시내 통학버스" width="300">  
<img src="img/outbus.gif" alt="시외 통학버스" width="300">

</div>

---

## **📋 변경 사항**  

### **[2024-11-07]**  
- **강현**: 스플래시 페이지, 메인 페이지, 건물 선택 페이지, 탐색 방법 UI 개발  
- **승건**: 평면도 틀, 강의실 검색 기능 개발, 학사일정 UI 개발, 데이터베이스 서버 연결  
- **수민**: 통학버스 선택 화면, 시내버스 시간표, 시외버스 시간표 UI 개발  

### **[2024-11-14]**  
- **강현**: 기능 통합  
- **승건**: 학사일정 데이터 추가, 강의실 정보 데이터 추가  
- **수민**: 메인 페이지 UI 디자인 시안 제작  
- **버전 업**: **고슴도치 → 코알라**  
- **기능 추가**: 통학버스 뒤로가기 버튼  

### **[2024-11-27]**  
- 시내/시외 선택 페이지 UI 변경  
- 시내버스 UI 변경  
- 시외버스 UI 변경  
- 층 선택 페이지 제거  
- 건물 선택 시 해당 Fragment로 이동 설정  
- 공학관, 인문관, 새천년관 각각의 Fragment 페이지 생성  
- 학사일정 UI 변경  

### **[2024-11-28]**  
- **인문관**: 평면도 구현  
- 평면도 **확대/축소 및 이동(상·하·좌·우)** 기능 구현  

### **[2024-11-30]**  
- **인문관**: 교수실 정보 추가  
- **프라임관**: 평면도 구현  
- 평면도 **확대/축소 및 이동(상·하·좌·우)** 기능 구현

### **[2024-12-02]**  
- 검색 기능 스크롤 및 지도보기 버튼 추가  
- RoomDetailActivity.java 추가  
- activity_room_detail.xml 추가 + 로고, 뒤로가기 버튼 추가  
- 세부지도 확대, 축소, 상, 하, 좌, 우 이동 기능 구현  
- SearchActivity.java 입력 버튼 클릭 시 키보드 내리는 기능 구현  
- 공학관 평면도 추가  
- 인문관, 프라임관 세부 평면도 이미지 데이터 삽입  

### **[2024-12-05]**  
- 시내 버스, 시외 버스 페이지 디자인 수정
- 공학관, 프라임관, 인문관 1~5층 버튼 UI 변경
