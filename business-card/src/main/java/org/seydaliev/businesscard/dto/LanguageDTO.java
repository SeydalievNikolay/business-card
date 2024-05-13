package org.seydaliev.businesscard.dto;

import lombok.Getter;
import lombok.Setter;
import org.seydaliev.businesscard.model.Language;

@Getter
@Setter
public class LanguageDTO {
    private Long id;
    private String name;
    private String level;
    public static LanguageDTO toDTO(Language language) {
        LanguageDTO dto = new LanguageDTO();
        dto.setId(language.getId());
        dto.setName(language.getName());
        dto.setLevel(language.getLevel());
        return dto;
    }

    public static Language toEntity(LanguageDTO dto) {
        Language language = new Language();
        language.setId(dto.getId());
        language.setName(dto.getName());
        language.setLevel(dto.getLevel());
        return language;
    }
}
