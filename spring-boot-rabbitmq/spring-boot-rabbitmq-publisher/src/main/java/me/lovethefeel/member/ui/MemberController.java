package me.lovethefeel.member.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.member.application.MemberService;
import me.lovethefeel.member.domain.Member;
import me.lovethefeel.member.dto.MemberRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("publish")
    public String memberPublish(@RequestBody final MemberRequest memberRequest) {

        return memberService.memberPublish(Member.from(memberRequest));
    }
}
