package com.user.UserManagement.DTO.PrescriptionDTO;

import com.user.UserManagement.DTO.MedicineDTO.MedicineInfoDTO;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionInfoDTO {
    private String doctorName;
    private String doctorMobileNumber;
    private String patientName;
    private String patientMobileNumber;
    private String diagnosis;
    private LocalDate date ;
    private List<MedicineInfoDTO> medicine;

    public PrescriptionInfoDTO() {
    }

    public PrescriptionInfoDTO(String doctorName, String doctorMobileNumber, String patientName, String patientMobileNumber, String diagnosis, LocalDate date, List<MedicineInfoDTO> medicine) {
        this.doctorName = doctorName;
        this.doctorMobileNumber = doctorMobileNumber;
        this.patientName = patientName;
        this.patientMobileNumber = patientMobileNumber;
        this.diagnosis = diagnosis;
        this.date = date;
        this.medicine = medicine;
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

    public List<MedicineInfoDTO> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<MedicineInfoDTO> medicine) {
        this.medicine = medicine;
    }
}
