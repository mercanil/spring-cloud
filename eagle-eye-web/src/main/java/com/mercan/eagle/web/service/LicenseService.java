package com.mercan.eagle.web.service;

import com.mercan.eagle.web.model.License;

import java.util.List;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId);

    List<License> getLicenseByOrganization(String organizationId);

    List<License> getTopLicense();

    void updateLicense(License license);

    void deletePost(String id);
}
