package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDAO {
    public void addTest(long visitId, String testName, String result, String testDatetime, long performedByStaffId) {
        String sql = "INSERT INTO Test (visit_id, test_name, result, test_datetime, performed_by_staff_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, visitId);
            stmt.setString(2, testName);
            stmt.setString(3, result);
            stmt.setString(4, testDatetime);
            stmt.setLong(5, performedByStaffId);

            stmt.executeUpdate();
            System.out.println("Test record added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
