package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.SkillDTO;
import org.seydaliev.businesscard.service.SkillService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<SkillDTO>> getAllSkillsByFilter(@RequestParam String filter, Pageable pageable) {
        try {
            Page<SkillDTO> skillsPage = skillService.findAllByFilter(filter, pageable);
            return new ResponseEntity<>(skillsPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<Page<SkillDTO>> getAllSkillsSortedBy(@RequestParam String sortBy, Pageable pageable) {
        try {
            Page<SkillDTO> skillsPage = skillService.findAllSortedBy(sortBy, pageable);
            return new ResponseEntity<>(skillsPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
