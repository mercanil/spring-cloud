package com.mercan.organization.service;


import com.mercan.organization.exception.OrganizationNotFoundException;
import com.mercan.organization.model.Organization;
import com.mercan.organization.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Data
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;


    @Override
    public Organization getOrganization(String organizationId) throws OrganizationNotFoundException {
        Optional<Organization> org = organizationRepository.findById(organizationId);
        return org.orElseThrow(() ->  new OrganizationNotFoundException());
    }

    @Override
    public Organization saveOrUpdatOrganization(Organization organization) {
        if(organization.getId() == null){
            organization.setId(UUID.randomUUID().toString());
        }
        organizationRepository.save(organization);
        return organization;
    }

    @Override
    public void deleteOrganization(String id) throws OrganizationNotFoundException {
        organizationRepository.deleteById(id);
    }
}
