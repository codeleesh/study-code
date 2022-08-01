package me.lovethefeel.jpahistory.write.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.jpahistory.write.application.WriteService;
import me.lovethefeel.jpahistory.write.dto.WriteRequest;
import me.lovethefeel.jpahistory.write.dto.WriteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/write")
@RequiredArgsConstructor
public class WriteController {

    private final WriteService writeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WriteResponse> createMember(@RequestBody WriteRequest writeRequest) {

        final WriteResponse writeResponse = writeService.createWrite(writeRequest);
        return ResponseEntity.created(URI.create("/v1/write/" + writeResponse.getWriteId())).body(writeResponse);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WriteResponse> saveMember(@RequestBody WriteRequest writeRequest) {

        final WriteResponse writeResponse = writeService.updateWrite(writeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(writeResponse);
    }
}
