package dao;

import db.DBConnection;
import models.Staff;

import java.sql.*;

public class StaffDAO {
    public void addStaff(Staff staff) {
        String sql = "INSERT INTO Staff (name, specialization, department_id, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getSpecialization());
            stmt.setLong(3, staff.getDepartmentId());
            stmt.setString(4, staff.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
