package me.lovethefeel.member.ui;

import lombok.AllArgsConstructor;
import me.lovethefeel.member.application.MemberService;
import me.lovethefeel.member.dto.MemberRequest;
import me.lovethefeel.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/v1/member")
@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/cert")
    public ResponseEntity<MemberResponse> certMember(@RequestBody final MemberRequest memberRequest) {

        MemberResponse response = memberService.certMember(memberRequest);
        return ResponseEntity.created(URI.create("/v1/member" + response.getMemberId())).body(response);
    }
}
