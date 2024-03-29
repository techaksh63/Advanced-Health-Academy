package com.user.UserManagement.Controller;

import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Service.ProfileService;
import com.user.UserManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createProfile(@PathVariable long userId, @RequestBody Profile profile) throws Exception {
        Profile createdDetails = profileService.createProfile(userId, profile);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getProfileById(@PathVariable long userId) {
        try {
            Optional<List<Profile>> Profile = profileService.getProfileById(userId);
            if (Profile.isPresent()) {
                return new ResponseEntity<>(Profile.get(), HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User details for user ID " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PutMapping
//    public ResponseEntity<?> updateProfile(@PathVariable long userId, @RequestBody Profile Profile) {
//        Profile.setUserId(userId);
//        try {
//            Optional<Profile> existingDetails = profileService.getProfileById(userId);
//            if (existingDetails.isPresent()) {
//                Profile updatedDetails = profileService.updateProfile(Profile);
//                return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
//            } else {
//                throw new UserNotFoundException("User details for user ID " + userId + " not found");
//            }
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @DeleteMapping
    public ResponseEntity<?> deleteProfileById(@PathVariable long userId) {
        try {
            profileService.deleteProfileById(userId);
            return ResponseEntity.ok("Successfully Deleted Profile");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}