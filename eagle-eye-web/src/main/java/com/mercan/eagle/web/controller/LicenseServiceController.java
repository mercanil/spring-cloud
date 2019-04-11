package com.mercan.eagle.web.controller;


import com.mercan.eagle.web.model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@RequiredArgsConstructor
public class LicenseServiceController {

    @RequestMapping(value = "{licenseId}", method = RequestMethod.GET)
    public License getLicense(@PathVariable(value = "organizationId") String organizationId,
                              @PathVariable(value = "licenseId") String licenseId) {

        return License.builder()
                .id(licenseId)
                .licenseType("Seat")
                .productName("Teleco")
                .organizationId(organizationId)
                .build();
    }
}
