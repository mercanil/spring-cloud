package com.mercan.eagle.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@EnableCaching
public class EagleEyeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EagleEyeApplication.class, args);
    }
}
