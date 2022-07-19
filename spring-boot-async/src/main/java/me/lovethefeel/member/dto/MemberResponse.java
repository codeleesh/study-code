package me.lovethefeel.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.lovethefeel.member.domain.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private long memberId;

    public MemberResponse toEntity(final Member member) {
        return new MemberResponse(member.getMemberId());
    }
}
