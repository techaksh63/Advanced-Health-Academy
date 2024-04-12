package com.user.UserManagement.DTO.UserDTO;

import com.user.UserManagement.DTO.ProfileDTO.ProfileDTO;

public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private String userMobileNumber;
    private String address;
    private Integer totalFamilyMembers;
    private ProfileDTO profile;

    public UserDTO(){
    }

    public UserDTO(String userName, String password, String email, String userMobileNumber, String address, Integer totalFamilyMembers, ProfileDTO profile) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userMobileNumber = userMobileNumber;
        this.address = address;
        this.totalFamilyMembers = totalFamilyMembers;
        this.profile = profile;
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

    public Integer getTotalFamilyMembers() {
        return totalFamilyMembers;
    }

    public void setTotalFamilyMembers(Integer totalFamilyMembers) {
        this.totalFamilyMembers = totalFamilyMembers;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
