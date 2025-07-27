package dao;

import db.DBConnection;
import models.Payment;
import java.sql.*;

public class PaymentDAO {
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (billingItem_id, payment_date, amount_paid, payment_method, payer) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getBillingItemId());
            stmt.setDate(2, Date.valueOf(payment.getPaymentDate()));
            stmt.setDouble(3, payment.getAmountPaid());
            stmt.setString(4, payment.getPaymentMethod());
            stmt.setString(5, payment.getPayer());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
