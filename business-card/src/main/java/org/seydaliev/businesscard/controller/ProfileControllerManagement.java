package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles_management")
public class ProfileControllerManagement {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable Long id) {
        Optional<ProfileDTO> profileDTOOptional = profileService.findById(id);
        return profileDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> findAll() {
        List<ProfileDTO> profileDTOs = profileService.findAll();
        return ResponseEntity.ok(profileDTOs);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> save(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO savedProfileDTO = profileService.save(profileDTO);
        return ResponseEntity.ok(savedProfileDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
