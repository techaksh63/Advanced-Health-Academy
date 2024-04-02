package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.Entity.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProfileConverter {
    public ProfileInfoDTO Entity_to_ProfileDetailsDTO(Profile profile) throws Exception{
        ProfileInfoDTO profileInfoDTO = new ProfileInfoDTO();
        profileInfoDTO.setFullName(profile.getFullName());
        profileInfoDTO.setRelationship(profile.getRelationship());
        profileInfoDTO.setGender(profile.getGender());
        return profileInfoDTO;
    }

    public Optional<List<ProfileInfoDTO>> entitiesToProfileInfoDTOs(List<Profile> profiles) {
        if (profiles == null || profiles.isEmpty()) {
            return Optional.empty();
        }

        List<ProfileInfoDTO> profileInfoDTOs = profiles.stream()
                .map(profile -> {
                    try {
                        return Entity_to_ProfileDetailsDTO(profile);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return Optional.of(profileInfoDTOs);
    }



//    public Optional<List<ProfileInfoDTO>> convertQueryResultToProfileInfoDTOs(Optional<Object> optionalResult) {
//        if (optionalResult.isEmpty()) {
//            return Optional.empty();
//        }
//        List<Object[]> resultList = (List<Object[]>) optionalResult.get();
//        if (resultList == null || resultList.isEmpty()) {
//            return Optional.empty();
//        }
//        List<ProfileInfoDTO> profileInfoDTOs = resultList.stream()
//                .map(row -> {
//                    String fullName = (String) row[0];
//                    String relationship = (String) row[1];
//                    String gender = (String) row[2];
//                    return new ProfileInfoDTO(fullName, relationship, gender);
//                })
//                .collect(Collectors.toList());
//        return Optional.of(profileInfoDTOs);
//    }

    public List<ProfileInfoDTO> convertQueryResultToProfileInfoDTOs(List<Object> resultList) {
        if (resultList == null || resultList.isEmpty()) {
            return Collections.emptyList(); // Return an empty list instead of null
        }

        List<ProfileInfoDTO> profileInfoDTOs = resultList.stream()
                .map(row -> {
                    Object[] rowArray = (Object[]) row; // Cast to Object[]
                    // Assuming row order matches query result
                    String fullName = (String) rowArray[0];
                    String relationship = (String) rowArray[1];
                    String gender = (String) rowArray[2];
                    return new ProfileInfoDTO(fullName, relationship, gender);
                })
                .collect(Collectors.toList());

        return profileInfoDTOs;
    }








}
