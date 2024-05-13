package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.EducationDTO;
import org.seydaliev.businesscard.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationController {
    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }


    @GetMapping("/educations")
    public List<EducationDTO> getAllEducations() {
        return educationService.findAll();
    }

    @GetMapping("/educations/filter")
    public List<EducationDTO> getEducationsByInstitutionFilter(@RequestParam String filter) {
        return educationService.findAllByInstitutionFilter(filter);
    }

    @GetMapping("/educations/sorted")
    public Page<EducationDTO> getEducationsSortedByDegree(@RequestParam String sortBy, Pageable pageable) {
        return educationService.findAllSortedByDegree(sortBy, pageable);
    }

    @GetMapping("/educations/filter/sorted")
    public Page<EducationDTO> getEducationsByInstitutionFilterWithSorting(@RequestParam String filter, @RequestParam String sortBy, Pageable pageable) {
        return educationService.findAllByInstitutionFilterWithSorting(filter, sortBy, pageable);
    }
}
