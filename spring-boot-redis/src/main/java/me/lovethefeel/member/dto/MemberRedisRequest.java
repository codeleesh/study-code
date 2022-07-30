package me.lovethefeel.member.dto;

import lombok.Getter;

@Getter
public class MemberRedisRequest {

    private String userId;

    @Override
    public String toString() {
        return "MemberRedisRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
