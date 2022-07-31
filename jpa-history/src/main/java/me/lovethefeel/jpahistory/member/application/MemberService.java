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

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    final MemberRepository memberRepository;
    final MemberHistoryRepository historyRepository;

    @Transactional
    public MemberResponse createMember(final MemberRequest memberRequest) {

        final Member saveMember = memberRepository.save(memberRequest.toCreateEntity());
        if (Objects.nonNull(saveMember)) {
            final MemberHistory memberHistory = MemberHistory.fromEntity(saveMember, "신규 저장");
            historyRepository.save(memberHistory);
        }

        final MemberResponse memberResponse = MemberResponse.fromResponse(saveMember);
        return memberResponse;
    }

    @Transactional
    public MemberResponse updateMember(final MemberRequest memberRequest) {

        final Member findMember = memberRepository.findById(memberRequest.getMemberId())
                .orElseThrow(IllegalArgumentException::new);

        findMember.updateName(memberRequest.getMemberName());
        final MemberHistory memberHistory = MemberHistory.fromEntity(findMember, "이름 변경");
        historyRepository.save(memberHistory);

        final MemberResponse memberResponse = MemberResponse.fromResponse(findMember);
        return memberResponse;
    }
}
