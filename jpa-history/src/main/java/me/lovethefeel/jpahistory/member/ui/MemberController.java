package me.lovethefeel.jpahistory.member.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.jpahistory.member.application.MemberService;
import me.lovethefeel.jpahistory.member.dto.MemberRequest;
import me.lovethefeel.jpahistory.member.dto.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest memberRequest) {

        final MemberResponse memberResponse = memberService.createMember(memberRequest);
        return ResponseEntity.created(URI.create("/v1/member/" + memberResponse.getMemberId())).body(memberResponse);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberResponse> saveMember(@RequestBody MemberRequest memberRequest) {

        final MemberResponse memberResponse = memberService.updateMember(memberRequest);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }
}
