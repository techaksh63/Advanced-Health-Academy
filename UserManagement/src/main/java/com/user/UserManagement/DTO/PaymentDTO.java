package com.user.UserManagement.DTO;

import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;

public class PaymentDTO {
    private Long paymentId;
    private Long userId;
    private Long profileId;
    private double amount;
    private boolean isPaymentSuccess = false;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentId, Long userId, Long profileId, double amount, boolean isPaymentSuccess) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.profileId = profileId;
        this.amount = amount;
        this.isPaymentSuccess = isPaymentSuccess;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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
        isPaymentSuccess = paymentSuccess;
    }
}
