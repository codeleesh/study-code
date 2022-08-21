package me.lovethefeel.jpahistory.member.application;

import me.lovethefeel.jpahistory.member.domain.Member;
import me.lovethefeel.jpahistory.member.domain.MemberHistory;
import me.lovethefeel.jpahistory.member.dto.MemberRequest;
import me.lovethefeel.jpahistory.member.repository.MemberHistoryRepository;
import me.lovethefeel.jpahistory.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberHistoryRepository historyRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 사용자_저장() {

        final MemberRequest 사용자_요청 = MemberRequest.from("lsh");
        final Timestamp 현재시간 = Timestamp.valueOf("2022-08-21 14:00:00.000");
        final Member 사용자_저장_결과 = Member.ofCreate(1L, "lsh", 현재시간, "lah", 현재시간, "lsh");
        final MemberHistory 사용자_이력_저장_결과 = MemberHistory.fromEntity(1L, 사용자_저장_결과, "신규 저장");

        when(memberRepository.save(any())).thenReturn(사용자_저장_결과);
        when(historyRepository.save(any())).thenReturn(사용자_이력_저장_결과);

        memberService.createMember(사용자_요청);

        verify(memberRepository, times(1)).save(any());
        verify(historyRepository, times(1)).save(any());

        final InOrder inOrder = inOrder(memberRepository, historyRepository);
        inOrder.verify(memberRepository).save(any());
        inOrder.verify(historyRepository).save(any());
    }
}