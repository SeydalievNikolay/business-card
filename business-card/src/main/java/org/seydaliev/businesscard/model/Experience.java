package org.seydaliev.businesscard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String position;
    private String startDate;
    private String endDate;
    private String responsibilities;
    private String technologiesUsed;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
