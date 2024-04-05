package com.ADA.AdvancedHealthAcademy.Entity;

import jakarta.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "PrescriptionsUpload")
public class PrescriptionsUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private Blob image;

    private Date date = new Date();
    private String userId;
    private String profileId;

    public PrescriptionsUpload() {
    }

    public PrescriptionsUpload(long id, Blob image, Date date, String userId, String profileId) {
        this.id = id;
        this.image = image;
        this.date = date;
        this.userId = userId;
        this.profileId = profileId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}
