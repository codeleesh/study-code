package me.lovethefeel.jpahistory.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.jpahistory.member.domain.Member;
import me.lovethefeel.jpahistory.member.domain.MemberHistory;
import me.lovethefeel.jpahistory.member.dto.MemberRequest;
import me.lovethefeel.jpahistory.member.dto.MemberResponse;
import me.lovethefeel.jpahistory.member.repository.MemberHistoryRepository;
import me.lovethefeel.jpahistory.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberHistoryRepository historyRepository;

    @Transactional
    public MemberResponse createMember(final MemberRequest memberRequest) {

        final Member saveMember = memberRepository.save(memberRequest.toCreateEntity());
        final MemberHistory memberHistory = MemberHistory.fromEntity(saveMember, "신규 저장");
        historyRepository.save(memberHistory);

        return MemberResponse.fromResponse(saveMember);
    }

    @Transactional
    public MemberResponse updateMember(final MemberRequest memberRequest) {

        final Member findMember = memberRepository.findById(memberRequest.getMemberId())
                .orElseThrow(IllegalArgumentException::new);

        findMember.updateName(memberRequest.getMemberName());
        final MemberHistory memberHistory = MemberHistory.fromEntity(findMember, "이름 변경");
        historyRepository.save(memberHistory);

        return MemberResponse.fromResponse(findMember);
    }
}
