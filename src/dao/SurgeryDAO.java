package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SurgeryDAO {
    public void addSurgery(long admissionId, String surgeryType, String surgeryDatetime, long performedByStaffId) {
        String sql = "INSERT INTO Surgery (admission_id, surgery_type, surgery_datetime, performed_by_staff_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, admissionId);
            stmt.setString(2, surgeryType);
            stmt.setString(3, surgeryDatetime);
            stmt.setLong(4, performedByStaffId);

            stmt.executeUpdate();
            System.out.println("Surgery record added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
