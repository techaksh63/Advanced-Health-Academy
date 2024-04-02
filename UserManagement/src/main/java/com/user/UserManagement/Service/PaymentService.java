package com.user.UserManagement.Service;

import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
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
}
