package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "Prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String doctorName;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String,Object> information;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescriptionId",referencedColumnName = "id")
    private List<Medicine> medicine;
    private Date date = new Date();

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

    public Map<String, Object> getInformation() {
        return information;
    }

    public void setInformation(Map<String, Object> information) {
        this.information = information;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
