package com.mercan.eagle.web.service;

import com.mercan.eagle.web.exception.LicenseNotFoundException;
import com.mercan.eagle.web.model.License;

import java.util.List;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId) throws LicenseNotFoundException;

    List<License> getLicenseByOrganization(String organizationId);


    void saveOrUpdateLicense(License license);

    void deletePost(String id);

}
