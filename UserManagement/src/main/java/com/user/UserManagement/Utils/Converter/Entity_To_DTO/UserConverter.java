package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.ProfileDTO.ProfileDetailsDTO;
import com.user.UserManagement.DTO.UserDTO.UpdateUserInfoDTO;
import com.user.UserManagement.DTO.UserDTO.UserInfoDTO;
import com.user.UserManagement.DTO.UserDTO.UserRegisterDTO;
import com.user.UserManagement.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserConverter {
    @Autowired ProfileConverter profileConverter;

    public UserRegisterDTO entityToUserRegisterDTO(User user) throws Exception {
         UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
         userRegisterDTO.setUserName(user.getUserName());
         userRegisterDTO.setEmail(user.getEmail());
         userRegisterDTO.setUserMobileNumber(user.getUserMobileNumber());
         userRegisterDTO.setAddress(user.getAddress());
         userRegisterDTO.setTotalFamilyMembers(user.getTotalFamilyMembers());
         userRegisterDTO.setDate(user.getDate());
         userRegisterDTO.setActive(user.isActive());
         List<ProfileDetailsDTO> profileDetailsDTO = profileConverter.ListOfProfileToListOfProfileDetailsDTO(user.getProfile());
         userRegisterDTO.setProfile(profileDetailsDTO.get(0));
        return userRegisterDTO;
    }

    public Optional<UserInfoDTO> convertQueryResultToUserInfoDTO(Optional<Object> optionalResult) {
        if (optionalResult.isEmpty()) {
            return Optional.empty();
        }
        Object[] row = (Object[]) optionalResult.get();
        try {
            String userName = (String) row[0];
            String email = (String) row[1];
            String userMobileNumber = (String) row[2];
            String address = (String) row[3];
            Integer totalFamilyMembers = (Integer) row[4];

            return Optional.of(new UserInfoDTO(userName, email, userMobileNumber, address, totalFamilyMembers));
        } catch (ClassCastException e) {
            System.err.println("Error converting query result: " + e.getMessage());
            return Optional.empty();
        }
    }


    public UpdateUserInfoDTO UpdateUserInfoConverter(User user){
        UpdateUserInfoDTO updateUserInfoDTO = new UpdateUserInfoDTO();
        updateUserInfoDTO.setUserName(user.getUserName());
        updateUserInfoDTO.setPassword(user.getPassword());
        updateUserInfoDTO.setEmail(user.getEmail());
        updateUserInfoDTO.setUserMobileNumber(user.getUserMobileNumber());
        updateUserInfoDTO.setAddress(user.getAddress());
        updateUserInfoDTO.setTotalFamilyMembers(user.getTotalFamilyMembers());
        return updateUserInfoDTO;
    }

}

