package org.seydaliev.businesscard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.model.Profile;
import org.seydaliev.businesscard.repository.ProfileRepository;
import org.seydaliev.businesscard.service.impl.ProfileServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    private List<ProfileDTO> profiles;
    private Page<ProfileDTO> profilesPage;

    @BeforeEach
    void setUp() {
        ProfileDTO profile1 = new ProfileDTO();
        profile1.setId(1L);
        profile1.setName("John Doe");
        profile1.setEmail("john.doe@example.com");
        profile1.setAboutMe("Software Developer");
        profile1.setGithubProfile("johndoe");

        ProfileDTO profile2 = new ProfileDTO();
        profile2.setId(2L);
        profile2.setName("Jane Doe");
        profile2.setEmail("jane.doe@example.com");
        profile2.setAboutMe("Data Scientist");
        profile2.setGithubProfile("janedoe");

        profiles = List.of(profile1, profile2);
        profilesPage = new PageImpl<>(profiles);
    }

    @Test
    void findAllWithPageable() {
        Profile profile1 = new Profile();
        profile1.setId(1L);
        profile1.setName("John Doe");
        profile1.setEmail("john.doe@example.com");
        profile1.setAboutMe("Software Developer");
        profile1.setGithubProfile("johndoe");

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setName("Jane Doe");
        profile2.setEmail("jane.doe@example.com");
        profile2.setAboutMe("Data Scientist");
        profile2.setGithubProfile("janedoe");

        List<Profile> profiles = List.of(profile1, profile2);
        Page<Profile> profilesPage = new PageImpl<>(profiles);
        when(profileRepository.findAll(PageRequest.of(0, 10))).thenReturn(profilesPage);

        Page<Profile> result = profileService.findAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getName());
        assertEquals("Jane Doe", result.getContent().get(1).getName());
    }

    @Test
    void findAllByFilter() {
        when(profileRepository.findAllByFilter("Software")).thenReturn(profiles);

        List<ProfileDTO> result = profileService.findAllByFilter("Software");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void findAllByFilterWithPageable() {
        when(profileRepository.findAllByFilter("Software", PageRequest.of(0, 10))).thenReturn(profilesPage);

        Page<ProfileDTO> result = profileService.findAllByFilter("Software", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("John Doe", result.getContent().get(0).getName());
        assertEquals("Jane Doe", result.getContent().get(1).getName());
    }

    @Test
    void findAllSortedBy() {
        when(profileRepository.findAllSortedBy("name")).thenReturn(profiles);

        List<ProfileDTO> result = profileService.findAllSortedBy("name");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void findById() {

        ProfileDTO profile = profiles.get(0);

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setName("John Doe");
        profile2.setEmail("jane.doe@example.com");
        profile2.setAboutMe("Data Scientist");
        profile2.setGithubProfile("johndoe");

        when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile2));

        Optional<ProfileDTO> result = profileService.findById(profile.getId());

        assertTrue(result.isPresent());
        assertEquals(profile.getName(), result.get().getName());
    }

    @Test
    void findAll() {
        Profile profile1 = new Profile();
        profile1.setId(1L);
        profile1.setName("John Doe");
        profile1.setEmail("john.doe@example.com");
        profile1.setAboutMe("Software Developer");
        profile1.setGithubProfile("johndoe");

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setName("Jane Doe");
        profile2.setEmail("jane.doe@example.com");
        profile2.setAboutMe("Data Scientist");
        profile2.setGithubProfile("janedoe");

        List<Profile> allProfiles = List.of(profile1, profile2);

        when(profileRepository.findAll()).thenReturn(allProfiles);

        List<ProfileDTO> result = profileService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    void save() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(3L);
        profileDTO.setName("New User");
        profileDTO.setEmail("newuser@example.com");
        profileDTO.setAboutMe("Newbie in programming");
        profileDTO.setGithubProfile("newuser");

        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> {
            Profile profile = invocation.getArgument(0);
            profile.setId(profileDTO.getId());
            return profile;
        });

        ProfileDTO savedProfileDTO = profileService.save(profileDTO);

        assertNotNull(savedProfileDTO);
        assertEquals(profileDTO.getName(), savedProfileDTO.getName());
        assertEquals(profileDTO.getEmail(), savedProfileDTO.getEmail());
        assertEquals(profileDTO.getAboutMe(), savedProfileDTO.getAboutMe());
        assertEquals(profileDTO.getGithubProfile(), savedProfileDTO.getGithubProfile());
    }
    @Test
    void deleteById() {
        Long id = 1L;
        doNothing().when(profileRepository).deleteById(id);

        profileService.deleteById(id);

        verify(profileRepository, times(1)).deleteById(id);
    }
}
