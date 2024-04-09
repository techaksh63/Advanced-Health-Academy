package com.user.UserManagement.Controller;

import com.user.UserManagement.DTO.PaymentDTO.PaymentDTO;
import com.user.UserManagement.DTO.PrescriptionDTO.PrescriptionInfoDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileDetailsDTO;
import com.user.UserManagement.DTO.ProfileDTO.ProfileInfoDTO;
import com.user.UserManagement.DTO.ProfileDTO.UpdateProfileInfoDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Service.PaymentService;
import com.user.UserManagement.Service.ProfileService;
import com.user.UserManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/{userId}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{profileId}/all-prescriptions")
    public ResponseEntity<?> getAllPrescriptionsFromPrescriptionService(@PathVariable long userId,@PathVariable long profileId) {
        try {
            List<PrescriptionInfoDTO> prescription = profileService.getAllPrescriptionInfo(userId,profileId);
            if (!prescription.isEmpty()){
                return new ResponseEntity<>(prescription, HttpStatus.OK);
            }else {
                throw new ServiceNotFoundException("Prescriptions of Profile " + profileId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{profileId}/prescription/{prescriptionId}")
    public ResponseEntity<?> getPrescriptionByIdFromPrescriptionService(@PathVariable long userId,@PathVariable long profileId,@PathVariable Long prescriptionId) {
        try {
            PrescriptionInfoDTO prescription = profileService.getPrescriptionInfoById(userId,profileId,prescriptionId);
            if (!(prescription == null)){
                return new ResponseEntity<>(prescription, HttpStatus.OK);
            }else {
                throw new ServiceNotFoundException("Prescriptions of Profile " + profileId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/{profileId}/prescription/{prescriptionId}/delete")
    public ResponseEntity<?> DeletePrescriptionByIdFromPrescriptionService(@PathVariable long userId,@PathVariable long profileId,@PathVariable Long prescriptionId) {
        try {
            PrescriptionInfoDTO prescription = profileService.getPrescriptionInfoById(userId,profileId,prescriptionId);
            if (!(prescription == null)){
                return ResponseEntity.ok(profileService.deletePrescriptionById(profileId,prescriptionId));
            }else {
                throw new ServiceNotFoundException("Prescriptions of Profile " + profileId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/{profileId}/prescription/delete")
    public ResponseEntity<?> DeleteAllPrescriptionByProfileIdFromPrescriptionService(@PathVariable long userId,@PathVariable long profileId) {
        try {
            List<PrescriptionInfoDTO> prescription = profileService.getAllPrescriptionInfo(userId,profileId);
            if (!prescription.isEmpty()){
                return ResponseEntity.ok(profileService.deleteAllPrescriptionByProfileId(profileId));
            }else {
                throw new ServiceNotFoundException("Prescriptions of Profile " + profileId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }










    @PutMapping("/{profileId}/activate")
    public ResponseEntity<?> profileActivation(@PathVariable long userId,@PathVariable long profileId) throws Exception{
          try {
              PaymentDTO paymentDetails = profileService.profileActivate(userId,profileId);
              return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
          }catch (Exception e) {
              throw new RuntimeException(e);
          }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createProfile(@PathVariable long userId, @RequestBody ProfileDTO profileDTO) throws Exception {
        try {
            PaymentDTO paymentDetails = profileService.createProfile(userId, profileDTO);
            return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all-profiles")
    public ResponseEntity<?> getAllProfilesInfoByUserId(@PathVariable long userId) {
        try {
            List<ProfileInfoDTO> profiles = profileService.getAllProfilesInfo(userId);
            if(!profiles.isEmpty()){
                return new ResponseEntity<>(profiles, HttpStatus.OK);
            }
            else {
                throw new UserNotFoundException("Profiles of " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/all-active-profiles")
    public ResponseEntity<?> getAllActiveProfilesInfoByUserId(@PathVariable long userId) {
        try {
            List<ProfileInfoDTO> profiles = profileService.getAllActiveProfilesInfo(userId);
            if(!profiles.isEmpty()){
                return new ResponseEntity<>(profiles, HttpStatus.OK);
            }
            else {
                throw new UserNotFoundException("Active Profiles of " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{profileId}/details")
    public ResponseEntity<?> getProfileDetailsBy(@PathVariable long userId,@PathVariable long profileId) {
        try {
            Optional<ProfileDetailsDTO> profileDetailsDTO = profileService.getProfileDetailsById(userId, profileId);
            if (profileDetailsDTO.isPresent()) {
                return new ResponseEntity<>(profileDetailsDTO.get(), HttpStatus.OK);
            } else {
                throw new UserNotFoundException("Profile details for user ID " + userId + " and profile ID " + profileId +" not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{profileId}/active-profile-details")
    public ResponseEntity<?> getActiveProfileDetailsBy(@PathVariable long userId,@PathVariable long profileId) {
        try {
            Optional<ProfileDetailsDTO> profileDetailsDTO = profileService.getActiveProfileDetailsById(userId, profileId);
            if (profileDetailsDTO.isPresent()) {
                return new ResponseEntity<>(profileDetailsDTO.get(), HttpStatus.OK);
            } else {
                throw new UserNotFoundException("Profile details for user ID " + userId + " and profile ID " + profileId +" not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{profileId}/update")
    public ResponseEntity<?> updateProfile(@PathVariable long userId, @PathVariable long profileId, @RequestBody UpdateProfileInfoDTO updateProfileInfoDTO) {
        try {
            Optional<Profile> existingDetails = profileRepository.findActiveProfileAllInfo(userId,profileId,true);
            if (existingDetails.isPresent()) {
                UpdateProfileInfoDTO updatedDetails = profileService.updateProfile(userId,profileId,updateProfileInfoDTO);
                return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User details for user ID " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{profileId}/delete")
    public ResponseEntity<?> deleteProfileById(@PathVariable long userId, @PathVariable long profileId) {
        try {
            Optional<Profile> profileOptional = profileRepository.findActiveProfileAllInfo(userId,profileId,true);
            if (profileOptional.isPresent()){
                return ResponseEntity.ok(profileService.deleteProfileById(userId,profileId));
            }else {
                throw new ResourceNotFoundException("Active Profile not found with ID: " + profileId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}