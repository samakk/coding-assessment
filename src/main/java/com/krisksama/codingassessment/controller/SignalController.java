package com.krisksama.codingassessment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krisksama.codingassessment.service.Application;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignalController {

    private final Application application;

    // one could improve the rest controller by returning problem json when handling exceptions ...
    @GetMapping(value = "/execute")
    public void handleSignal(@RequestParam int signal) {
        application.handleSignal(signal);
    }

}
