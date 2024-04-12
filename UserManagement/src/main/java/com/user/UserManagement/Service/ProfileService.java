package com.user.UserManagement.Service;

import com.user.UserManagement.Configuration.EnvironmentVariables;
import com.user.UserManagement.DTO.PaymentDTO.PaymentDTO;
import com.user.UserManagement.DTO.PrescriptionDTO.PrescriptionInfoDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileDetailsDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileInfoDTO;
import com.user.UserManagement.DTO.ProfileDTO.UpdateProfileInfoDTO;
import com.user.UserManagement.Entity.Payment;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Repository.PaymentRepository;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.DTO_To_Entity.ProfileDTOConverter;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.PaymentConverter;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.ProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private PaymentConverter paymentConverter;
    @Autowired
    private ProfileDTOConverter profileDTOConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EnvironmentVariables environmentVariables;

    public List<PrescriptionInfoDTO> getAllPrescriptionInfo(long userId,long profileId)throws Exception{
        try {
            String url = environmentVariables.getAllPrescriptionInfoById();
            List<PrescriptionInfoDTO> response =  restTemplate.getForObject(url,List.class,profileId);
            return response;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all Prescription: " + e.getMessage());
        }
    }

    public PrescriptionInfoDTO getPrescriptionInfoById(long userId,long profileId,long prescriptionId)throws Exception{
        try {
            String url = environmentVariables.getPrescriptionInfoById();
            PrescriptionInfoDTO response =  restTemplate.getForObject(url,PrescriptionInfoDTO.class,profileId,prescriptionId);
            return response;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving Prescription: " + e.getMessage());
        }
    }
    public String deletePrescriptionById(long profileId, long prescriptionId)throws Exception{
        try {
            String url = environmentVariables.deletePrescriptionById();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, profileId, prescriptionId);
            return response.getBody();
        } catch (DataAccessException e) {
            throw new Exception("Error deleting Prescription: " + e.getMessage());
        }
    }
    public String deleteAllPrescriptionByProfileId(long profileId)throws Exception{
        try {
            String url = environmentVariables.deleteAllPrescriptionById();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, profileId);
            return response.getBody();
        } catch (DataAccessException e) {
            throw new Exception("Error deleting All Prescription by Profile Id: " +profileId+ " "+ e.getMessage());
        }
    }









    public PaymentDTO createProfile(Long userId, ProfileDTO profileDTO) throws Exception {
        try {
            Profile profile = profileDTOConverter.convertDtoToProfile(profileDTO);
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                throw new Exception("User with ID " + userId + " not found");
            }

            long existingProfilesCount = profileRepository.countActiveProfilesByUserid(userId,true);
            long pendingPaymentProfileCount = paymentRepository.countPendingPaymentOfProfilesByUserid(userId,false);

            long profileCount = existingProfilesCount+pendingPaymentProfileCount;
            long additionalProfileCost = 3;
            long price = 5;


            for (int i = 1; i <= profileCount; i++) {
                if (i == 1) {
                    price = 5;
                } else {
                    price += additionalProfileCost;
                }
            }

            profile.setUser(optionalUser.get());
            Profile save = profileRepository.save(profile);

            Payment payment = new Payment();
            payment.setUser(optionalUser.get());
            payment.setProfile(profile);
            payment.setAmount(price);
            payment.setPaymentSuccess(false);
            paymentRepository.save(payment);

            return paymentConverter.EntityToPaymentDTO(payment);
        } catch (DataAccessException e) {
            throw new Exception("Error saving profile: " + e.getMessage());
        }
    }
    public PaymentDTO profileActivate(Long userId, Long profileId) throws Exception {
        try {

            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                throw new Exception("User with ID " + userId + " not found");
            }

            Optional<Profile> optionalProfile = profileRepository.findById(profileId);
            if (!optionalProfile.isPresent() || !optionalProfile.get().getUser().getUserId().equals(userId)){
                throw new Exception("Profile with ID " + profileId + " not found");
            }
//            Profile profile = optionalProfile.get();
            if (optionalProfile.get().isActive()){
                throw new Exception("Profile with ID " + profileId + " already active");
            }

            List<Object> paymentObject = paymentRepository.AllPendingPaymentOfProfilesByUserid(userId, false);
            List<PaymentDTO> paymentDTO = paymentConverter.PaymentObjectConvertToPaymentDTOs(paymentObject);
            for(PaymentDTO paymentDTO1 : paymentDTO){
                if (paymentDTO1.getProfileId().equals(profileId)){
                    throw new Exception("Payment details already provided with Payment ID: " + paymentDTO1.getPaymentId());
                }
            }

            long existingProfilesCount = profileRepository.countActiveProfilesByUserid(userId,true);
            long pendingPaymentProfileCount = paymentRepository.countPendingPaymentOfProfilesByUserid(userId,false);

            long profileCount = existingProfilesCount+pendingPaymentProfileCount;
            Payment payment = getPayment(profileCount, optionalUser, optionalProfile);
            paymentRepository.save(payment);

            return paymentConverter.EntityToPaymentDTO(payment);
        } catch (DataAccessException e) {
            throw new Exception("Error saving profile: " + e.getMessage());
        }
    }

    private static Payment getPayment(long profileCount, Optional<User> optionalUser, Optional<Profile> optionalProfile) {
        long additionalProfileCost = 3;
        long price = 5;


        for (int i = 1; i <= profileCount; i++) {
            if (i == 1) {
                price = 5;
            } else {
                price += additionalProfileCost;
            }
        }


        Payment payment = new Payment();
        payment.setUser(optionalUser.get());
        payment.setProfile(optionalProfile.get());
        payment.setAmount(price);
        payment.setPaymentSuccess(false);
        return payment;
    }

    public Optional<ProfileDetailsDTO> getProfileDetailsById(long userId, long profileId) throws Exception {
        try {
            Optional<Object> profileInfoById = profileRepository.findProfileDetailsById(userId, profileId);
            if(profileInfoById.isPresent()) {
                Optional<ProfileDetailsDTO> profileDetailsDTO = profileConverter.convertProfileDetailsToDTO(profileInfoById);
                return profileDetailsDTO;
            }else {
                throw new UserNotFoundException("Profile with ID " + profileId + " not found");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error finding Profile by ID: " + e.getMessage());
        }
    }
    public Optional<ProfileDetailsDTO> getActiveProfileDetailsById(long userId, long profileId) throws Exception {
        try {
            Optional<Object> profileInfoById = profileRepository.findActiveProfileDetailsById(userId, profileId,true);
            if(profileInfoById.isPresent()){
                Optional<ProfileDetailsDTO> profileDetailsDTO = profileConverter.convertProfileDetailsToDTO(profileInfoById);
                return profileDetailsDTO;
            }else {
                throw new UserNotFoundException("Profile with " + profileId + " not Active");
            }
        } catch (DataAccessException e) {
            throw new Exception("Error finding Active Profile by ID: " + e.getMessage());
        }
    }

    public List<ProfileInfoDTO> getAllProfilesInfo(long userId)throws Exception{
        try {
           List<Object> profiles = profileRepository.findAllProfilesInfoByUserid(userId);
           List<ProfileInfoDTO> profileInfoDTO = profileConverter.convertQueryResultToProfileInfoDTOs(profiles);

            return profileInfoDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all profiles: " + e.getMessage());
        }
    }
    public List<ProfileInfoDTO> getAllActiveProfilesInfo(long userId)throws Exception{
        try {
           List<Object> profiles = profileRepository.findAllActiveProfilesInfoByUserid(userId,true);
           List<ProfileInfoDTO> profileInfoDTO = profileConverter.convertQueryResultToProfileInfoDTOs(profiles);

            return profileInfoDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all Active profiles: " + e.getMessage());
        }
    }

    public String deleteProfileById(long userId, long profileId) throws Exception {
        try {
            Optional<Profile> optionalProfile = profileRepository.findActiveProfileAllInfo(userId,profileId,true);
            if(optionalProfile.isPresent()){
                Profile profile = optionalProfile.get();
                profile.setActive(false);
                try {
                    List<PrescriptionInfoDTO> prescription = getAllPrescriptionInfo(userId,profileId);
                    if (!prescription.isEmpty()){
                        deleteAllPrescriptionByProfileId(profileId);
                    }
                }catch (Exception e){
                    System.out.println("");
                }
                profileRepository.save(profile);
                return "Profile successfully Inactivated with ID " + profileId;
            }else {
                throw new ResourceNotFoundException("No Active Profile found with ID: " + profileId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error deleting Profile: " + e.getMessage());
        }
    }


    public UpdateProfileInfoDTO updateProfile(long userId,long profileId,UpdateProfileInfoDTO updateProfileInfoDTO) throws Exception {
        if (updateProfileInfoDTO == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        Optional<Profile> optionalProfile = profileRepository.findActiveProfileAllInfo(userId,profileId,true);
        if (!optionalProfile.isPresent()){
            throw new UserNotFoundException("Profile with ID " + profileId + " not found");
        }
        Profile updateProfile = optionalProfile.get();

        if (updateProfileInfoDTO.getFullName() != null){
            updateProfile.setFullName(updateProfileInfoDTO.getFullName());
        }
        if(updateProfileInfoDTO.getRelationship() != null){
            updateProfile.setRelationship(updateProfileInfoDTO.getRelationship());
        }
        if (updateProfileInfoDTO.getGender() != null){
            updateProfile.setGender(updateProfileInfoDTO.getGender());
        }
        if(updateProfileInfoDTO.getBirthDate() != null){
            updateProfile.setBirthDate(updateProfileInfoDTO.getBirthDate());
        }
        if(updateProfileInfoDTO.getBloodGroup() != null){
            updateProfile.setBloodGroup(updateProfileInfoDTO.getBloodGroup());
        }
        if(updateProfileInfoDTO.getDiabetesStatus() != null){
            updateProfile.setDiabetesStatus(updateProfileInfoDTO.getDiabetesStatus());
        }
        if(updateProfileInfoDTO.getBloodPressureStatus() != null){
            updateProfile.setBloodPressureStatus(updateProfileInfoDTO.getBloodPressureStatus());
        }
        if(updateProfileInfoDTO.getCurrentDisease() != null){
            updateProfile.setCurrentDisease(updateProfileInfoDTO.getCurrentDisease());
        }
        if(updateProfileInfoDTO.getPreviousSurgeries() != null){
            updateProfile.setPreviousSurgeries(updateProfileInfoDTO.getPreviousSurgeries());
        }
        if(updateProfileInfoDTO.getPreviouslyCuredDiseases() != null){
            updateProfile.setPreviouslyCuredDiseases(updateProfileInfoDTO.getPreviouslyCuredDiseases());
        }
        if(updateProfileInfoDTO.getHeight() != null){
            updateProfile.setHeight(updateProfileInfoDTO.getHeight());
        }

        try {
            Profile save = profileRepository.save(updateProfile);
            return profileConverter.UpdateProfileInfoConverter(save);
        } catch (DataAccessException e) {
            throw new Exception("Error Updating User: " + e.getMessage());
        }

    }
}
