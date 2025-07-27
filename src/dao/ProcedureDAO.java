package dao;

import db.DBConnection;
import models.Procedure;
import java.sql.*;

public class ProcedureDAO {
    public void addProcedure(Procedure procedure) {
        String sql = "INSERT INTO Procedure (visit_id, procedure_type, performed_datetime, performed_by_staff_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, procedure.getVisitId());
            stmt.setString(2, procedure.getProcedureType());
            stmt.setString(3, procedure.getPerformedDatetime());
            stmt.setLong(4, procedure.getStaffId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
