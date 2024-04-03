package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.user.UserManagement.DTO.UpdateUserInfoDTO;
import com.user.UserManagement.DTO.UserDTO;
import com.user.UserManagement.DTO.UserInfoDTO;
import com.user.UserManagement.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//
    public UserInfoDTO entity_to_InfoDTO(User user) throws Exception{
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName(user.getUserName());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setUserMobileNumber(user.getUserMobileNumber());
        userInfoDTO.setAddress(user.getAddress());
        userInfoDTO.setTotalFamilyMembers(user.getTotalFamilyMembers());
        return userInfoDTO;
    }

//public Optional<UserInfoDTO> entityToInfoDTO(Optional<User> optionalUser) throws Exception {
//    if (optionalUser.isPresent()) {
//        User user = optionalUser.get();
//        UserInfoDTO userInfoDTO = new UserInfoDTO();
//        userInfoDTO.setUserName(user.getUserName());
//        userInfoDTO.setEmail(user.getEmail());
//        userInfoDTO.setUserMobileNumber(user.getUserMobileNumber());
//        userInfoDTO.setAddress(user.getAddress());
//        userInfoDTO.setTotalFamilyMembers(user.getTotalFamilyMembers());
//        return Optional.of(userInfoDTO);
//    } else {
//        return Optional.empty();
//    }
// }




//    public Optional<List<UserInfoDTO>> convertQueryResultToUserInfoDTOs(List<Object[]> resultList) {
//        if (resultList == null || resultList.isEmpty()) {
//            return Optional.empty();
//        }
//
//        List<UserInfoDTO> userInfoDTOs = resultList.stream()
//                .map(row -> {
//                    // Assuming row order matches query result
//                    String userName = (String) row[0];
//                    String email = (String) row[1];
//                    String userMobileNumber = (String) row[2];
//                    String address = (String) row[3];
//                    int totalFamilyMembers = (Integer) row[4];
//                    return new UserInfoDTO(userName, email, userMobileNumber, address, totalFamilyMembers);
//                })
//                .collect(Collectors.toList());
//
//        return Optional.of(userInfoDTOs);
//    }

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

