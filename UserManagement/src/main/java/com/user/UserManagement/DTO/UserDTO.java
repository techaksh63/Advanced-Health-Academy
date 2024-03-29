package com.user.UserManagement.DTO;

import com.user.UserManagement.Entity.Profile;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private String userMobileNumber;
    private String address;
    private int totalFamilyMembers;
    private LocalDate date = LocalDate.now();
    private List<Profile> profile;

    public UserDTO(){
    }

    public UserDTO(Long userId, String userName, String password, String email, String userMobileNumber, String address, int totalFamilyMembers, LocalDate date, List<Profile> profile) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userMobileNumber = userMobileNumber;
        this.address = address;
        this.totalFamilyMembers = totalFamilyMembers;
        this.date = date;
        this.profile = profile;
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
}
