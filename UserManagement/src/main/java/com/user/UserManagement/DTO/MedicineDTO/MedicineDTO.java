package com.user.UserManagement.DTO.MedicineDTO;

import jakarta.persistence.Column;

public class MedicineDTO {
    private long medicineId;
    private String medicineName;
    private String medicinePower;
    private String frequency;
    private String dosage;
    private String duration;
    private String Quantity;
    private long prescriptionId;

    public MedicineDTO() {
    }

    public MedicineDTO(long medicineId, String medicineName, String medicinePower, String frequency, String dosage, String duration, String quantity, long prescriptionId) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.frequency = frequency;
        this.dosage = dosage;
        this.duration = duration;
        Quantity = quantity;
        this.prescriptionId = prescriptionId;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicinePower() {
        return medicinePower;
    }

    public void setMedicinePower(String medicinePower) {
        this.medicinePower = medicinePower;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
}
