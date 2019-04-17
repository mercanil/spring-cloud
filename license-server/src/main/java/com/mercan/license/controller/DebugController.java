package com.mercan.license.controller;

import com.mercan.license.config.ServiceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/")
@RequiredArgsConstructor
public class DebugController {
    private final ServiceConfig serviceConfig;

    @RequestMapping(value = "getLatest")
    public String getLatest() {
        return serviceConfig.getTracer();
    }

}
