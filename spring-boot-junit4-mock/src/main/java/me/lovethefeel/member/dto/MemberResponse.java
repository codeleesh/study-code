package me.lovethefeel.member.dto;

import java.time.LocalDateTime;

public class MemberResponse {

    private Long id;
    private String memberName;
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
