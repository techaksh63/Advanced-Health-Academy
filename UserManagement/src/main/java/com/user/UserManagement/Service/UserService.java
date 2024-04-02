package com.user.UserManagement.Service;

import com.user.UserManagement.DTO.UserInfoDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Utils.Converter.Entity_To_DTO.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
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
            }
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new Exception("Error Registering User: " + e.getMessage());
        }
    }

//    public List<User> getAllUsers() throws Exception {
//        try {
//            return userRepository.findAll();
//        } catch (DataAccessException e) {
//            throw new Exception("Error retrieving all User: " + e.getMessage());
//        }
//    }

    public Optional<User> getUserById(long userId) throws Exception {
        try {
            return userRepository.findById(userId);
        } catch (DataAccessException e) {
            throw new Exception("Error finding User by ID: " + e.getMessage());
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

    public void deleteUserById(long userId) throws Exception {
        try {
            userRepository.deleteById(userId);
        } catch (DataAccessException e) {
            throw new Exception("Error deleting User: " + e.getMessage());
        }
    }

    public User updateUser(User user) throws ChangeSetPersister.NotFoundException,Exception{
        if (user == null || user.getUserId() == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (!optionalUser.isPresent()){
            throw new ChangeSetPersister.NotFoundException();
        }
        User updateUser = optionalUser.get();
        updateUser.setUserName(user.getUserName());
        updateUser.setPassword(updateUser.getPassword());
        updateUser.setEmail(updateUser.getEmail());
        updateUser.setUserMobileNumber(user.getUserMobileNumber());
        updateUser.setProfile(user.getProfile());
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new Exception("Error Updating User: " + e.getMessage());
        }


    }

}
