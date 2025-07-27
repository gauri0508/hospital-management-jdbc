package dao;

import db.DBConnection;
import models.InsurancePolicy;
import java.sql.*;

public class InsurancePolicyDAO {
    public void addInsurancePolicy(InsurancePolicy policy) {
        String sql = "INSERT INTO InsurancePolicy (patient_id, provider_name, policy_number) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, policy.getPatientId());
            stmt.setString(2, policy.getProviderName());
            stmt.setString(3, policy.getPolicyNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
