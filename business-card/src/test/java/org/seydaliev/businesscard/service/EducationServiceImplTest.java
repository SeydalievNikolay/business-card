package org.seydaliev.businesscard.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seydaliev.businesscard.dto.EducationDTO;
import org.seydaliev.businesscard.model.Education;
import org.seydaliev.businesscard.repository.EducationRepository;
import org.seydaliev.businesscard.service.impl.EducationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {
    @Mock
    private EducationRepository educationRepository;

    @InjectMocks
    private EducationServiceImpl educationService;

    @Test
    void findAllTest() {
        Education education = Education.builder()
                .id(1L)
                .institution("Institution1")
                .degree("Degree1")
                .startDate("2021-01-01")
                .endDate("2022-01-01")
                .build();
        List<Education> mockEducations = new ArrayList<>();
        mockEducations.add(education);
        when(educationRepository.findAll()).thenReturn(mockEducations);

        List<EducationDTO> result = educationService.findAll();

        assertEquals(1, result.size());
        verify(educationRepository, times(1)).findAll();
    }


    @Test
    void findAllByInstitutionFilterTest() {
        Education education = Education.builder()
                .id(1L)
                .institution("Institution1")
                .degree("Degree1")
                .startDate("2021-01-01")
                .endDate("2022-01-01")
                .build();
        List<Education> mockEducations = new ArrayList<>();
        mockEducations.add(education);

        when(educationRepository.findAllByInstitutionFilter("Institution1")).thenReturn(mockEducations);

        List<EducationDTO> result = educationService.findAllByInstitutionFilter("Institution1");

        assertEquals(1, result.size());
        verify(educationRepository, times(1)).findAllByInstitutionFilter("Institution1");
    }

    @ParameterizedTest
    @MethodSource("providePageable")
    void findAllTest(Pageable pageable) {
        Education education = Education.builder()
                .id(1L)
                .institution("Institution1")
                .degree("Degree1")
                .startDate("2021-01-01")
                .endDate("2022-01-01")
                .build();
        Page<Education> mockEducationsPage = new PageImpl<>(List.of(education));
        when(educationRepository.findAll(pageable)).thenReturn(mockEducationsPage);

        Page<EducationDTO> result = educationService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        verify(educationRepository, times(1)).findAll(pageable);
    }

    @ParameterizedTest
    @MethodSource("providePageable")
    void findAllSortedByDegreeTest(Pageable pageable) {
        Education education = Education.builder()
                .id(1L)
                .institution("Institution1")
                .degree("Degree1")
                .startDate("2021-01-01")
                .endDate("2022-01-01")
                .build();
        Page<Education> mockEducationsPage = new PageImpl<>(List.of(education));
        when(educationRepository.findAllSortedByDegree(pageable)).thenReturn(mockEducationsPage);

        Page<EducationDTO> result = educationService.findAllSortedByDegree("degree", pageable);

        assertEquals(1, result.getTotalElements());
        verify(educationRepository, times(1)).findAllSortedByDegree(pageable);
    }

    static Stream<Pageable> providePageable() {
        return Stream.of(
                PageRequest.of(0, 10),
                PageRequest.of(1, 10)
        );
    }
}
