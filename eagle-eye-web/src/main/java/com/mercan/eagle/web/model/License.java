package com.mercan.eagle.web.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "licenses", schema = "public")
@Wither
public class License implements Serializable {
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

    @Column
    private String organizationName;
    @Column
    private String contactName;
    @Column
    private String contactEmail;
    @Column
    private String contactPhone;



}
