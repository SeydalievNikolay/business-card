package org.seydaliev.businesscard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seydaliev.businesscard.dto.ExperienceDTO;
import org.seydaliev.businesscard.model.Experience;
import org.seydaliev.businesscard.repository.ExperienceRepository;
import org.seydaliev.businesscard.service.impl.ExperienceServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExperienceServiceTest {
    @Mock
    private ExperienceRepository experienceRepository;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Test
    void findAllByTitleFilter() {
        String filter = "Software Engineer";
        List<Experience> experiences = new ArrayList<>();

        when(experienceRepository.findAllByTitleFilter(filter)).thenReturn(experiences);

        List<ExperienceDTO> result = experienceService.findAllByTitleFilter(filter);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findAll() {
        List<Experience> experiences = new ArrayList<>();

        when(experienceRepository.findAll()).thenReturn(experiences);

        List<ExperienceDTO> result = experienceService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("providePageable")
    void findAll(Pageable pageable) {
        Page<Experience> experiencesPage = new PageImpl<>(new ArrayList<>());

        when(experienceRepository.findAll(pageable)).thenReturn(experiencesPage);

        Page<ExperienceDTO> result = experienceService.findAll(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideFilterAndPageable")
    void findAllByTitleFilterWithSorting(String filter, Pageable pageable) {
        Page<Experience> experiencesPage = new PageImpl<>(new ArrayList<>());

        when(experienceRepository.findAllByTitleFilterWithSorting(filter, pageable)).thenReturn(experiencesPage);

        Page<ExperienceDTO> result = experienceService.findAllByTitleFilterWithSorting(filter, pageable);


        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("providePageable")
    void findAllSortedByStartDate(Pageable pageable) {
        Page<Experience> experiencesPage = new PageImpl<>(new ArrayList<>());

        when(experienceRepository.findAllSortedByStartDate(pageable)).thenReturn(experiencesPage);

        Page<ExperienceDTO> result = experienceService.findAllSortedByStartDate(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    public static Stream<Arguments> provideFilterAndPageable() {
        return Stream.of(
                Arguments.of("Software Engineer", PageRequest.of(0,10),
                        Arguments.of("Data Scientist", PageRequest.of(0, 10))
        ));
    }
    public static Stream<Pageable> providePageable() {
        return Stream.of(
                PageRequest.of(0, 10),
                PageRequest.of(1, 10)
        );
    }
}
