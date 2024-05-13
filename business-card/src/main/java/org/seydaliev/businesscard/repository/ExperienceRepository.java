package org.seydaliev.businesscard.repository;

import org.seydaliev.businesscard.model.Experience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("SELECT e FROM Experience e")
    Page<Experience> findAll(Pageable pageable);

    @Query("SELECT e FROM Experience e")
    List<Experience> findAll();

    @Query("SELECT e FROM Experience e WHERE e.companyName LIKE %:filter%")
    List<Experience> findAllByTitleFilter(@Param("filter") String filter);

    @Query("SELECT e FROM Experience e WHERE e.companyName LIKE %:filter% ORDER BY e.position")
    Page<Experience> findAllByTitleFilterWithSorting(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT e FROM Experience e ORDER BY e.startDate")
    Page<Experience> findAllSortedByStartDate(Pageable pageable);
}
