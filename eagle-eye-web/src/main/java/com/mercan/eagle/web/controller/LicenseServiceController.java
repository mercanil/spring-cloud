package com.mercan.eagle.web.controller;


import com.mercan.eagle.web.exception.LicenseNotFoundException;
import com.mercan.eagle.web.model.License;
import com.mercan.eagle.web.service.LicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@RequiredArgsConstructor
@Slf4j
public class LicenseServiceController {

    private final static String cacheValue = "license-single";
    private final LicenseService licenseService;

    @Cacheable(value = cacheValue ,key = "#id" , unless = "#result.shares < 500" )
    @GetMapping(value = "{licenseId}")
    public License getLicense(@PathVariable(value = "organizationId") String organizationId,
                              @PathVariable(value = "licenseId") String licenseId) {

        return License.builder()
                .licenseId(licenseId)
                .licenseType("Seat")
                .productName("Teleco")
                .organizationId(organizationId)
                .build();
    }

    @Cacheable(value = "post-top")
    @GetMapping("/top")
    public List<License> getTopLicense() {
        return licenseService.getTopLicense();
    }


    @CachePut(value = cacheValue , key = "#license.id")
    @PutMapping("/")
    public License updateLiceseById(@RequestBody License license) throws LicenseNotFoundException {
        log.info("update post with id {}", license.getLicenseId());
        licenseService.updateLicense(license);
        return license;
    }

    @CacheEvict(value = cacheValue, key = "#id")
    @DeleteMapping("/delete/{id}")
    public void deletePostByID(@PathVariable String id) throws LicenseNotFoundException {
        log.info("delete post with id {}", id);
        licenseService.deletePost(id);
    }

    @CacheEvict(value = "post-top")
    @GetMapping("/top/evict")
    public void evictTopPosts() {
        log.info("Evict post-top");
    }

}
