package me.lovethefeel.member.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.member.application.MemberRedisService;
import me.lovethefeel.member.dto.MemberRedisRequest;
import me.lovethefeel.member.dto.MemberRedisResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("ci")
    public ResponseEntity<MemberRedisResponse> findUserCi(@RequestParam final String userId) {

        final MemberRedisResponse memberRedisResponseResponse = memberRedisService.findUserCi(userId);

        return ResponseEntity.status(HttpStatus.OK).body(memberRedisResponseResponse);
    }

    @DeleteMapping("ci")
    public ResponseEntity<Void> deleteUserCi(@RequestParam final String userId) {

        memberRedisService.deleteUserCi(userId);

        return ResponseEntity.noContent().build();
    }

}
