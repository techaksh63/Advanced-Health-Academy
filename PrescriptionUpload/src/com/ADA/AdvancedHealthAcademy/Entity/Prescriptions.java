package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Prescriptions")
@Data
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prescriptionId;
    private String doctorName;
    private String doctorMobileNumber;
    private String patientName;
    private String patientMobileNumber;
    private String diagnosis;
    private LocalDate date ;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescriptionId",referencedColumnName = "prescriptionId")
    private List<Medicine> medicine;
    private long profileId;
    public Prescriptions() {
    }

    public Prescriptions(long prescriptionId, String doctorName, String doctorMobileNumber, String patientName, String patientMobileNumber, String diagnosis, LocalDate date, List<Medicine> medicine, long profileId) {
        this.prescriptionId = prescriptionId;
        this.doctorName = doctorName;
        this.doctorMobileNumber = doctorMobileNumber;
        this.patientName = patientName;
        this.patientMobileNumber = patientMobileNumber;
        this.diagnosis = diagnosis;
        this.date = date;
        this.medicine = medicine;
        this.profileId = profileId;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorMobileNumber() {
        return doctorMobileNumber;
    }

    public void setDoctorMobileNumber(String doctorMobileNumber) {
        this.doctorMobileNumber = doctorMobileNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientMobileNumber() {
        return patientMobileNumber;
    }

    public void setPatientMobileNumber(String patientMobileNumber) {
        this.patientMobileNumber = patientMobileNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
