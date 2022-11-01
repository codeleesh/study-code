package me.lovethefeel.sample.api;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.sample.application.SampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("circuit")
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("test")
    public ResponseEntity<Void> enable() {

        sampleService.send();
        return ResponseEntity.ok().build();
    }
}
