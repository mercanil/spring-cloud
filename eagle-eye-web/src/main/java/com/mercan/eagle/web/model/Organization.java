package com.mercan.eagle.web.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "organizations", schema = "public")
public class Organization {

    @Id
    @Column(name = "id", nullable = false)
    private String licenseId;

    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}
