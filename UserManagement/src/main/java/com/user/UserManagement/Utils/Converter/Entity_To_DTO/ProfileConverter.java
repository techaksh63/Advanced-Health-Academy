package com.user.UserManagement.Utils.Converter.Entity_To_DTO;

import com.user.UserManagement.DTO.ProfileInfoDTO;
import com.user.UserManagement.Entity.Profile;
import org.springframework.stereotype.Component;

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

}
