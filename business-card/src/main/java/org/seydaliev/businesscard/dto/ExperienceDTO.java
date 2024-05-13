package org.seydaliev.businesscard.dto;

import lombok.Getter;
import lombok.Setter;
import org.seydaliev.businesscard.model.Experience;

@Getter
@Setter
public class ExperienceDTO {
    private Long id;
    private String companyName;
    private String position;
    private String startDate;
    private String endDate;
    private String responsibilities;
    private String technologiesUsed;

    public static ExperienceDTO toDTO(Experience experience) {
        ExperienceDTO dto = new ExperienceDTO();
        dto.setId(experience.getId());
        dto.setCompanyName(experience.getCompanyName());
        dto.setPosition(experience.getPosition());
        dto.setStartDate(experience.getStartDate());
        dto.setEndDate(experience.getEndDate());
        dto.setResponsibilities(experience.getResponsibilities());
        dto.setTechnologiesUsed(experience.getTechnologiesUsed());
        return dto;
    }

    public static Experience toEntity(ExperienceDTO dto) {
        Experience experience = new Experience();
        experience.setId(dto.getId());
        experience.setCompanyName(dto.getCompanyName());
        experience.setPosition(dto.getPosition());
        experience.setStartDate(dto.getStartDate());
        experience.setEndDate(dto.getEndDate());
        experience.setResponsibilities(dto.getResponsibilities());
        experience.setTechnologiesUsed(dto.getTechnologiesUsed());
        return experience;
    }
}
