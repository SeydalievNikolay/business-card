package org.seydaliev.businesscard.service;

import org.seydaliev.businesscard.dto.ExperienceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExperienceService {

    List<ExperienceDTO> findAllByTitleFilter(String filter);

    Page<ExperienceDTO> findAll(Pageable pageable);

    Page<ExperienceDTO> findAllByTitleFilterWithSorting(String filter, Pageable pageable);

    List<ExperienceDTO> findAll();

    Page<ExperienceDTO> findAllSortedByStartDate(Pageable pageable);
}
