package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.UpdatePaymentRequestDTO;
import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Repository.PaymentRepository;
import com.user.UserManagement.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProfileRepository profileRepository;
//    public boolean processPayment(Long userId, int amount, String description) throws Exception {
//
//        System.out.println("Simulating payment processing for user " + userId +
//                ", amount: " + amount + ", description: " + description);
//        return true;
//    }

    public Payment CreatePayment(Payment payment) throws Exception {
        try {
            return paymentRepository.save(payment);
        } catch (DataAccessException e) {
            throw new Exception("Error saving Payment: " + e.getMessage());
        }
    }

    public long PendingPaymentProfile(long userId) throws Exception{
        try {
            return paymentRepository.countPendingPaymentOfProfilesByUserid(userId,false);

        }catch (DataAccessException e) {
            throw new Exception("Error finding Pending Payment Profiles by ID: " + e.getMessage());
        }
    }


    public boolean updatePaymentSuccess(UpdatePaymentRequestDTO request) throws Exception {
        Long paymentId = request.getPaymentId();
        Long userId = request.getUserId();
        Long profileId = request.getProfileId();
        double receivedAmount = request.getAmount();

        // Find Payment by ID
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (!optionalPayment.isPresent()) {
            throw new Exception("Payment with ID " + paymentId + " not found");
        }

        Payment payment = optionalPayment.get();

        // Validate user and ownership (optional)
        if (!payment.getUser().getUserId().equals(userId)) {
            throw new Exception("Unauthorized access: Payment does not belong to user");
        }

        // Validate profile association (optional)
        if (!payment.getProfile().getId().equals(profileId)) {
            throw new Exception("Payment not associated with provided profile ID");
        }

        // Validate payment amount
        if (payment.getAmount() != receivedAmount) {
            throw new Exception("Payment amount mismatch: Received amount does not match database record");
        }
        payment.setPaymentSuccess(true);
        paymentRepository.save(payment);

        //Activating Profile
        Optional<Profile> optionalProfile = profileRepository.findById(payment.getProfile().getId());
        if (!optionalProfile.isPresent()) {
            throw new Exception("Payment is done but error Activating Profile ");
        }
        Profile profile = optionalProfile.get();
        profile.setActive(true);
        profileRepository.save(profile);

        return true;
    }

}
