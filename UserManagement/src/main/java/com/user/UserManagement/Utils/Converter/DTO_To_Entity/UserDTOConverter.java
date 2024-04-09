package com.user.UserManagement.Utils.Converter.DTO_To_Entity;

import com.user.UserManagement.DTO.ProfileDTO.ProfileDTO;
import com.user.UserManagement.DTO.UserDTO.UserDTO;
import com.user.UserManagement.Entity.Profile;
import com.user.UserManagement.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserDTOConverter {
    @Autowired
    private ProfileDTOConverter profileDTOConverter;
    public User DTOToEntity(UserDTO userDTO){
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setUserMobileNumber(userDTO.getUserMobileNumber());
        user.setAddress(userDTO.getAddress());
        user.setTotalFamilyMembers(userDTO.getTotalFamilyMembers());
        Profile profile = profileDTOConverter.convertDtoToProfile(userDTO.getProfile());
        user.setProfile(Collections.singletonList(profile));
        return user;
    }
}
