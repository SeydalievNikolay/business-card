package org.seydaliev.businesscard.dto;

import lombok.*;
import org.seydaliev.businesscard.model.Skill;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    private Long id;
    private String name;
    private String level;

    public static SkillDTO toDTO(Skill skill) {
        SkillDTO dto = new SkillDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setLevel(skill.getLevel());
        return dto;
    }

    public static Skill toEntity(SkillDTO dto) {
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setLevel(dto.getLevel());
        return skill;
    }
}
