package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.ExperienceDTO;
import org.seydaliev.businesscard.service.ExperienceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/experiences")
    public List<ExperienceDTO> getAllExperiences() {
        return experienceService.findAll();
    }

    @GetMapping("/experiences/filter")
    public List<ExperienceDTO> getExperiencesByTitleFilter(@RequestParam String filter) {
        return experienceService.findAllByTitleFilter(filter);
    }

    @GetMapping("/experiences/sorted")
    public Page<ExperienceDTO> getExperiencesSortedByStartDate(@RequestParam String sortBy, Pageable pageable) {
        if ("startDate".equals(sortBy)) {
            return experienceService.findAllSortedByStartDate(pageable);
        } else {
            return experienceService.findAllSortedByStartDate(pageable);
        }
    }

    @GetMapping("/experiences/filter/sorted")
    public Page<ExperienceDTO> getExperiencesByTitleFilterWithSorting(@RequestParam String filter, @RequestParam Pageable pageable) {
        return experienceService.findAllByTitleFilterWithSorting(filter, pageable);
    }
}
