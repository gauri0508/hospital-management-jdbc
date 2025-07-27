package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationRoomDAO {
    public void addOperationRoom(String roomNumber, int capacity) {
        String sql = "INSERT INTO OperationRoom (room_number, capacity) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomNumber);
            stmt.setInt(2, capacity);

            stmt.executeUpdate();
            System.out.println("Operation Room added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
