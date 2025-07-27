package dao;

import db.DBConnection;
import models.Diagnosis;
import java.sql.*;

public class DiagnosisDAO {
    public void addDiagnosis(Diagnosis diagnosis) {
        String sql = "INSERT INTO Diagnosis (visit_id, diagnosis_code, description) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, diagnosis.getVisitId());
            stmt.setString(2, diagnosis.getDiagnosisCode());
            stmt.setString(3, diagnosis.getDescription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
