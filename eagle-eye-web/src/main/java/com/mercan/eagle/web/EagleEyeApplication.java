package com.mercan.eagle.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients
public class EagleEyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleEyeApplication.class, args);
    }
}
