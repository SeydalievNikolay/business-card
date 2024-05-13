package org.seydaliev.businesscard.service;

import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Optional<ProfileDTO> findById(Long id);

    List<ProfileDTO> findAll();
    Page<Profile> findAll(Pageable pageable);
    List<ProfileDTO> findAllByFilter(String filter);
    Page<ProfileDTO> findAllByFilter(String filter, Pageable pageable);
    List<ProfileDTO> findAllSortedBy(String sortBy);
    Page<ProfileDTO> findAllSortedBy(String sortBy, Pageable pageable);

    ProfileDTO save(ProfileDTO profileDTO);

    void deleteById(Long id);
}
