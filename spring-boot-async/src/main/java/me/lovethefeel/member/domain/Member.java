package me.lovethefeel.member.domain;

import lombok.Data;

@Data
public class Member {

    private long memberId;
    private String memberName;

    public Member() {
    }

    public Member(final String memberName) {
        this.memberName = memberName;
    }
}
