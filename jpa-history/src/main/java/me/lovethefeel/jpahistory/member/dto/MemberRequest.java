package me.lovethefeel.jpahistory.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.lovethefeel.jpahistory.member.domain.Member;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Getter
@JsonInclude(NON_NULL)
public class MemberRequest {

    private Long memberId;
    private String memberName;

    protected MemberRequest() {}

    private MemberRequest(final String memberName) {
        this.memberName = memberName;
    }

    private MemberRequest(final Long memberId, final String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public static MemberRequest from(final String memberName) {
        return new MemberRequest(memberName);
    }

    public static MemberRequest of(final Long memberId, final String memberName) {
        return new MemberRequest(memberId, memberName);
    }

    public Member toCreateEntity() {
        return Member.fromCreate(this.memberName);
    }
}
