package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.model.Profile;
import org.seydaliev.businesscard.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public Page<Profile> getAllProfiles(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return profileService.findAll(pageable);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProfileDTO>> getAllProfilesByFilter(@RequestParam String filter) {
        try {
            List<ProfileDTO> profiles = profileService.findAllByFilter(filter);
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<List<ProfileDTO>> getAllProfilesSortedBy(@RequestParam String sortBy) {
        try {
            List<ProfileDTO> profiles = profileService.findAllSortedBy(sortBy);
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
