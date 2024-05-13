package org.seydaliev.businesscard.service.impl;

import org.seydaliev.businesscard.dto.ExperienceDTO;
import org.seydaliev.businesscard.model.Experience;
import org.seydaliev.businesscard.repository.ExperienceRepository;
import org.seydaliev.businesscard.service.ExperienceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<ExperienceDTO> findAllByTitleFilter(String filter) {
        List<Experience> experiences = experienceRepository.findAllByTitleFilter(filter);
        return experiences.stream()
                .map(ExperienceDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExperienceDTO> findAll() {
        List<Experience> experiences = experienceRepository.findAll();
        return experiences.stream()
                .map(ExperienceDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ExperienceDTO> findAll(Pageable pageable) {
        Page<Experience> experiencesPage = experienceRepository.findAll(pageable);
        return experiencesPage.map(ExperienceDTO::toDTO);
    }

    @Override
    public Page<ExperienceDTO> findAllByTitleFilterWithSorting(String filter, Pageable pageable) {
        Page<Experience> experiencesPage = experienceRepository.findAllByTitleFilterWithSorting(filter, pageable);
        return experiencesPage.map(ExperienceDTO::toDTO);
    }

    @Override
    public Page<ExperienceDTO> findAllSortedByStartDate(Pageable pageable) {
        Page<Experience> experiencesPage = experienceRepository.findAllSortedByStartDate(pageable);
        return experiencesPage.map(ExperienceDTO::toDTO);
    }
}
