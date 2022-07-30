package me.lovethefeel.member.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.member.application.MemberRedisService;
import me.lovethefeel.member.dto.MemberRedisRequest;
import me.lovethefeel.member.dto.MemberRedisResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/member")
@RestController
@RequiredArgsConstructor
public class MemberRedisController {

    private final MemberRedisService memberRedisService;

    @PostMapping("ci")
    public ResponseEntity<MemberRedisResponse> saveUserCi(@RequestBody final MemberRedisRequest request) {

        final MemberRedisResponse memberRedisResponseResponse = memberRedisService.saveUserCi(request);

        return ResponseEntity.status(HttpStatus.OK).body(memberRedisResponseResponse);
    }

}
