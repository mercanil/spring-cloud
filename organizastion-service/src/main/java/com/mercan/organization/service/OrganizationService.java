package com.mercan.organization.service;

import com.mercan.organization.exception.OrganizationNotFoundException;
import com.mercan.organization.model.Organization;

public interface OrganizationService {
    Organization getOrganization(String organizationId) throws OrganizationNotFoundException;

    Organization saveOrUpdatOrganization(Organization organization);

    void deleteOrganization(String id) throws OrganizationNotFoundException;
}
