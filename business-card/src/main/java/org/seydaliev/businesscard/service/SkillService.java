package org.seydaliev.businesscard.service;

import org.seydaliev.businesscard.dto.SkillDTO;
import org.seydaliev.businesscard.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillService {
    List<SkillDTO> findAll();
    Page<SkillDTO> findAll(Pageable pageable);
    List<Skill> findAllByFilter(String filter);

    Page<SkillDTO> findAllByFilter(String filter, Pageable pageable);

    List<Skill> findAllSortedBy(String sortBy);

    Page<SkillDTO> findAllSortedBy(String sortBy, Pageable pageable);
}
