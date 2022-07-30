package me.lovethefeel.member.dto;

import lombok.Getter;

@Getter
public class MemberRedisResponse {

    private boolean result;

    private MemberRedisResponse(final boolean result) {
        this.result = result;
    }

    public static MemberRedisResponse success() {
        return new MemberRedisResponse(true);
    }
}
