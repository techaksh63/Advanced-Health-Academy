package com.ADA.AdvancedHealthAcademy.DTO;

public class MedicineInfoDTO {

    private String medicineName;
    private String medicinePower;
    private String frequency;
    private String dosage;
    private String duration;
    private String quantity;

    public MedicineInfoDTO() {
    }

    public MedicineInfoDTO(String medicineName, String medicinePower, String frequency, String dosage, String duration, String quantity) {
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.frequency = frequency;
        this.dosage = dosage;
        this.duration = duration;
        this.quantity = quantity;
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
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
