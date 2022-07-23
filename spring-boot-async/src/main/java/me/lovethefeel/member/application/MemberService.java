package me.lovethefeel.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.cert.application.CertService;
import me.lovethefeel.member.domain.Member;
import me.lovethefeel.member.dto.MemberRequest;
import me.lovethefeel.member.dto.MemberResponse;
import me.lovethefeel.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 외부 API호출
    private final CertService certService;

    public MemberResponse certMember(final MemberRequest memberRequest) {

        log.info("대기응답이 긴 Thread 호출전 - 1");
        final CompletableFuture<String> certResult = certService.certProcess(memberRequest.getMemberId());
        certResult.thenAccept(
                result -> {
                    log.info("대기응답이 긴 Thread 응답 결과 {}", result);
                    if ("false".equals(result)) {
                        log.warn("Cert 인증 실패");
                        return ;
                    }
                    log.info("사용자 최종 상태 변경 호출전 - 4");
                    memberRepository.registerById(memberRequest.getMemberId());
                    log.info("사용자 최종 상태 변경 호출전 - 4");
                    log.info("대기응답이 긴 Thread 호출 후 - 1");
                }
        );

        log.info("대기응답이 짧은 사용자 조회 호출전 - 2");
        final Member findMember = memberRepository.findById(memberRequest.getMemberId());
        log.info("대기응답이 짧은 사용자 조회 호출후 - 2");

        if (Objects.nonNull(findMember)) {
            throw new IllegalArgumentException();
        }

        log.info("대기응답이 짧은 사용자 저장 호출전 - 3");
        final Member saveMember = memberRepository.save(new Member(memberRequest.getMemberName()));
        log.info("대기응답이 짧은 사용자 저장 호출후 - 3");

        return new MemberResponse().toEntity(saveMember);
    }
}
