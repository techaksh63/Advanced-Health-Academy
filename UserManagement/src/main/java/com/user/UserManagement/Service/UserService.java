package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.UpdateUserInfoDTO;
import com.user.UserManagement.DTO.UserInfoDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public User createUser(User user) throws Exception {
        try {
            for (Profile profile : user.getProfile()) {
                profile.setUser(user);
                profile.setActive(true);
            }
            return userRepository.save(user);
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

    public String deleteUserById(long userId) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()){
                User deactiveUser = userOptional.get();
                deactiveUser.setActive(false);

                List<Profile> profileOptional = profileRepository.findAll();
                if (!profileOptional.isEmpty()){
                    for (Profile profile : deactiveUser.getProfile()) {
                        profile.setActive(false);
                    }
                    userRepository.save(deactiveUser);
                    return "User Deactivated with all the Profiles";
                } else {
                    throw new ResourceNotFoundException("Profiles not found with ID: " + userId);
                }


//                return "User Deactivated";
            }else {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            }

        } catch (DataAccessException e) {
            throw new Exception("Error deleting User: " + e.getMessage());
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


}
