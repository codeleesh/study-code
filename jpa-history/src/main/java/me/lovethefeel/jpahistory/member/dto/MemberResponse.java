package me.lovethefeel.jpahistory.member.dto;

import lombok.Getter;
import me.lovethefeel.jpahistory.member.domain.Member;

@Getter
public class MemberResponse {

    private Long memberId;
    private String memberName;

    protected MemberResponse() {}

    private MemberResponse(final Long memberId, final String memberName) {

        this.memberId = memberId;
        this.memberName = memberName;
    }

    public static MemberResponse fromResponse(final Member member) {

        return new MemberResponse(member.getId(), member.getMemberName());
    }
}
