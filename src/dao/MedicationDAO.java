package dao;

import db.DBConnection;
import models.Medication;
import java.sql.*;

public class MedicationDAO {
    public void addMedication(Medication medication) {
        String sql = "INSERT INTO Medication (name, strength) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medication.getName());
            stmt.setString(2, medication.getStrength());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
