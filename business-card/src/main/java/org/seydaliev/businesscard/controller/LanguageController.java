package org.seydaliev.businesscard.controller;

import org.seydaliev.businesscard.dto.LanguageDTO;
import org.seydaliev.businesscard.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/languages")
    public List<LanguageDTO> getAllLanguages() {
        return languageService.findAll();
    }

    @GetMapping("/languages/page")
    public Page<LanguageDTO> getAllLanguages(Pageable pageable) {
        return languageService.findAll(pageable);
    }

    @GetMapping("/languages/filter")
    public List<LanguageDTO> getLanguagesByFilter(@RequestParam String filter) {
        return languageService.findAllByFilter(filter);
    }

    @GetMapping("/languages/filter/page")
    public Page<LanguageDTO> getLanguagesByFilter(@RequestParam String filter, Pageable pageable) {
        return languageService.findAllByFilter(filter, pageable);
    }

    @GetMapping("/languages/sorted")
    public List<LanguageDTO> getLanguagesSortedBy(@RequestParam String sortBy) {
        return languageService.findAllSortedBy(sortBy);
    }

    @GetMapping("/languages/sorted/page")
    public Page<LanguageDTO> getLanguagesSortedBy(@RequestParam String sortBy, Pageable pageable) {
        return languageService.findAllSortedBy(sortBy, pageable);
    }

}
