package me.lovethefeel.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.config.MemoryCache;
import me.lovethefeel.member.dto.MemberRedisRequest;
import me.lovethefeel.member.dto.MemberRedisResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRedisService {

    private final MemoryCache<String> memoryCache;
    public static final String STRING_EMPTY = "";
    public static final String MEMBER_KEY_CI = "member-key-ci";

    public MemberRedisResponse saveUserCi(final MemberRedisRequest request) {

        log.info("MemberRedisRequest :: {}", request);
        final String userId = request.getUserId();
        final String uuid = UUID.randomUUID().toString();

        try {
            memoryCache.set(MEMBER_KEY_CI + "-" + userId, uuid);
            log.info("Redis Save Success");
        } catch (DataAccessException dae) {
            log.error("Redis Connection fail :: {}", dae);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {
            log.error("Request type is unsupported operation :: {}", uoe);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }

        return MemberRedisResponse.success(uuid);
    }

    public MemberRedisResponse findUserCi(final String userId) {

        log.info("MemberRedisRequest :: {}", userId);
        String findUuid = STRING_EMPTY;

        try {
            findUuid = memoryCache.get(MEMBER_KEY_CI + "-" + userId, String.class);
            log.info("Redis Find Success");
        } catch (DataAccessException dae) {
            log.error("Redis Connection fail :: {}", dae);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {
            log.error("Request type is unsupported operation :: {}", uoe);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }

        return MemberRedisResponse.success(findUuid);
    }

    public void deleteUserCi(final String userId) {

        log.info("MemberRedisRequest :: {}", userId);

        try {
            memoryCache.delete(MEMBER_KEY_CI + "-" + userId);
            log.info("Redis Find Success");
        } catch (DataAccessException dae) {
            log.error("Redis Connection fail :: {}", dae);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dae.getMessage());
        } catch (UnsupportedOperationException uoe) {
            log.error("Request type is unsupported operation :: {}", uoe);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, uoe.getMessage());
        }
    }
}
