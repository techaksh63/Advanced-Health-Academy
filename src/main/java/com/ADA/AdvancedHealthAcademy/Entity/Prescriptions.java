package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


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
    private String instruction;
    private String disease;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescriptionId",referencedColumnName = "id")
    private List<Medicine> medicine;
    private Date date = new Date();


}
