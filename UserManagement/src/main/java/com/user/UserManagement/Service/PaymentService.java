package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.PaymentDTO.PaymentDTO;
import com.user.UserManagement.DTO.PaymentDTO.UpdatePaymentRequestDTO;
import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Repository.PaymentRepository;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.PaymentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
     @Autowired
    private PaymentConverter paymentConverter;

    public Payment CreatePayment(Payment payment) throws Exception {
        try {
            return paymentRepository.save(payment);
        } catch (DataAccessException e) {
            throw new Exception("Error saving Payment: " + e.getMessage());
        }
    }

    public List<PaymentDTO> PendingProfilesPayment(long userId) throws Exception{
        try {
            boolean isActive = userRepository.isActiveUser(userId);
            if (isActive){
                List<Object> paymentObject = paymentRepository.AllPendingPaymentOfProfilesByUserid(userId, false);
                List<PaymentDTO> payment = paymentConverter.PaymentObjectConvertToPaymentDTOs(paymentObject);
                return payment;
            }else {
                throw new Exception("User is Inactive with ID: "+ userId);
            }
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

        //Validate payment status
        if (payment.isPaymentSuccess()){
            throw new Exception("Payment is already done");
        }

        // Validate user and ownership (optional)
        if (!payment.getUser().getUserId().equals(userId)) {
            throw new Exception("Unauthorized access: Payment does not belong to provided user");
        }
        //Validate user is Active or not
        if(!payment.getUser().isActive()){
            throw new Exception("User is not Active so Payment can not be done to active profile");
        }

        // Validate profile association (optional)
        if (!payment.getProfile().getId().equals(profileId)) {
            throw new Exception("Payment not associated with provided profile ID");
        }

        Optional<Profile> optionalProfile = profileRepository.findById(payment.getProfile().getId());
        // Validate profile is present in database
        if (!optionalProfile.isPresent()) {
            throw new Exception("Profile not found with provided ID" );
        }

        //Validate profile is Inactive
        boolean isActive = profileRepository.isActiveProfile(payment.getProfile().getId());
        if (isActive){
            throw new Exception("Profile is already Active with provided ID" );

        }

        // Validate payment amount
        if (payment.getAmount() != receivedAmount) {
            throw new Exception("Payment amount mismatch: Received amount does not match amount you need to pay");
        }
        payment.setPaymentSuccess(true);
        paymentRepository.save(payment);

        //Activating Profile
        if (!optionalProfile.isPresent()) {
            throw new Exception("Payment is done but error Activating Profile ");
        }
        Profile profile = optionalProfile.get();
        if (!profile.isActive()){
            profile.setActive(true);
            profileRepository.save(profile);
        }

        return true;
    }

}
