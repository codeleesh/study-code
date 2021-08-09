package com.lovethefeel.springboot.dto.user;

import com.lovethefeel.springboot.domain.user.UserSequence;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSequenceRequest {
    private String name;

    @Builder
    public UserSequenceRequest(String name){
        this.name = name;
    }

    public UserSequence toEntity() {
        return UserSequence.builder()
                .name(name)
                .build();
    }

}
