package com.user.UserManagement.DTO.PrescriptionDTO;

import com.user.UserManagement.DTO.MedicineDTO.MedicineDTO;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionDTO {
    private long id;
    private String doctorName;
    private String doctorMobileNumber;
    private String patientName;
    private String patientMobileNumber;
    private String diagnosis;
    private LocalDate date ;
    private List<MedicineDTO> medicine;
    private long profileId;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(long id, String doctorName, String doctorMobileNumber, String patientName, String patientMobileNumber, String diagnosis, LocalDate date, List<MedicineDTO> medicine, long profileId) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorMobileNumber = doctorMobileNumber;
        this.patientName = patientName;
        this.patientMobileNumber = patientMobileNumber;
        this.diagnosis = diagnosis;
        this.date = date;
        this.medicine = medicine;
        this.profileId = profileId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<MedicineDTO> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<MedicineDTO> medicine) {
        this.medicine = medicine;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }
}
