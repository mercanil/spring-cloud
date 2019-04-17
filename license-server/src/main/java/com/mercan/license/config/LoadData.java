package com.mercan.license.config;

import com.mercan.license.model.License;
import com.mercan.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {

    private final LicenseRepository repository;

    @Override
    public void run(String... args) throws Exception {
        License license = License.builder().licenseId("lic1").organizationId("org1").productName("pro1").licenseType("free").build();
        repository.save(license);
    }
}
