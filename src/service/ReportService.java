package service;

import db.DBConnection;
import java.sql.*;

public class ReportService {

    // 1. Upcoming appointments per department
    public void getUpcomingAppointmentsPerDepartment() {
        String sql = """
            SELECT d.name AS department, COUNT(*) AS upcoming_appointments
            FROM Appointment a
            JOIN Department d ON a.department_id = d.id
            WHERE a.appointment_datetime > NOW()
            GROUP BY d.name
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nUpcoming Appointments per Department:");
            while (rs.next()) {
                System.out.println(rs.getString("department") + ": " + rs.getInt("upcoming_appointments"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Patients with overdue bills older than 60 days
    public void getOverdueBillsPatients() {
        String sql = """
            SELECT DISTINCT p.name, b.due_date, b.amount
            FROM BillingItem b
            JOIN Visit v ON b.visit_id = v.id
            JOIN Patient p ON v.patient_id = p.id
            WHERE b.due_date < CURDATE() - INTERVAL 60 DAY
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nPatients with Bills Overdue > 60 Days:");
            while (rs.next()) {
                System.out.printf("%s | Due: %s | ₹%.2f\n",
                    rs.getString("name"), rs.getDate("due_date"), rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Monthly procedure count by type
    public void getMonthlyProcedureCountByType() {
        String sql = """
            SELECT DATE_FORMAT(performed_datetime, '%Y-%m') AS month,
                   procedure_type, COUNT(*) AS total
            FROM Procedure
            GROUP BY month, procedure_type
            ORDER BY month DESC
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nMonthly Procedure Count by Type:");
            while (rs.next()) {
                System.out.printf("%s | %s | %d\n",
                    rs.getString("month"), rs.getString("procedure_type"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Insurance claims pending approval
    public void getPendingInsuranceClaims() {
        String sql = """
            SELECT ic.id, p.name, ip.provider_name, ic.claim_date
            FROM InsuranceClaim ic
            JOIN InsurancePolicy ip ON ic.insurance_policy_id = ip.id
            JOIN Patient p ON ip.patient_id = p.id
            WHERE ic.claim_status = 'Pending'
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nPending Insurance Claims:");
            while (rs.next()) {
                System.out.printf("Claim ID: %d | Patient: %s | Provider: %s | Date: %s\n",
                    rs.getInt("id"), rs.getString("name"),
                    rs.getString("provider_name"), rs.getDate("claim_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Doctor utilization rates per shift
    public void getDoctorUtilizationPerShift() {
        String sql = """
            SELECT s.name AS doctor, COUNT(DISTINCT ss.shift_date) AS total_shifts,
                   COUNT(DISTINCT pr.id) AS procedures_performed,
                   ROUND(COUNT(DISTINCT pr.id) / COUNT(DISTINCT ss.shift_date), 2) AS utilization_rate
            FROM Staff s
            JOIN Staff_Schedule ss ON s.id = ss.staff_id
            LEFT JOIN Procedure pr ON pr.performed_by_staff_id = s.id
            WHERE s.role = 'Doctor'
            GROUP BY s.id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nDoctor Utilization Rates per Shift:");
            while (rs.next()) {
                System.out.printf("%s | Shifts: %d | Procedures: %d | Utilization: %.2f\n",
                    rs.getString("doctor"), rs.getInt("total_shifts"),
                    rs.getInt("procedures_performed"), rs.getDouble("utilization_rate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6. Average length of stay by diagnosis
    public void getAverageStayByDiagnosis() {
        String sql = """
            SELECT d.diagnosis_code, d.description,
                   ROUND(AVG(DATEDIFF(a.discharge_date, a.admission_date)), 1) AS avg_stay_days
            FROM Diagnosis d
            JOIN Visit v ON d.visit_id = v.id
            JOIN Admission a ON v.patient_id = a.patient_id
            GROUP BY d.diagnosis_code
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nAverage Length of Stay by Diagnosis:");
            while (rs.next()) {
                System.out.printf("%s | %s | Avg Stay: %.1f days\n",
                    rs.getString("diagnosis_code"), rs.getString("description"), rs.getDouble("avg_stay_days"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
