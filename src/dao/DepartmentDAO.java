package dao;

import db.DBConnection;
import models.Department;

import java.sql.*;

public class DepartmentDAO {
    public void addDepartment(Department department) {
        String sql = "INSERT INTO Department (name, location) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, department.getName());
            stmt.setString(2, department.getLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
