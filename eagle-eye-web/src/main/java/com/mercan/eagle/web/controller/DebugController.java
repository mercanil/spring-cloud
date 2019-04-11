package com.mercan.eagle.web.controller;

import com.mercan.eagle.web.config.ServiceConfig;
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
