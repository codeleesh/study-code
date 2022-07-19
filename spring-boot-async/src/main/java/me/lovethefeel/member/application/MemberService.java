package me.lovethefeel.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.cert.application.CertService;
import me.lovethefeel.member.domain.Member;
import me.lovethefeel.member.dto.MemberRequest;
import me.lovethefeel.member.dto.MemberResponse;
import me.lovethefeel.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

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

        log.info("certProcess 호출전 - 1");
        final CompletableFuture<String> certResult = certService.certProcess(memberRequest.getMemberId());
        certResult.thenAccept(
                r -> {
                    log.info("--> non block result {}", r);
                    log.info("certProcess 호출후 - 1");
                }
        );

        log.info("findById 호출전 - 2");
        final Member findMember = memberRepository.findById(memberRequest.getMemberId());
        log.info("findById 호출후 - 2");

        if (Objects.nonNull(findMember)) {
            throw new IllegalArgumentException();
        }

        final Member saveMember = memberRepository.save(new Member(memberRequest.getMemberName()));

        return new MemberResponse().toEntity(saveMember);
    }
}
