package com.mercan.organization.config;

import com.mercan.aspect.LoggerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class AspectConfig {
    @Bean
    public LoggerAspect createLoggerAspect(){
        return new LoggerAspect();
    }
}
