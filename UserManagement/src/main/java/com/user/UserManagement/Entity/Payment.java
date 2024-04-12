package com.user.UserManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private double amount;
    private boolean isPaymentSuccess = false;
    public Payment() {
    }

    public Payment(User user, Profile profile, double amount, boolean isPaymentSuccess) {
        this.user = user;
        this.profile = profile;
        this.amount = amount;
        this.isPaymentSuccess = isPaymentSuccess;
    }
    public Payment(Long paymentId, User user, Profile profile, double amount, boolean isPaymentSuccess) {
        this.paymentId = paymentId;
        this.user = user;
        this.profile = profile;
        this.amount = amount;
        this.isPaymentSuccess = isPaymentSuccess;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaymentSuccess() {
        return isPaymentSuccess;
    }

    public void setPaymentSuccess(boolean paymentSuccess) {
        this.isPaymentSuccess = paymentSuccess;
    }
}
