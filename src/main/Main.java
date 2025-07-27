package main;
import dao.PatientDAO;
import models.Patient;
public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient("Gauri Shukla", "2002-11-15", "Female", "gauri@example.com");

        PatientDAO dao = new PatientDAO();
        dao.addPatient(patient);
    }
}
