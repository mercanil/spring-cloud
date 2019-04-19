package com.mercan.organization.config;

import com.mercan.organization.model.Organization;
import com.mercan.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final OrganizationRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Organization organization = Organization.builder().id("org1")
                                                .contactEmail("email1")
                                                .contactName("name1")
                                                .contactPhone("phone1").build();
        repository.save(organization);
    }
}
