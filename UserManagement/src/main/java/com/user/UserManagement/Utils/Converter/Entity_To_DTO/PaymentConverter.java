package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.PaymentDTO.PaymentDTO;
import com.user.UserManagement.Entity.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentConverter {
    public PaymentDTO EntityToPaymentDTO(Payment payment) throws Exception{
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setUserId(payment.getUser().getUserId());
        paymentDTO.setProfileId(payment.getProfile().getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentSuccess(payment.isPaymentSuccess());
        return paymentDTO;
    }

    public List<PaymentDTO> PaymentObjectConvertToPaymentDTOs(List<Object> paymentData) {
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Object rawPaymentData : paymentData) {
            Object[] paymentValues = (Object[]) rawPaymentData;

            Long paymentId = (Long) paymentValues[0];
            Long userId = (Long) paymentValues[1];
            Long profileId = (Long) paymentValues[2];
            double amount = (Double) paymentValues[3];
            boolean isPaymentSuccess = (Boolean) paymentValues[4];

            PaymentDTO paymentDTO = new PaymentDTO(paymentId, userId, profileId, amount, isPaymentSuccess);
            paymentDTOs.add(paymentDTO);
        }
        return paymentDTOs;
    }

}
