package org.seydaliev.businesscard.service.impl;

import org.seydaliev.businesscard.dto.SkillDTO;
import org.seydaliev.businesscard.model.Skill;
import org.seydaliev.businesscard.repository.SkillRepository;
import org.seydaliev.businesscard.service.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillDTO> findAll() {
        return skillRepository.findAll().stream()
                .map(SkillDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SkillDTO> findAll(Pageable pageable) {
        return skillRepository.findAll(pageable).map(SkillDTO::toDTO);
    }
    @Override
    public List<Skill> findAllByFilter(String filter) {
        List<SkillDTO> skillDTOs = skillRepository.findAllByFilter(filter);
        return skillDTOs.stream()
                .map(SkillDTO::toEntity)
                .collect(Collectors.toList());
    }
    @Override
    public Page<SkillDTO> findAllByFilter(String filter, Pageable pageable) {
        Page<Skill> skillsPage = skillRepository.findAllByFilter(filter, pageable);
        List<SkillDTO> skillDTOs = skillsPage.getContent().stream()
                .map(SkillDTO::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(skillDTOs, pageable, skillsPage.getTotalElements());
    }
    @Override
    public List<Skill> findAllSortedBy(String sortBy) {
        List<SkillDTO> skillDTOS = skillRepository.findAllSortedBy(sortBy);
        return skillRepository.findAllSortedBy(sortBy).stream()
                .map(SkillDTO::toEntity)
                .collect(Collectors.toList());
    }
    @Override
    public Page<SkillDTO> findAllSortedBy(String sortBy, Pageable pageable) {
        return skillRepository.findAllSortedBy(sortBy, pageable);
    }

}
