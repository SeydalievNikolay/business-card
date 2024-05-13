package org.seydaliev.businesscard.service;

import org.seydaliev.businesscard.dto.LanguageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LanguageService {
    List<LanguageDTO> findAll();
    Page<LanguageDTO> findAll(Pageable pageable);
    List<LanguageDTO> findAllByFilter(String filter);
    Page<LanguageDTO> findAllByFilter(String filter, Pageable pageable);
    List<LanguageDTO> findAllSortedBy(String sortBy);
    Page<LanguageDTO> findAllSortedBy(String sortBy, Pageable pageable);
}
