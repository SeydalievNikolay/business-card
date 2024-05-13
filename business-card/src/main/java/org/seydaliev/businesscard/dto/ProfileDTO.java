package org.seydaliev.businesscard.dto;

import lombok.Getter;
import lombok.Setter;
import org.seydaliev.businesscard.model.Profile;
import org.seydaliev.businesscard.model.Role;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfileDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String aboutMe;
    private String githubProfile;
    private Role role;

    public static ProfileDTO toDTO(Profile profile) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setPassword(profile.getPassword());
        dto.setAboutMe(profile.getAboutMe());
        dto.setGithubProfile(profile.getGithubProfile());
        dto.setRole(profile.getRole());
        return dto;
    }

    public static Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword());
        profile.setAboutMe(dto.getAboutMe());
        profile.setGithubProfile(dto.getGithubProfile());
        profile.setRole(dto.getRole());
        return profile;
    }

    public static List<ProfileDTO> toDTOList(List<Profile> profiles) {
        return profiles.stream()
                .map(ProfileDTO::toDTO)
                .collect(Collectors.toList());
    }
    public static List<Profile> toEntityList(List<ProfileDTO> dtos) {
        return dtos.stream()
                .map(ProfileDTO::toEntity)
                .collect(Collectors.toList());
    }
}
