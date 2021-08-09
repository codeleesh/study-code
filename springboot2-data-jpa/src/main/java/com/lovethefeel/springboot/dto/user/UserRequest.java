package com.lovethefeel.springboot.dto.user;

import com.lovethefeel.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String userId;
    private String userName;
    private int count;

    @Builder
    public UserRequest(String userId, String userName, Integer count){
        this.userId = userId;
        this.userName = userName;
        this.count = count;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userName(userName)
                .count(count)
                .build();
    }

}
