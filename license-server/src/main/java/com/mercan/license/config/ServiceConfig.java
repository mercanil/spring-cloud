package com.mercan.license.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
@Getter

public class ServiceConfig {

    @Value("${tracer.property}")
    private String tracer;

    @PostConstruct
    public void postConstruct() {
        System.out.println("------------------" + tracer);
    }


}
