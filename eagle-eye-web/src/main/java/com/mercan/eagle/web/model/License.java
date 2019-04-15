package com.mercan.eagle.web.model;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "licenses",schema = "public")
public class License {
    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;

    @Column(nullable = false)
    private String productName;
    private String licenseType;


    @Column(nullable = false)
    private String organizationId;

    @Transient
    private String comment;
}
