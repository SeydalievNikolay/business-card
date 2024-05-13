package org.seydaliev.businesscard.service.impl;

import org.seydaliev.businesscard.dto.EducationDTO;
import org.seydaliev.businesscard.model.Education;
import org.seydaliev.businesscard.repository.EducationRepository;
import org.seydaliev.businesscard.service.EducationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }


    @Override
    public List<EducationDTO> findAll() {
        List<Education> educations = educationRepository.findAll();
        return educations.stream()
                .map(EducationDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationDTO> findAllByInstitutionFilter(String filter) {
        List<Education> educations = educationRepository.findAllByInstitutionFilter(filter);
        return educations.stream()
                .map(EducationDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EducationDTO> findAll(Pageable pageable) {
        Page<Education> educationsPage = educationRepository.findAll(pageable);
        return educationsPage.map(EducationDTO::toDTO);
    }

    @Override
    public Page<EducationDTO> findAllSortedByDegree(String sortBy, Pageable pageable) {
        Page<Education> educationsPage = educationRepository.findAllSortedByDegree(pageable);
        return educationsPage.map(EducationDTO::toDTO);
    }

    @Override
    public Page<EducationDTO> findAllByInstitutionFilterWithSorting(String filter, String sortBy, Pageable pageable) {
        Page<Education> educationsPage = educationRepository.findAllByInstitutionFilterWithSorting(filter, pageable);
        return educationsPage.map(EducationDTO::toDTO);
    }
}
