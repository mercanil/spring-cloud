package com.mercan.organization.repository;

import com.mercan.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization , String> {
}
