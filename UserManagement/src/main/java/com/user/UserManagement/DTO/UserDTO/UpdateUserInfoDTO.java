package com.user.UserManagement.DTO.UserDTO;

public class UpdateUserInfoDTO {
    private String userName;
    private String password;
    private String email;
    private String userMobileNumber;
    private String address;
    private Integer totalFamilyMembers;

    public UpdateUserInfoDTO() {
    }

    public UpdateUserInfoDTO(String userName, String password, String email, String userMobileNumber, String address, Integer totalFamilyMembers) {
        this.userName = userName;
        this.password = password;
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
}
