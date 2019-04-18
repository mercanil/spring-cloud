package com.mercan.organization.controller;


import com.mercan.organization.exception.OrganizationNotFoundException;
import com.mercan.organization.model.Organization;
import com.mercan.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/organizations/")
@RequiredArgsConstructor
@Slf4j
public class OrganizationServiceController {

    private final static String cacheValue = "license-single";

    private final OrganizationService organizationService;

    @Cacheable(value = cacheValue)
    @GetMapping(value = "{organizationId}")
    public Organization getOrganization(@PathVariable(value = "organizationId") String organizationId )
            throws OrganizationNotFoundException {

        return organizationService.getOrganization(organizationId);
    }

    @CachePut(value = cacheValue)
    @PostMapping("/")
    public Organization createLicense(@RequestBody Organization organization) throws OrganizationNotFoundException {
        log.info("update post with id {}", organization.getId());
        return organizationService.saveOrUpdatOrganization(organization);

    }

    @CachePut(value = cacheValue)
    @PutMapping("/")
    public Organization updateLicense(@RequestBody Organization organization) throws OrganizationNotFoundException {
        log.info("update post with id {}", organization.getId());
        return organizationService.saveOrUpdatOrganization(organization);

    }

    @CacheEvict(value = cacheValue)
    @DeleteMapping("/{id}")
    public ResponseEntity deletePostById(@PathVariable String id) throws OrganizationNotFoundException {
        log.info("delete post with id {}", id);
         organizationService.deleteOrganization(id);
         return ResponseEntity.noContent().build();
    }

}
