package me.lovethefeel.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.member.dto.MemberRedisRequest;
import me.lovethefeel.member.dto.MemberRedisResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRedisService {

    private final RedisTemplate redisTemplate;

    public static final String MEMBER_KEY_CI = "member-key-ci";

    public MemberRedisResponse saveUserCi(final MemberRedisRequest request) {

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        log.info("MemberRedisRequest :: {}", request);
        final String memberId = request.getUserId();
        final String uuid = UUID.randomUUID().toString();

        try {
            valueOperations.set(MEMBER_KEY_CI, uuid);
            log.info("Redis Save Success");
        } catch (DataAccessException dae) {
            log.error("Redis Connection fail :: {}", dae);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {
            log.error("Request type is unsupported operation :: {}", uoe);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }

        return MemberRedisResponse.success();
    }
}
