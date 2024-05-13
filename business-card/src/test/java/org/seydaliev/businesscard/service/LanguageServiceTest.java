package org.seydaliev.businesscard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seydaliev.businesscard.dto.LanguageDTO;
import org.seydaliev.businesscard.model.Language;
import org.seydaliev.businesscard.repository.LanguageRepository;
import org.seydaliev.businesscard.service.impl.LanguageServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    @Test
    void findAll() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);

        when(languageRepository.findAll()).thenReturn(languagesList);

        List<LanguageDTO> result = languageService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getName());
        assertEquals("Spanish", result.get(1).getName());
    }

    @Test
    void findAllWithPageable() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);
        Page<Language> languagesPage = new PageImpl<>(languagesList);
        when(languageRepository.findAll(PageRequest.of(0, 10))).thenReturn(languagesPage);

        Page<LanguageDTO> result = languageService.findAll(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("English", result.getContent().get(0).getName());
        assertEquals("Spanish", result.getContent().get(1).getName());
    }

    @Test
    void findAllByFilter() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);
        when(languageRepository.findAllByFilter("A")).thenReturn(languagesList);

        List<LanguageDTO> result = languageService.findAllByFilter("A");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getName());
        assertEquals("Spanish", result.get(1).getName());
    }

    @Test
    void findAllByFilterWithPageable() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);
        Page<Language> languagesPage = new PageImpl<>(languagesList);
        when(languageRepository.findAllByFilter("A", PageRequest.of(0, 10))).thenReturn(languagesPage);

        Page<LanguageDTO> result = languageService.findAllByFilter("A", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("English", result.getContent().get(0).getName());
        assertEquals("Spanish", result.getContent().get(1).getName());
    }

    @Test
    void findAllSortedBy() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);
        when(languageRepository.findAllSortedBy("name")).thenReturn(languagesList);

        List<LanguageDTO> result = languageService.findAllSortedBy("name");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getName());
        assertEquals("Spanish", result.get(1).getName());
    }

    @Test
    void findAllSortedByWithPageable() {
        Language language1 = new Language();
        language1.setId(1L);
        language1.setName("English");
        language1.setLevel("A1");

        Language language2 = new Language();
        language2.setId(2L);
        language2.setName("Spanish");
        language2.setLevel("A2");

        List<Language> languagesList = List.of(language1, language2);
        Page<Language> languagesPage = new PageImpl<>(languagesList);
        when(languageRepository.findAllSortedBy("name", PageRequest.of(0, 10))).thenReturn(languagesPage);

        Page<LanguageDTO> result = languageService.findAllSortedBy("name", PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals("English", result.getContent().get(0).getName());
        assertEquals("Spanish", result.getContent().get(1).getName());
    }
}
