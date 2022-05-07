package com.lovethefeel.webflux.application;

import com.lovethefeel.webflux.domain.User;
import com.lovethefeel.webflux.dto.UserRequest;
import com.lovethefeel.webflux.dto.UserResponse;
import com.lovethefeel.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    final UserRepository userRepository;

    @Transactional
    public UserResponse saveUser(final UserRequest userRequest) {
        log.info("saveUser - userRequest [{}]", userRequest);
        final User saveUser = userRepository.save(userRequest.toEntity());
        UserResponse userResponse = UserResponse.of(saveUser);
        log.info("saveUser - userResponse [{}]", userResponse);
        return userResponse;
    }
}
