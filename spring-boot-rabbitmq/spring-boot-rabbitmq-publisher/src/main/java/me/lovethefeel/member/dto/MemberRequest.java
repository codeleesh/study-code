package me.lovethefeel.member.dto;

import lombok.Getter;
import me.lovethefeel.member.domain.Address;
import me.lovethefeel.member.domain.MemberStatus;

import java.time.LocalDateTime;

@Getter
public class MemberRequest {

    private String memberName;
    private MemberStatus memberStatus;
    private Address address;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updateBy;
}
