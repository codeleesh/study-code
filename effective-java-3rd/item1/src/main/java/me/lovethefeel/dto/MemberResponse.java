package me.lovethefeel.dto;

import me.lovethefeel.domain.Member;
import me.lovethefeel.domain.MemberStatus;

public class MemberResponse {

    private Long id;
    private String memberName;
    private MemberStatus memberStatus;

    private MemberResponse(final Long id, final String memberName, final MemberStatus memberStatus) {
        this.id = id;
        this.memberName = memberName;
        this.memberStatus = memberStatus;
    }

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getMemberName(), member.getMemberStatus());
    }
}
