package com.user.UserManagement.DTO;

public class ProfileInfoDTO {
    private String fullName;
    private String relationship;
    private String gender;
    private boolean isActive;

    public ProfileInfoDTO() {
    }

    public ProfileInfoDTO(String fullName, String relationship, String gender, boolean isActive) {
        this.fullName = fullName;
        this.relationship = relationship;
        this.gender = gender;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
