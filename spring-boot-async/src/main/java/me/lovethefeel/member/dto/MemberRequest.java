package me.lovethefeel.member.dto;

import lombok.Data;

@Data
public class MemberRequest {

    private Long memberId;
    private String memberName;
}
