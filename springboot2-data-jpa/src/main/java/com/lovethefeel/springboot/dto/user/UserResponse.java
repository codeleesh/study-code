package com.lovethefeel.springboot.dto.user;

import com.lovethefeel.springboot.domain.user.UserSequence;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String name;

    public UserResponse(UserSequence userSequence) {
        this.id = userSequence.getId();
        this.name = userSequence.getName();
    }
}
