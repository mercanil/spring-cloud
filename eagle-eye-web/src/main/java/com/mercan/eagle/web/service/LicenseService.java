package com.mercan.eagle.web.service;

import com.mercan.eagle.web.exception.LicenseNotFoundException;
import com.mercan.eagle.web.model.License;
import com.mercan.eagle.web.model.Organization;

import java.util.List;

public interface LicenseService {
    License getLicense(String organizationId, String licenseId,String clientType) throws LicenseNotFoundException;

    List<License> getLicenseByOrganization(String organizationId);


    void saveOrUpdateLicense(License license);

    void deletePost(String id);

    Organization retriveOrgInfo(String organizationId, String clientType);
}
