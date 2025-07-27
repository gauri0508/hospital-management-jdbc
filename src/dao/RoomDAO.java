package dao;

import db.DBConnection;
import models.Room;

import java.sql.*;

public class RoomDAO {
    public void addRoom(Room room) {
        String sql = "INSERT INTO Room (room_number, department_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setLong(2, room.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
