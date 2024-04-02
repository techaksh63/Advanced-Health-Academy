//package com.user.UserManagement.Controller;
//
//import com.user.UserManagement.Entity.Profile;
//import com.user.UserManagement.Service.PaymentService;
//import com.user.UserManagement.Service.ProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("")
//public class PaymentController {
//    @Autowired
//    private ProfileService profileService;
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @PostMapping("/add")
//    public ResponseEntity<?> createProfile(@PathVariable long userId, @RequestBody Profile profile) throws Exception {
//        Profile createdDetails = profileService.createProfile(userId, profile, paymentService);
//        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
//    }
//
//}
