package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdmissionDAO {
    public void addAdmission(long patientId, long roomId, String admissionDate, String dischargeDate) {
        String sql = "INSERT INTO Admission (patient_id, room_id, admission_date, discharge_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, patientId);
            stmt.setLong(2, roomId);
            stmt.setString(3, admissionDate);
            stmt.setString(4, dischargeDate);

            stmt.executeUpdate();
            System.out.println("Admission record added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
