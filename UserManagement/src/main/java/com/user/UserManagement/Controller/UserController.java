package com.user.UserManagement.Controller;

import com.user.UserManagement.DTO.UserDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Service.ProfileService;
import com.user.UserManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
//    @GetMapping
//    public ResponseEntity<?> getAllUsers() throws Exception {
//        try {
//            List<User> users = userService.getAllUsers();
//            List<Profile> profiles = profileService.getAllProfiles();
//
//            List<UserDTO> usersDTO = new ArrayList<>();
//
//            return ResponseEntity.ok(usersDTO);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User with ID " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody User user) {
        try {
            Optional<User> existingUser = userService.getUserById(userId);
            if (existingUser.isPresent()) {
                user.setUserId(userId);
                User updatedUser = userService.updateUser(user);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User with ID " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        try {
            Optional<User> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()){
                userService.deleteUserById(userId);
                return ResponseEntity.ok("Successfully Deleted User");
            }else {
                throw new ResourceNotFoundException("To Delete, User not found with ID: " + userId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
