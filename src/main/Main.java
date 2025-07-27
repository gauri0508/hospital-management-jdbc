import service.ReportService;

public class Main {
    public static void main(String[] args) {
        ReportService reports = new ReportService();

        reports.getUpcomingAppointmentsPerDepartment();
        reports.getOverdueBillsPatients();
        reports.getMonthlyProcedureCountByType();
        reports.getPendingInsuranceClaims();
        reports.getDoctorUtilizationPerShift();
        reports.getAverageStayByDiagnosis();
    }
}
