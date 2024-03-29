package com.user.UserManagement.Service;

import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Repository.ProfileRepository;
import com.user.UserManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;

    public Profile createProfile(Long userId, Profile profile) throws Exception {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                throw new Exception("User with ID " + userId + " not found");
            }
            profile.setUser(optionalUser.get());

            return profileRepository.save(profile);
        } catch (DataAccessException e) {
            throw new Exception("Error saving profile: " + e.getMessage());
        }
    }

    public List<Profile> getAllProfiles() throws Exception {
        try {
            return profileRepository.findAll();
        } catch (DataAccessException e) {
            throw new Exception("Error retrieving all profile: " + e.getMessage());
        }
    }

    public Optional<List<Profile>> getProfileById(long userId) throws Exception {
        try {
            return Optional.ofNullable(profileRepository.findByUserid(userId));
        } catch (DataAccessException e) {
            throw new Exception("Error finding Profile by ID: " + e.getMessage());
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
