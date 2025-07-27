package dao;

import db.DBConnection;
import models.StaffSchedule;
import java.sql.*;

public class StaffScheduleDAO {
    public void addSchedule(StaffSchedule schedule) {
        String sql = "INSERT INTO Staff_Schedule (staff_id, shift_date, shift_start_time, shift_end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, schedule.getStaffId());
            stmt.setDate(2, Date.valueOf(schedule.getShiftDate()));
            stmt.setTime(3, Time.valueOf(schedule.getStartTime()));
            stmt.setTime(4, Time.valueOf(schedule.getEndTime()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
