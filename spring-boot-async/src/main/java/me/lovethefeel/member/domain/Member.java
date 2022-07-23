package me.lovethefeel.member.domain;

import lombok.Data;

@Data
public class Member {

    private long memberId;
    private String memberName;

    private MemberStatus memberStatus;

    public Member() {
    }

    public Member(final String memberName) {
        this.memberName = memberName;
        this.memberStatus = MemberStatus.PROGRESS;
    }

    public void successRegisterMember() {
        this.memberStatus = MemberStatus.COMPLETED;
    }
}
