package com.mercan.eagle.web.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class License {
    private  String id;
    private  String productName;
    private  String licenseType;
    private  String organizationId;
}
