package com.example.campusFinder;

public class RoomInfo {
    private String building_name;
    private String building_number;
    private String room_name;
    private String room_floor;
    private String image_path;

    // Getter 메서드
    public String getBuilding_name() {
        return building_name;
    }

    public String getBuilding_number() {
        return building_number;
    }

    public String getRoom_name() {
        return room_name;
    }

    public String getRoom_floor() {
        return room_floor;
    }
    public String getImage_path(){
        return image_path;
    }
    public void setImage_path(String imagePath) {

        this.image_path = imagePath;
    }
}