package com.user.UserManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Profile")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Payment> payment;
    private String fullName;
    private String relationship;
    private String gender;
    private LocalDate birthDate;
    private String bloodGroup;
    private String diabetesStatus;
    private String bloodPressureStatus;
    private String currentDisease;
    private String previousSurgeries;
    private String previouslyCuredDiseases;
    private Double height;
    private Double weight;
    private boolean isActive = false;

    public Profile() {
    }

    public Profile(User user, List<Payment> payment, String fullName, String relationship, String gender, LocalDate birthDate, String bloodGroup, String diabetesStatus, String bloodPressureStatus, String currentDisease, String previousSurgeries, String previouslyCuredDiseases, Double height, Double weight, boolean isActive) {
        this.user = user;
        this.payment = payment;
        this.fullName = fullName;
        this.relationship = relationship;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bloodGroup = bloodGroup;
        this.diabetesStatus = diabetesStatus;
        this.bloodPressureStatus = bloodPressureStatus;
        this.currentDisease = currentDisease;
        this.previousSurgeries = previousSurgeries;
        this.previouslyCuredDiseases = previouslyCuredDiseases;
        this.height = height;
        this.weight = weight;
        this.isActive = isActive;
    }

    public Profile(Long id, User user, List<Payment> payment, String fullName, String relationship, String gender, LocalDate birthDate, String bloodGroup, String diabetesStatus, String bloodPressureStatus, String currentDisease, String previousSurgeries, String previouslyCuredDiseases, Double height, Double weight, boolean isActive) {
        this.id = id;
        this.user = user;
        this.payment = payment;
        this.fullName = fullName;
        this.relationship = relationship;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bloodGroup = bloodGroup;
        this.diabetesStatus = diabetesStatus;
        this.bloodPressureStatus = bloodPressureStatus;
        this.currentDisease = currentDisease;
        this.previousSurgeries = previousSurgeries;
        this.previouslyCuredDiseases = previouslyCuredDiseases;
        this.height = height;
        this.weight = weight;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDiabetesStatus() {
        return diabetesStatus;
    }

    public void setDiabetesStatus(String diabetesStatus) {
        this.diabetesStatus = diabetesStatus;
    }

    public String getBloodPressureStatus() {
        return bloodPressureStatus;
    }

    public void setBloodPressureStatus(String bloodPressureStatus) {
        this.bloodPressureStatus = bloodPressureStatus;
    }

    public String getCurrentDisease() {
        return currentDisease;
    }

    public void setCurrentDisease(String currentDisease) {
        this.currentDisease = currentDisease;
    }

    public String getPreviousSurgeries() {
        return previousSurgeries;
    }

    public void setPreviousSurgeries(String previousSurgeries) {
        this.previousSurgeries = previousSurgeries;
    }

    public String getPreviouslyCuredDiseases() {
        return previouslyCuredDiseases;
    }

    public void setPreviouslyCuredDiseases(String previouslyCuredDiseases) {
        this.previouslyCuredDiseases = previouslyCuredDiseases;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}


