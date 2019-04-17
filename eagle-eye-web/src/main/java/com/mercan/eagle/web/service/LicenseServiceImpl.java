package com.mercan.eagle.web.service;

import com.mercan.eagle.web.config.ServiceConfig;
import com.mercan.eagle.web.exception.LicenseNotFoundException;
import com.mercan.eagle.web.model.License;
import com.mercan.eagle.web.model.Organization;
import com.mercan.eagle.web.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {


    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;


    @Override
    public License getLicense(String organizationId, String licenseId , String clientType) throws LicenseNotFoundException {
        Optional<License> license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization organization = retriveOrgInfo(organizationId ,clientType );
        if(!license.isPresent()){
            throw new LicenseNotFoundException();
        }
          return   license.get()
                .withContactEmail(organization.getContactEmail())
                .withContactName(organization.getContactName())
                .withOrganizationName(organization.getName())
                .withContactPhone(organization.getContactPhone());
    }

    public Organization retriveOrgInfo(String organizationId, String clientType) {

        return null;
    }

    @Override
    public List<License> getLicenseByOrganization(String organizationId) {
        List<License> orgList = licenseRepository.findByOrganizationId(organizationId);
        orgList.forEach(org -> org.setComment(serviceConfig.getTracer()));
        return orgList;
    }


    @Override
    public void saveOrUpdateLicense(License license) {
        if (license.getLicenseId() == null){
            license.setLicenseId(UUID.randomUUID().toString());
        }
        licenseRepository.save(license);
    }

    @Override
    public void deletePost(String id) {
        licenseRepository.deleteById(id);
    }
}
