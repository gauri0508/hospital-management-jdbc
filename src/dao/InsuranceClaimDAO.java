package dao;

import db.DBConnection;
import models.InsuranceClaim;
import java.sql.*;

public class InsuranceClaimDAO {
    public void addClaim(InsuranceClaim claim) {
        String sql = "INSERT INTO InsuranceClaim (billingItem_id, insurance_policy_id, claim_date, claim_status, approved_amount) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, claim.getBillingItemId());
            stmt.setLong(2, claim.getInsurancePolicyId());
            stmt.setDate(3, Date.valueOf(claim.getClaimDate()));
            stmt.setString(4, claim.getClaimStatus());
            stmt.setDouble(5, claim.getApprovedAmount());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
