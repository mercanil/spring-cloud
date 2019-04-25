package com.mercan.license.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
