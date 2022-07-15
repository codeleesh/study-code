package me.lovethefeel.member.application;

import me.lovethefeel.common.NotFoundException;
import me.lovethefeel.member.domain.Member;
import me.lovethefeel.member.domain.MemberRepository;
import me.lovethefeel.member.dto.MemberRequest;
import me.lovethefeel.member.dto.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createMember(final MemberRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }

    public MemberResponse findMember(final Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return MemberResponse.of(member);
    }

    @Transactional
    public void updateMember(final Long id, final MemberRequest param) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        member.update(param.toMember());
    }

    @Transactional
    public void deleteMember(final Long id) {
        memberRepository.deleteById(id);
    }

    public Member findMemberById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
