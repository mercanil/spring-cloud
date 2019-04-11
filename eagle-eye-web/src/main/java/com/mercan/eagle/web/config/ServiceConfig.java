package com.mercan.eagle.web.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;

}
