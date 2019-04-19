package com.mercan.license.service;

import com.mercan.license.model.Organization;
import com.mercan.license.repository.LicenseRepository;
import com.mercan.license.config.ServiceConfig;
import com.mercan.license.exception.LicenseNotFoundException;
import com.mercan.license.model.License;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@DefaultProperties(
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "500")
        }
)
public class LicenseServiceImpl implements LicenseService {


    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;
    private final RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "buildFallbackLicenseList")

    public License getLicense(String organizationId, String licenseId ) throws LicenseNotFoundException {
        sleep();
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

    public License buildFallbackLicenseList(String organizationId,String licenseId){
        return License.builder()
                .comment("empty")
                .contactEmail("empty")
                .contactPhone("empty")
                .contactName("empty")
                .licenseId(licenseId)
                .licenseType("empty")
                .comment(organizationId)
                .build();
    }

    public Organization retriveOrgInfo(String organizationId) {

        ResponseEntity<Organization> exchange = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", HttpMethod.GET,
                null, Organization.class, organizationId);

        log.debug(exchange.getBody().toString());
        return exchange.getBody();

    }

    @Override
    @HystrixCommand
    public List<License> getLicenseByOrganization(String organizationId) {
        randomlyRunLong();
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


    private void randomlyRunLong(){
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum==3) sleep();
    }
    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
