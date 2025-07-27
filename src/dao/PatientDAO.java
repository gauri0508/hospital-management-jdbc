package dao;

import models.Patient;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (name, dob, gender, contact_info) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getDob());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContactInfo());

            int rows = stmt.executeUpdate();
            System.out.println(rows + " patient added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
