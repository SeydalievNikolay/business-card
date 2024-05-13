package org.seydaliev.businesscard.repository;

import org.seydaliev.businesscard.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("SELECT l FROM Language l WHERE l.name LIKE %:filter%")
    List<Language> findAllByFilter(@Param("filter") String filter);

    @Query("SELECT l FROM Language l WHERE l.name LIKE %:filter%")
    Page<Language> findAllByFilter(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT l FROM Language l ORDER BY l.name")
    List<Language> findAllSortedBy(@Param("sortBy") String sortBy);

    @Query("SELECT l FROM Language l ORDER BY l.name")
    Page<Language> findAllSortedBy(@Param("sortBy") String sortBy, Pageable pageable);

}
