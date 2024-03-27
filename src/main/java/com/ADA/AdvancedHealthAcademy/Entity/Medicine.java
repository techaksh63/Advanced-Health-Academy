package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Medicine")
@Data
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long medicineId;
    private String medicineName;
    private String medicinePower;
    @Column(name = "Frequency (MN-AF-EN-NT)")
    private String frequency;
    @Column(name = "Consume (Af or Bf)")
    private String dosage;
    private String duration;
    private String Quantity;

    public Medicine() {
    }

    public Medicine(String medicineName, String medicinePower, String frequency, String dosage, String duration, String quantity) {
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.frequency = frequency;
        this.dosage = dosage;
        this.duration = duration;
        Quantity = quantity;
    }
    public Medicine(long medicineId, String medicineName, String medicinePower, String frequency, String dosage, String duration, String quantity) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.frequency = frequency;
        this.dosage = dosage;
        this.duration = duration;
        Quantity = quantity;
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
}
