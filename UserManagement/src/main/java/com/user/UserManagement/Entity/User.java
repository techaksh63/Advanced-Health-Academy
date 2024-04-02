package com.user.UserManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;
        @Column(nullable = false, unique = true)
        private String userName;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false, unique = true)
        private String email;
        @Column(unique = true)
        private String userMobileNumber;
        @Column(nullable = false)
        private String address;
        @Column(nullable = false)
        private int totalFamilyMembers;
        @Column(nullable = false)
        private LocalDate date = LocalDate.now();
        @Column(nullable = false,columnDefinition = "BOOLEAN")
        private boolean isActive = true;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        @JsonBackReference
        private List<Profile> profile;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//        @JsonBackReference
        private List<Payment> payment;



        public User() {
        }

        public User(Long userId, String userName, String password, String email, String userMobileNumber, String address, int totalFamilyMembers, LocalDate date, boolean isActive, List<Profile> profile, List<Payment> payment) {
                this.userId = userId;
                this.userName = userName;
                this.password = password;
                this.email = email;
                this.userMobileNumber = userMobileNumber;
                this.address = address;
                this.totalFamilyMembers = totalFamilyMembers;
                this.date = date;
                this.isActive = isActive;
                this.profile = profile;
                this.payment = payment;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getUserMobileNumber() {
                return userMobileNumber;
        }

        public void setUserMobileNumber(String userMobileNumber) {
                this.userMobileNumber = userMobileNumber;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public int getTotalFamilyMembers() {
                return totalFamilyMembers;
        }

        public void setTotalFamilyMembers(int totalFamilyMembers) {
                this.totalFamilyMembers = totalFamilyMembers;
        }

        public LocalDate getDate() {
                return date;
        }

        public void setDate(LocalDate date) {
                this.date = date;
        }


        public List<Profile> getProfile() {
                return profile;
        }

        public void setProfile(List<Profile> profile) {
                this.profile = profile;
        }

        public boolean isActive() {
                return isActive;
        }

        public void setActive(boolean active) {
                isActive = active;
        }

        public List<Payment> getPayment() {
                return payment;
        }

        public void setPayment(List<Payment> payment) {
                this.payment = payment;
        }
}






