package com.example.campussfinder_lsg_frame;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class DatabaseHelper extends AsyncTask<Void, Void, List<String>> {
    private static final String DB_URL = "jdbc:mysql://o2b2-campusfinder.cruwys2ouxur.ap-northeast-2.rds.amazonaws.com:3306/campusfinder";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "1234567890";

    private String queryType;
    private String searchQuery;

    public DatabaseHelper(String queryType, String searchQuery) {
        this.queryType = queryType;
        this.searchQuery = searchQuery;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        List<String> results = new ArrayList<>();
        Log.d("DatabaseHelper", "Attempting to connect to DB");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Log.d("DatabaseHelper", "DB connection successful");

            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            if ("schedule".equals(queryType)) {
                String sql = "SELECT DATE(date) AS date, schedule FROM AcademicSchedule";
                preparedStatement = connection.prepareStatement(sql);
                Log.d("DatabaseHelper", "Executing schedule query");

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    String schedule = resultSet.getString("schedule");
                    results.add(date + " | " + schedule);
                }

            } else if ("room".equals(queryType) && searchQuery != null) {
                String sql = "SELECT building_name, building_number, room_name, room_floor FROM RoomInfo " +
                        "WHERE building_name LIKE ? OR room_name LIKE ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + searchQuery + "%");
                preparedStatement.setString(2, "%" + searchQuery + "%");
                Log.d("DatabaseHelper", "Executing room query with searchQuery: " + searchQuery);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String buildingName = resultSet.getString("building_name");
                    String buildingNumber = resultSet.getString("building_number");
                    String roomName = resultSet.getString("room_name");
                    String roomFloor = resultSet.getString("room_floor");
                    results.add(buildingName + " | " + buildingNumber + " | " + roomName + " | " + roomFloor);
                }
            }

            if (resultSet != null) {
                resultSet.close();
                Log.d("DatabaseHelper", "ResultSet closed successfully");
            }
            if (preparedStatement != null) {
                preparedStatement.close();
                Log.d("DatabaseHelper", "PreparedStatement closed successfully");
            }

            Log.d("DatabaseHelper", "Query executed successfully. Results: " + results);

        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error connecting to DB", e);
        }
        return results;
    }
}
