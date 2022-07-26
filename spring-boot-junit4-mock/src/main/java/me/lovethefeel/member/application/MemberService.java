package me.lovethefeel.member.application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.member.domain.Member;
import me.lovethefeel.member.domain.MemberRepository;
import me.lovethefeel.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private RestTemplate restTemplate;
    private MemberRepository memberRepository;

    public Member save(final Member member) {

        log.info("MemberService:: {}", member);

        // 외부 호출
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("url", null, String.class);

        try {
            MemberResponse memberResponse = new Gson().fromJson(responseEntity.getBody(), MemberResponse.class);
            log.info("memberResponse:: {}", memberResponse);

        } catch(JsonSyntaxException je) {
            log.error("Exception!! {}", je);
            throw new RuntimeException("custom error");
        }

        return member;
    }
}
