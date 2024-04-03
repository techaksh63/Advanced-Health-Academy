package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.PaymentDTO;
import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    public PaymentDTO Entity_to_PaymentDTO(Payment payment) throws Exception{
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setUserId(payment.getUser().getUserId());
        paymentDTO.setProfileId(payment.getProfile().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentSuccess(payment.isPaymentSuccess());
        return paymentDTO;
    }
}
