package dao;

import db.DBConnection;
import models.BillingItem;
import java.sql.*;

public class BillingItemDAO {
    public void addBillingItem(BillingItem billingItem) {
        String sql = "INSERT INTO BillingItem (visit_id, description, amount, billing_date, due_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, billingItem.getVisitId());
            stmt.setString(2, billingItem.getDescription());
            stmt.setDouble(3, billingItem.getAmount());
            stmt.setDate(4, Date.valueOf(billingItem.getBillingDate()));
            stmt.setDate(5, Date.valueOf(billingItem.getDueDate()));

            stmt.executeUpdate();
            System.out.println("Billing item added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
