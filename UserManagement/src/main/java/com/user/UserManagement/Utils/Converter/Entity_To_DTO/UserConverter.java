package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.UserDTO;
import com.user.UserManagement.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO entity_to_dto(User user) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserMobileNumber(user.getUserMobileNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setTotalFamilyMembers(user.getTotalFamilyMembers());
        userDTO.setDate(user.getDate());
        userDTO.setProfile(user.getProfile());
        return userDTO;
    }
}

