package dao;

import db.DBConnection;
import models.Appointment;
import java.sql.*;

public class AppointmentDAO {
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (patient_id, department_id, room_id, appointment_datetime, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, appointment.getPatientId());
            stmt.setLong(2, appointment.getDepartmentId());
            stmt.setLong(3, appointment.getRoomId());
            stmt.setString(4, appointment.getAppointmentDatetime());
            stmt.setString(5, appointment.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
