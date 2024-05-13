package org.seydaliev.businesscard.dto;

import lombok.Getter;
import lombok.Setter;
import org.seydaliev.businesscard.model.Education;

@Getter
@Setter
public class EducationDTO {
    private Long id;
    private String institution;
    private String degree;
    private String startDate;
    private String endDate;

    public static EducationDTO toDTO(Education education) {
        EducationDTO dto = new EducationDTO();
        dto.setId(education.getId());
        dto.setInstitution(education.getInstitution());
        dto.setDegree(education.getDegree());
        dto.setStartDate(education.getStartDate());
        dto.setEndDate(education.getEndDate());
        return dto;
    }

    public static Education toEntity(EducationDTO dto) {
        Education education = new Education();
        education.setId(dto.getId());
        education.setInstitution(dto.getInstitution());
        education.setDegree(dto.getDegree());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        return education;
    }
}
