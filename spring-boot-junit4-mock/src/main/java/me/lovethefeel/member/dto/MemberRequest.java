package me.lovethefeel.member.dto;

public class MemberRequest {

    private String memberName;

    private MemberRequest(final String memberName) {
        this.memberName = memberName;
    }

    public static MemberRequest from(final String memberName) {
        return new MemberRequest(memberName);
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
