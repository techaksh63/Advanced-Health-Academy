package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.ProfileDetailsDTO;
import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.DTO.UpdateProfileInfoDTO;
import com.user.UserManagement.Entity.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
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
            return Collections.emptyList();
        }

        List<ProfileInfoDTO> profileInfoDTOs = resultList.stream()
                .map(row -> {
                    Object[] rowArray = (Object[]) row;
                    String fullName = (String) rowArray[0];
                    String relationship = (String) rowArray[1];
                    String gender = (String) rowArray[2];
                    boolean isActive = (boolean) rowArray[3];
                    return new ProfileInfoDTO(fullName, relationship, gender, isActive);
                })
                .collect(Collectors.toList());

        return profileInfoDTOs;
    }

    public Optional<ProfileDetailsDTO> convertProfileDetailsToDTO(Optional<Object> optionalProfileInfo) {
        if (!optionalProfileInfo.isPresent()) {
            return Optional.empty();
        }
        Object[] profileInfoArray = (Object[]) optionalProfileInfo.get();

        Long userId = (Long) profileInfoArray[0];
        String fullName = (String) profileInfoArray[1];
        String relationship = (String) profileInfoArray[2];
        String gender = (String) profileInfoArray[3];
        System.out.println(profileInfoArray[4]);
        java.sql.Date sqlDate = (Date) profileInfoArray[4];
        LocalDate birthDate = LocalDate.of(sqlDate.getYear() + 1900, sqlDate.getMonth() + 1, sqlDate.getDate());
        String bloodGroup = (String) profileInfoArray[5];
        String diabetesStatus = (String) profileInfoArray[6];
        String bloodPressureStatus = (String) profileInfoArray[7];
        String currentDisease = (String) profileInfoArray[8];
        String previousSurgeries = (String) profileInfoArray[9];
        String previouslyCuredDiseases = (String) profileInfoArray[10];
        Double height = (Double) profileInfoArray[11];
        Double weight = (Double) profileInfoArray[12];
        boolean isActive = (Boolean) profileInfoArray[13];

        return Optional.of(new ProfileDetailsDTO(userId, fullName, relationship, gender, birthDate, bloodGroup,
                diabetesStatus, bloodPressureStatus, currentDisease, previousSurgeries, previouslyCuredDiseases,
                height, weight, isActive));
    }

public UpdateProfileInfoDTO UpdateProfileInfoConverter(Profile profile){
        UpdateProfileInfoDTO updateProfileInfoDTO = new UpdateProfileInfoDTO();
        updateProfileInfoDTO.setFullName(profile.getFullName());
        updateProfileInfoDTO.setRelationship(profile.getRelationship());
        updateProfileInfoDTO.setGender(profile.getGender());
        updateProfileInfoDTO.setBirthDate(profile.getBirthDate());
        updateProfileInfoDTO.setBloodGroup(profile.getBloodGroup());
        updateProfileInfoDTO.setDiabetesStatus(profile.getDiabetesStatus());
        updateProfileInfoDTO.setBloodPressureStatus(profile.getBloodPressureStatus());
        updateProfileInfoDTO.setCurrentDisease(profile.getCurrentDisease());
        updateProfileInfoDTO.setPreviousSurgeries(profile.getPreviousSurgeries());
        updateProfileInfoDTO.setPreviouslyCuredDiseases(profile.getPreviouslyCuredDiseases());
        updateProfileInfoDTO.setHeight(profile.getHeight());
        updateProfileInfoDTO.setWeight(profile.getWeight());
        return updateProfileInfoDTO;
}



}
