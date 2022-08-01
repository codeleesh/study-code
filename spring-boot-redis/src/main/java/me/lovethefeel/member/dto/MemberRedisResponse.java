package me.lovethefeel.member.dto;

import lombok.Getter;

@Getter
public class MemberRedisResponse {

    private boolean result;
    private String uuid;

    private MemberRedisResponse(final boolean result, final String uuid) {

        this.result = result;
        this.uuid = uuid;
    }

    public static MemberRedisResponse success(final String uuid) {
        return new MemberRedisResponse(true, uuid);
    }
}
