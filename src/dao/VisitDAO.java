package dao;

import db.DBConnection;
import models.Visit;
import java.sql.*;

public class VisitDAO {
    public void addVisit(Visit visit) {
        String sql = "INSERT INTO Visit (patient_id, appointment_id, department_id, room_id, start_datetime, end_datetime) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, visit.getPatientId());
            stmt.setLong(2, visit.getAppointmentId());
            stmt.setLong(3, visit.getDepartmentId());
            stmt.setLong(4, visit.getRoomId());
            stmt.setString(5, visit.getStartDatetime());
            stmt.setString(6, visit.getEndDatetime());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
