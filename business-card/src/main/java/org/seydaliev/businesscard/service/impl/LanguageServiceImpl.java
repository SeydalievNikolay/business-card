package org.seydaliev.businesscard.service.impl;

import org.seydaliev.businesscard.dto.LanguageDTO;
import org.seydaliev.businesscard.repository.LanguageRepository;
import org.seydaliev.businesscard.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
    @Override
    public List<LanguageDTO> findAll() {
        return languageRepository.findAll().stream()
                .map(LanguageDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LanguageDTO> findAll(Pageable pageable) {
        return languageRepository.findAll(pageable).map(LanguageDTO::toDTO);
    }

    @Override
    public List<LanguageDTO> findAllByFilter(String filter) {
        return languageRepository.findAllByFilter(filter).stream()
                .map(LanguageDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LanguageDTO> findAllByFilter(String filter, Pageable pageable) {
        return languageRepository.findAllByFilter(filter, pageable).map(LanguageDTO::toDTO);
    }

    @Override
    public List<LanguageDTO> findAllSortedBy(String sortBy) {
        return languageRepository.findAllSortedBy(sortBy).stream()
                .map(LanguageDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LanguageDTO> findAllSortedBy(String sortBy, Pageable pageable) {
        return languageRepository.findAllSortedBy(sortBy, pageable).map(LanguageDTO::toDTO);
    }
}
