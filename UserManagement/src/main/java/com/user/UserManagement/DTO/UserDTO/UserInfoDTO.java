package com.user.UserManagement.DTO.UserDTO;

import com.user.UserManagement.Entity.Profile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class UserInfoDTO {
    private String userName;
    private String email;
    private String userMobileNumber;
    private String address;
    private int totalFamilyMembers;
    public UserInfoDTO(){

    }

    public UserInfoDTO(String userName, String email, String userMobileNumber, String address, int totalFamilyMembers) {
        this.userName = userName;
        this.email = email;
        this.userMobileNumber = userMobileNumber;
        this.address = address;
        this.totalFamilyMembers = totalFamilyMembers;
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

    public int getTotalFamilyMembers() {
        return totalFamilyMembers;
    }

    public void setTotalFamilyMembers(int totalFamilyMembers) {
        this.totalFamilyMembers = totalFamilyMembers;
    }
}
