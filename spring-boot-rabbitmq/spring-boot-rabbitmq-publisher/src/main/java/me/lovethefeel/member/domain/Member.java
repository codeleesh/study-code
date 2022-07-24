package me.lovethefeel.member.domain;

import me.lovethefeel.member.dto.MemberRequest;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Member implements Serializable {

    private String memberName;
    private MemberStatus memberStatus;
    private Address address;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updateBy;

    public Member(final String memberName, final MemberStatus memberStatus, final Address address, final LocalDateTime created
            , final String createdBy, final LocalDateTime updated, final String updateBy) {

        this.memberName = memberName;
        this.memberStatus = memberStatus;
        this.address = address;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updateBy = updateBy;
    }

    public static Member from(final MemberRequest memberRequest) {
        return new Member(memberRequest.getMemberName(), memberRequest.getMemberStatus(), memberRequest.getAddress()
                , memberRequest.getCreated(), memberRequest.getCreatedBy(), memberRequest.getUpdated(), memberRequest.getUpdateBy());
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberName='" + memberName + '\'' +
                ", memberStatus=" + memberStatus +
                ", address=" + address +
                ", created=" + created +
                ", createdBy='" + createdBy + '\'' +
                ", updated=" + updated +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
