package com.user.UserManagement.DTO.UserDTO;

import com.user.UserManagement.DTO.ProfileDTO.ProfileDetailsDTO;

import java.time.LocalDate;

public class UserRegisterDTO {
    private String userName;
    private String email;
    private String userMobileNumber;
    private String address;
    private Integer totalFamilyMembers;
    private LocalDate date;
    private boolean isActive ;
    private ProfileDetailsDTO profile;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String userName, String email, String userMobileNumber, String address, Integer totalFamilyMembers, LocalDate date, boolean isActive, ProfileDetailsDTO profile) {
        this.userName = userName;
        this.email = email;
        this.userMobileNumber = userMobileNumber;
        this.address = address;
        this.totalFamilyMembers = totalFamilyMembers;
        this.date = date;
        this.isActive = isActive;
        this.profile = profile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getTotalFamilyMembers() {
        return totalFamilyMembers;
    }

    public void setTotalFamilyMembers(Integer totalFamilyMembers) {
        this.totalFamilyMembers = totalFamilyMembers;
    }

    public ProfileDetailsDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDetailsDTO profile) {
        this.profile = profile;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
