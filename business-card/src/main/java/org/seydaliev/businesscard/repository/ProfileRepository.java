package org.seydaliev.businesscard.repository;

import org.seydaliev.businesscard.dto.ProfileDTO;
import org.seydaliev.businesscard.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.name LIKE %:filter%")
    List<ProfileDTO> findAllByFilter(@Param("filter") String filter);

    @Query("SELECT p FROM Profile p WHERE p.name LIKE %:filter%")
    Page<ProfileDTO> findAllByFilter(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT p FROM Profile p ORDER BY p.name")
    List<ProfileDTO> findAllSortedBy(@Param("sortBy") String sortBy);

    @Query("SELECT p FROM Profile p ORDER BY p.name")
    Page<ProfileDTO> findAllSortedBy(@Param("sortBy") String sortBy, Pageable pageable);

    @Query(value = "SELECT * FROM profiles WHERE username = :username", nativeQuery = true)
    Optional<Profile> findByUsername(@Param("username") String username);
}
