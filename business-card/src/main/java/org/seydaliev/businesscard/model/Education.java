package org.seydaliev.businesscard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String institution;
    private String degree;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
