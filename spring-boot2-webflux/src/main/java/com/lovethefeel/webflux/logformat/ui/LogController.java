package com.lovethefeel.webflux.logformat.ui;

import com.lovethefeel.webflux.logformat.application.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("structure-arguments")
    public void logStructureArguments() {
        logService.callStructureArguments();
    }

    @GetMapping("markers")
    public void logMarkers() {
        logService.callMarkers();
    }

}

