package models;

public class Patient {
    private long id;
    private String name;
    private String dob;
    private String gender;
    private String contactInfo;

    public Patient(String name, String dob, String gender, String contactInfo) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    // Getters
    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getContactInfo() { return contactInfo; }
}
