package com.mercan.eagle.web.service;

import com.mercan.eagle.web.config.ServiceConfig;
import com.mercan.eagle.web.model.License;
import com.mercan.eagle.web.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {


    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;


    @Override
    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        license.setComment(serviceConfig.getTracer());
        return license;
    }

    @Override
    public List<License> getLicenseByOrganization(String organizationId) {
        List<License> orgList = licenseRepository.findByOrganizationId(organizationId);
        orgList.forEach(org -> org.setComment(serviceConfig.getTracer()));
        return orgList;
    }

    @Override
    public List<License> getTopLicense() {
        //TODO implement method
        return null;
    }

    @Override
    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    @Override
    public void deletePost(String id) {
        licenseRepository.deleteById(id);
    }

    @Override
    public void save(License license) {
        licenseRepository.save(license);
    }

    public License saveLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license;
    }
}
