package com.user.UserManagement.DTO.PaymentDTO;

public class UpdatePaymentRequestDTO {
    private Long paymentId;
    private Long userId;
    private Long profileId;
    private double amount;

    public UpdatePaymentRequestDTO() {
    }

    public UpdatePaymentRequestDTO(Long paymentId, Long userId, Long profileId, double amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.profileId = profileId;
        this.amount = amount;
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
}
