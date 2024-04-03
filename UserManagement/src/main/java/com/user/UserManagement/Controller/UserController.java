package com.user.UserManagement.Controller;

import com.user.UserManagement.DTO.UpdatePaymentRequestDTO;
import com.user.UserManagement.DTO.UpdateUserInfoDTO;
import com.user.UserManagement.DTO.UserDTO;
import com.user.UserManagement.DTO.UserInfoDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import com.user.UserManagement.Exception.ResourceNotFoundException;
import com.user.UserManagement.Exception.UserNotFoundException;
import com.user.UserManagement.Repository.UserRepository;
import com.user.UserManagement.Service.PaymentService;
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
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/payment")
    public ResponseEntity<?> updatePaymentSuccess(@RequestBody UpdatePaymentRequestDTO request) throws Exception {
        try {
            boolean success = paymentService.updatePaymentSuccess(request);
            if (success) {
                return ResponseEntity.ok("Payment done Successfully");
            } else {
                throw new Exception("Payment update failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{userId}/info")
    public ResponseEntity<?> getUserInfoById(@PathVariable long userId) {
        try {
            Optional<UserInfoDTO> userInfo = userService.getUserInfoById(userId);
            if (userInfo.isPresent()) {
                return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
            } else {
                throw new UserNotFoundException("User with ID " + userId + " not found");
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getUserById(@PathVariable long userId) {
//        try {
//            Optional<User> user = userService.getUserById(userId);
//            if (user.isPresent()) {
//                return new ResponseEntity<>(user.get(), HttpStatus.OK);
//            } else {
//                throw new UserNotFoundException("User with ID " + userId + " not found");
//            }
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody UpdateUserInfoDTO updateUserInfoDTO) {
        try {
            Optional<User> existingUser = userRepository.findById(userId);
            if (existingUser.isPresent()) {
                UpdateUserInfoDTO updatedUser = userService.updateUser(userId,updateUserInfoDTO);
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

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()){
              String result =  userService.deleteUserById(userId);
                return ResponseEntity.ok(result);
            }else {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
