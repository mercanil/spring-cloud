package com.mercan.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients
public class LicenseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseServerApplication.class, args);
    }


    @Bean
    @LoadBalanced //This annotation fixes host exception. Ribbon knows where is the host
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
