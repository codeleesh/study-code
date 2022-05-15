package com.lovethefeel.webflux.user.ui;

import com.lovethefeel.webflux.user.application.UserService;
import com.lovethefeel.webflux.user.dto.UserRequest;
import com.lovethefeel.webflux.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        final UserResponse userResponse = userService.saveUser(userRequest);
        return ResponseEntity.created(URI.create("/users/" + userResponse.getId())).body(userResponse);
    }
}
