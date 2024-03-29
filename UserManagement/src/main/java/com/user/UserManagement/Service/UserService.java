package com.user.UserManagement.Service;

import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
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

    public User createUser(User user) throws Exception {
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new Exception("Error Registering User: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all User: " + e.getMessage());
        }
    }

    public Optional<User> getUserById(long userId) throws Exception {
        try {

            return userRepository.findById(userId);
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
