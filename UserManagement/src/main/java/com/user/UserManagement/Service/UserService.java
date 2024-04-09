package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.PrescriptionDTO.PrescriptionInfoDTO;
import com.user.UserManagement.DTO.UserDTO.UpdateUserInfoDTO;
import com.user.UserManagement.DTO.UserDTO.UserDTO;
import com.user.UserManagement.DTO.UserDTO.UserInfoDTO;
import com.user.UserManagement.DTO.UserDTO.UserRegisterDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.DTO_To_Entity.UserDTOConverter;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserDTOConverter userDTOConverter;

    public UserRegisterDTO createUser(UserDTO userDTO) throws Exception {
        try {
            User user = userDTOConverter.DTOToEntity(userDTO);
            for (Profile profile : user.getProfile()) {
                profile.setUser(user);
                profile.setActive(true);
            }
            User save = userRepository.save(user);
            UserRegisterDTO userRegisterDTO = userConverter.entityToUserRegisterDTO(user);

            return userRegisterDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error Registering User: " + e.getMessage());
        }
    }

    public Optional<UserInfoDTO> getUserInfoById(long userId) throws Exception {
        try {
            Optional<Object> optionalUser = userRepository.findUserInfoById(userId);
            Optional<UserInfoDTO> userInfoDTO = Optional.of(new UserInfoDTO());
            userInfoDTO = userConverter.convertQueryResultToUserInfoDTO(optionalUser);
            return userInfoDTO;
        } catch (DataAccessException e) {
            throw new Exception("Error finding User by ID: " + e.getMessage());
        }

    }

    public UpdateUserInfoDTO updateUser(long userId, UpdateUserInfoDTO updateUserInfoDTO) throws ChangeSetPersister.NotFoundException,Exception{
        if (updateUserInfoDTO == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        User updateUser = optionalUser.get();

        if (updateUserInfoDTO.getUserName() != null) {
            updateUser.setUserName(updateUserInfoDTO.getUserName());
        }
        if (updateUserInfoDTO.getPassword() != null) {
            updateUser.setPassword(updateUserInfoDTO.getPassword());
        }
        if (updateUserInfoDTO.getEmail() != null) {
            updateUser.setEmail(updateUserInfoDTO.getEmail());
        }
        if (updateUserInfoDTO.getUserMobileNumber() != null) {
            updateUser.setUserMobileNumber(updateUserInfoDTO.getUserMobileNumber());
        }
        if (updateUserInfoDTO.getAddress() != null) {
            updateUser.setAddress(updateUserInfoDTO.getAddress());
        }
        if (updateUserInfoDTO.getTotalFamilyMembers() != null) {
            updateUser.setTotalFamilyMembers(updateUserInfoDTO.getTotalFamilyMembers());
        }

        try {
            User save = userRepository.save(updateUser);
            return userConverter.UpdateUserInfoConverter(save);
        } catch (DataAccessException e) {
            throw new Exception("Error Updating User: " + e.getMessage());
        }
    }

    public String deleteUserById(long userId) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()){
                User deactiveUser = userOptional.get();
                if(deactiveUser.isActive()){
                    deactiveUser.setActive(false);

                    List<Profile> profileOptional = profileRepository.findAll();
                    if (!profileOptional.isEmpty()){
                        for (Profile profile : deactiveUser.getProfile()) {
                            profile.setActive(false);

                            List<PrescriptionInfoDTO> prescription = profileService.getAllPrescriptionInfo(userId,profile.getId());
                            if (!prescription.isEmpty()){
                                profileService.deleteAllPrescriptionByProfileId(profile.getId());
                            }

                        }
                        userRepository.save(deactiveUser);
                        return "User Deactivated with all the Profiles";
                    } else {
                        throw new ResourceNotFoundException("Profiles not found with ID: " + userId);
                    }
                }
                else {
                    throw new ResourceNotFoundException("User already Inactive with ID: " + userId);
                }
            }else {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            }
        } catch (DataAccessException e) {
            throw new Exception("Error deleting User: " + e.getMessage());
        }
    }




    public String userActivationById(long userId) throws Exception{
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                if(!user.isActive()){
                    user.setActive(true);
                    Optional<Profile> profileOptional = profileRepository.findProfileByRelationship(userId,"self");
                    if (profileOptional.isPresent()){
                        Profile profile = profileOptional.get();
                        if (!profile.isActive()){
                            profile.setActive(true);
                            profileRepository.save(profile);
                        }else {
                            throw new ResourceNotFoundException("Profile already Active with User ID: " + userId);
                        }
                    }else {
                        throw new ResourceNotFoundException("Profile Not found with User ID: " + userId);
                    }
                 userRepository.save(user);
                 return "User Activated Successfully";
                }else {
                    throw new ResourceNotFoundException("User already Active with ID: " + userId);
                }
            }else {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            }

        }catch (DataAccessException e) {
            throw new Exception("Error Activating User: " + e.getMessage());
        }
    }



}
