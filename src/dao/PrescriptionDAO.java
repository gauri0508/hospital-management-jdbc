package dao;

import db.DBConnection;
import models.Prescription;
import java.sql.*;

public class PrescriptionDAO {
    public void addPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescription (visit_id, medication_id, start_date, end_date, dosage) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prescription.getVisitId());
            stmt.setLong(2, prescription.getMedicationId());
            stmt.setDate(3, Date.valueOf(prescription.getStartDate()));
            stmt.setDate(4, Date.valueOf(prescription.getEndDate()));
            stmt.setString(5, prescription.getDosage());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
