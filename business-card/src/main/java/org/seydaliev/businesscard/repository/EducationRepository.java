package org.seydaliev.businesscard.repository;

import org.seydaliev.businesscard.model.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query("SELECT e FROM Education e")
    List<Education> findAll();

    @Query("SELECT e FROM Education e WHERE e.institution LIKE %:filter%")
    List<Education> findAllByInstitutionFilter(@Param("filter") String filter);
    @Query("SELECT e FROM Education e ORDER BY e.degree")
    Page<Education> findAllSortedByDegree(Pageable pageable);

    @Query("SELECT e FROM Education e WHERE e.institution LIKE %:filter% ORDER BY e.degree")
    Page<Education> findAllByInstitutionFilterWithSorting(@Param("filter") String filter, Pageable pageable);
}
