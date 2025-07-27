package dao;

import db.DBConnection;
import models.Patient;

import java.sql.*;

public class PatientDAO {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (name, dob, gender, contact_info, primary_insurance_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setDate(2, Date.valueOf(patient.getDob()));
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContactInfo());
            stmt.setLong(5, patient.getPrimaryInsuranceId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
