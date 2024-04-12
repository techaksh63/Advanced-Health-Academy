package com.user.UserManagement.Utils.Converter.DTO_To_Entity;

import com.user.UserManagement.DTO.ProfileDTO.ProfileDTO;
import com.user.UserManagement.Entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTOConverter {
    public Profile convertDtoToProfile(ProfileDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Profile(
                null,
                null,
                dto.getFullName(),
                dto.getRelationship(),
                dto.getGender(),
                dto.getBirthDate(),
                dto.getBloodGroup(),
                dto.getDiabetesStatus(),
                dto.getBloodPressureStatus(),
                dto.getCurrentDisease(),
                dto.getPreviousSurgeries(),
                dto.getPreviouslyCuredDiseases(),
                dto.getHeight(),
                dto.getWeight(),
                false
        );
    }
}
