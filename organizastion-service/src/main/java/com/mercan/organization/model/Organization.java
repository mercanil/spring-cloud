package com.mercan.organization.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "organizations", schema = "public")
public class Organization implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

}
