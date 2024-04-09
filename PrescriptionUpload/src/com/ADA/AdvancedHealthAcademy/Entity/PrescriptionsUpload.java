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
    private Long profileId;

    public PrescriptionsUpload() {
    }

    public PrescriptionsUpload(long id, Blob image, Date date, Long profileId) {
        this.id = id;
        this.image = image;
        this.date = date;
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

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
