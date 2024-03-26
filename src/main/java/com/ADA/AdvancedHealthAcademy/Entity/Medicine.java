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
    private int dosage;
    private int frequency;

    public Medicine() {
    }
    public Medicine( String medicineName, String medicinePower, int dosage, int frequency) {
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.dosage = dosage;
        this.frequency = frequency;
    }
    public Medicine(long id, String medicineName, String medicinePower, int dosage, int frequency) {
        this.medicineId = id;
        this.medicineName = medicineName;
        this.medicinePower = medicinePower;
        this.dosage = dosage;
        this.frequency = frequency;
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

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
