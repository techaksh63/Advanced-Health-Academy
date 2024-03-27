package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Prescriptions")
@Data
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String doctorName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescriptionId",referencedColumnName = "id")
    private List<Medicine> medicine;
    private String instruction;
    private Date date = new Date();

    public Prescriptions() {
    }
    public Prescriptions(long id, String doctorName, List<Medicine> medicine, String instruction, Date date) {
        this.id = id;
        this.doctorName = doctorName;
        this.medicine = medicine;
        this.instruction = instruction;
        this.date = date;
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

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Date getDate() {
        return date;
    }

}
