package com.example.campussfinder_lsg_frame;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.util.Log;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseHelper extends AsyncTask<Void, Void, List<String>> {
    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

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
                // Query to get both single and range dates
                String sql = "SELECT date, start_date, end_date, schedule FROM AcademicSchedule " +
                        "WHERE (date IS NOT NULL AND MONTH(date) = ?) " +
                        "OR (start_date IS NOT NULL AND MONTH(start_date) = ?) " +
                        "ORDER BY COALESCE(date, start_date) ASC";

                preparedStatement = connection.prepareStatement(sql);

                // Use the current month to filter
                int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;  // Get current month (1~12)
                preparedStatement.setInt(1, currentMonth);
                preparedStatement.setInt(2, currentMonth);
                Log.d("DatabaseHelper", "Executing schedule query for month: " + currentMonth);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String date = resultSet.getString("date");
                    String startDate = resultSet.getString("start_date");
                    String endDate = resultSet.getString("end_date");
                    String schedule = resultSet.getString("schedule");

                    if (date != null) {
                        // Single day event
                        results.add(date + " | " + schedule);
                    } else if (startDate != null && endDate != null) {
                        // Range event
                        results.add(startDate + " ~ " + endDate + " | " + schedule);
                    }
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
