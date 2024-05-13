package org.seydaliev.businesscard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seydaliev.businesscard.dto.SkillDTO;
import org.seydaliev.businesscard.model.Skill;
import org.seydaliev.businesscard.repository.SkillRepository;
import org.seydaliev.businesscard.service.impl.SkillServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {


    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    private List<Skill> skills;
    private List<SkillDTO> skillDTOs;

    @BeforeEach
    void setUp() {
        skills = List.of(
                Skill.builder().id(1L).name("Java").level("Expert").build(),
                Skill.builder().id(2L).name("Python").level("Intermediate").build()
        );

        skillDTOs = List.of(
                SkillDTO.builder().id(1L).name("Java").level("Expert").build(),
                SkillDTO.builder().id(2L).name("Python").level("Intermediate").build()
        );
    }

    @Test
    void findAll() {
        when(skillRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(skills));

        Page<SkillDTO> result = skillService.findAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Java", result.getContent().get(0).getName());
        assertEquals("Python", result.getContent().get(1).getName());
    }

    @Test
    void findAllByFilter() {
        when(skillRepository.findAllByFilter("Java")).thenReturn(skillDTOs);

        List<Skill> result = skillService.findAllByFilter("Java");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Python", result.get(1).getName());
    }

    @Test
    void findAllByFilterWithPageable() {
        when(skillRepository.findAllByFilter("Java", PageRequest.of(0, 10))).thenReturn(new PageImpl<>(skills));

        Page<SkillDTO> result = skillService.findAllByFilter("Java", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Java", result.getContent().get(0).getName());
        assertEquals("Python", result.getContent().get(1).getName());
    }

    @Test
    void findAllSortedBy() {
        when(skillRepository.findAllSortedBy("name")).thenReturn(skillDTOs);

        List<Skill> result = skillService.findAllSortedBy("name");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Python", result.get(1).getName());
    }

    @Test
    void findAllSortedByWithPageable() {
        when(skillRepository.findAllSortedBy("name", PageRequest.of(0, 10))).thenReturn(new PageImpl<>(skillDTOs));

        Page<SkillDTO> result = skillService.findAllSortedBy("name", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("Java", result.getContent().get(0).getName());
        assertEquals("Python", result.getContent().get(1).getName());
    }
}
