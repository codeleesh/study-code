package com.lovethefeel.springboot.controller;

import com.lovethefeel.springboot.config.aop.LogExecutionTime;
import com.lovethefeel.springboot.dto.user.UserSequenceRequest;
import com.lovethefeel.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/user")
    @LogExecutionTime
    public Long save(@RequestBody UserSequenceRequest userSequenceRequest){
        return userService.save(userSequenceRequest);
    }
}
