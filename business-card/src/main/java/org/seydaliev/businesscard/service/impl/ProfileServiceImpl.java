package org.seydaliev.businesscard.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.model.Profile;
import org.seydaliev.businesscard.repository.ProfileRepository;
import org.seydaliev.businesscard.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder;

    public ProfileServiceImpl(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public List<ProfileDTO> findAllByFilter(String filter) {
        return profileRepository.findAllByFilter(filter);
    }

    @Override
    public Page<ProfileDTO> findAllByFilter(String filter, Pageable pageable) {
        return profileRepository.findAllByFilter(filter, pageable);
    }

    @Override
    public List<ProfileDTO> findAllSortedBy(String sortBy) {
        return profileRepository.findAllSortedBy(sortBy);
    }

    @Override
    public Page<ProfileDTO> findAllSortedBy(String sortBy, Pageable pageable) {
        return profileRepository.findAllSortedBy(sortBy, pageable);
    }

    @Override
    public Optional<ProfileDTO> findById(Long id) {
        try {
            Optional<Profile> profileOptional = profileRepository.findById(id);
            if (profileOptional.isPresent()) {
                Profile profile = profileOptional.get();

                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setId(profile.getId());
                profileDTO.setName(profile.getName());
                profileDTO.setEmail(profile.getEmail());
                profileDTO.setAboutMe(profile.getAboutMe());
                profileDTO.setGithubProfile(profile.getGithubProfile());

                return Optional.of(profileDTO);
            } else {
                throw new EntityNotFoundException("Profile not found with id: " + id);
            }
        } catch (EntityNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException("Profile not found with id: " + id, e);
        }
    }

    @Override
    public List<ProfileDTO> findAll() {
        List<Profile> profiles = profileRepository.findAll();

        List<ProfileDTO> profileDTOs = profiles.stream()
                .map(profile -> {
                    ProfileDTO profileDTO = new ProfileDTO();
                    profileDTO.setId(profile.getId());
                    profileDTO.setName(profile.getName());
                    profileDTO.setEmail(profile.getEmail());
                    profileDTO.setAboutMe(profile.getAboutMe());
                    profileDTO.setGithubProfile(profile.getGithubProfile());

                    return profileDTO;
                })
                .collect(Collectors.toList());
        return profileDTOs;
    }

    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setId(profileDTO.getId());
        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        profile.setAboutMe(profileDTO.getAboutMe());
        profile.setGithubProfile(profileDTO.getGithubProfile());
        profile.setRole(profileDTO.getRole());

        if (profileDTO.getPassword()!= null) {
            String encodedPassword = passwordEncoder.encode(profileDTO.getPassword());
            profile.setPassword(encodedPassword);
        }

        Profile savedProfile = profileRepository.save(profile);

        ProfileDTO savedProfileDTO = new ProfileDTO();
        savedProfileDTO.setId(savedProfile.getId());
        savedProfileDTO.setName(savedProfile.getName());
        savedProfileDTO.setEmail(savedProfile.getEmail());
        savedProfileDTO.setAboutMe(savedProfile.getAboutMe());
        savedProfileDTO.setGithubProfile(savedProfile.getGithubProfile());
        savedProfileDTO.setRole(savedProfile.getRole());
        return savedProfileDTO;
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

}
