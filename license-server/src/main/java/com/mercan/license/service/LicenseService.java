package com.mercan.license.service;

import com.mercan.license.model.Organization;
import com.mercan.license.exception.LicenseNotFoundException;
import com.mercan.license.model.License;

import java.util.List;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId,String clientType) throws LicenseNotFoundException;

    List<License> getLicenseByOrganization(String organizationId);


    void saveOrUpdateLicense(License license);

    void deletePost(String id);

    Organization retriveOrgInfo(String organizationId, String clientType);
}
