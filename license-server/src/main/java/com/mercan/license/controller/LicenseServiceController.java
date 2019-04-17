package com.mercan.license.controller;


import com.mercan.license.exception.LicenseNotFoundException;
import com.mercan.license.model.License;
import com.mercan.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@RequiredArgsConstructor
@Slf4j
public class LicenseServiceController {

    private final static String cacheValue = "license-single";
    private final LicenseService licenseService;

    @Cacheable(value = cacheValue)
    @GetMapping(value = "{licenseId}")
    public License getLicense(@PathVariable(value = "organizationId") String organizationId,
                              @PathVariable(value = "licenseId") String licenseId,
                              @PathVariable(value = "clientType") String clientType) throws LicenseNotFoundException {

        return licenseService.getLicense(organizationId, licenseId,clientType);
    }

    @CachePut(value = cacheValue)
    @PostMapping("/")
    public License createLicense(@RequestBody License license) throws LicenseNotFoundException {
        log.info("update post with id {}", license.getLicenseId());
        licenseService.saveOrUpdateLicense(license);
        return license;
    }

    @CachePut(value = cacheValue)
    @PutMapping("/")
    public License updateLicense(@RequestBody License license) throws LicenseNotFoundException {
        log.info("update post with id {}", license.getLicenseId());
        licenseService.saveOrUpdateLicense(license);
        return license;
    }

    @CacheEvict(value = cacheValue)
    @DeleteMapping("/{id}")
    public void deletePostByID(@PathVariable String id) throws LicenseNotFoundException {
        log.info("delete post with id {}", id);
        licenseService.deletePost(id);
    }

}
