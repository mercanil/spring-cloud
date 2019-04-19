package com.mercan.license.service;

import com.mercan.license.model.Organization;
import com.mercan.license.repository.LicenseRepository;
import com.mercan.license.config.ServiceConfig;
import com.mercan.license.exception.LicenseNotFoundException;
import com.mercan.license.model.License;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class LicenseServiceImpl implements LicenseService {


    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;
    private final RestTemplate restTemplate;

    @Override
    public License getLicense(String organizationId, String licenseId ) throws LicenseNotFoundException {
        Optional<License> license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        Organization organization = retriveOrgInfo(organizationId );
        if(!license.isPresent()){
            throw new LicenseNotFoundException();
        }
        return   license.get()
            .withContactEmail(organization.getContactEmail())
            .withContactName(organization.getContactName())
            .withOrganizationName(organization.getName())
            .withContactPhone(organization.getContactPhone());
    }

    public Organization retriveOrgInfo(String organizationId) {

        ResponseEntity<Organization> exchange = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", HttpMethod.GET,
                null, Organization.class, organizationId);

        log.debug(exchange.getBody().toString());
        return exchange.getBody();

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
