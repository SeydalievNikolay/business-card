package org.seydaliev.businesscard.service;

import org.seydaliev.businesscard.dto.EducationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducationService {
    List<EducationDTO> findAll();

    List<EducationDTO> findAllByInstitutionFilter(String filter);

    Page<EducationDTO> findAll(Pageable pageable);


    Page<EducationDTO> findAllSortedByDegree(String sortBy, Pageable pageable);

    Page<EducationDTO> findAllByInstitutionFilterWithSorting(String filter, String sortBy, Pageable pageable);
}
