package org.seydaliev.businesscard.repository;

import org.seydaliev.businesscard.dto.SkillDTO;
import org.seydaliev.businesscard.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("SELECT s FROM Skill s WHERE s.name LIKE %:filter%")
    List<SkillDTO> findAllByFilter(@Param("filter") String filter);

    @Query("SELECT s FROM Skill s WHERE s.name LIKE %:filter%")
    Page<Skill> findAllByFilter(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT s FROM Skill s ORDER BY s.name")
    List<SkillDTO> findAllSortedBy(@Param("sortBy") String sortBy);

    @Query("SELECT s FROM Skill s ORDER BY s.name")
    Page<SkillDTO> findAllSortedBy(@Param("sortBy") String sortBy, Pageable pageable);
}
