package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Repository.PaymentRepository;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.ProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
@Autowired
private ProfileConverter profileConverter;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;


//    public Profile createProfile(Long userId, Profile profile) throws Exception {
//        try {
//            Optional<User> optionalUser = userRepository.findById(userId);
//            if (!optionalUser.isPresent()) {
//                throw new Exception("User with ID " + userId + " not found");
//            }
//            profile.setUser(optionalUser.get());
//
//            return profileRepository.save(profile);
//        } catch (DataAccessException e) {
//            throw new Exception("Error saving profile: " + e.getMessage());
//        }
//    }




    public Profile createProfile(Long userId, Profile profile) throws Exception {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                throw new Exception("User with ID " + userId + " not found");
            }

            long existingProfilesCount = profileRepository.countActiveProfilesByUserid(userId,true);

            long additionalProfileCost = 3;
            long price = 5;

            if (existingProfilesCount == 2) {
                price = additionalProfileCost * existingProfilesCount;
            }

            profile.setUser(optionalUser.get());
            Profile save = profileRepository.save(profile);

            Payment payment = new Payment();
            payment.setUser(optionalUser.get());
            payment.setProfile(profile);
            payment.setAmount(price);
            payment.setPaymentStatus(false);
            paymentRepository.save(payment);
//            paymentService.CreatePayment(payment);



//            if (price > 0) {
//                boolean paymentSuccess = paymentService.processPayment(userId, price, "Profile creation fee");
//                if (!paymentSuccess) {
//                    throw new Exception("Payment failed for profile creation");
//                }
//            }


            return save;
        } catch (DataAccessException e) {
            throw new Exception("Error saving profile: " + e.getMessage());
        }
    }


//    public List<Profile> getAllProfiles() throws Exception {
//        try {
//            return profileRepository.findAll();
//        } catch (DataAccessException e) {
//            throw new Exception("Error retrieving all profile: " + e.getMessage());
//        }
//    }

    public Optional<Profile> getProfileById(long userId, long profileId) throws Exception {
        try {
            System.out.println(profileRepository.countActiveProfilesByUserid(userId, false));
            return profileRepository.findProfileAllByUserid(userId, profileId);
        } catch (DataAccessException e) {
            throw new Exception("Error finding Profile by ID: " + e.getMessage());
        }
    }

    public List<ProfileInfoDTO> getAllProfilesInfo(long userId)throws Exception{
        try {

//            List<Profile> profile = profileRepository.findProfileAllByUserid(userId);
           List<Object> profiles = profileRepository.findProfileInfoByUserid(userId);
           List<ProfileInfoDTO> profileInfoDTO = profileConverter.convertQueryResultToProfileInfoDTOs(profiles);

            return profileInfoDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all profiles: " + e.getMessage());
        }
    }




    public void deleteProfileById(long userId) throws Exception {
        try {
            profileRepository.deleteById(userId);
        } catch (DataAccessException e) {
            throw new Exception("Error deleting Profile: " + e.getMessage());
        }
    }

    public Profile updateProfile(Profile profile) throws Exception {
        if (profile == null || profile.getId() == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        Optional<Profile> optionalProfile = profileRepository.findById(profile.getId());
        if (!optionalProfile.isPresent()){
            throw new ChangeSetPersister.NotFoundException();
        }
        Profile updateProfile = optionalProfile.get();
        updateProfile.setFullName(profile.getFullName());



        try {
            return profileRepository.save(profile);
        } catch (DataAccessException e) {
            throw new Exception("Error Updating User: " + e.getMessage());
        }

    }
}
