package com.lovethefeel.springboot.service.user;

import com.lovethefeel.springboot.config.aop.LogExecutionTime;
import com.lovethefeel.springboot.dto.user.UserSequenceRequest;
import com.lovethefeel.springboot.repository.usersequence.UserSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserSequenceRepository userSequenceRepository;

    @Transactional
    @LogExecutionTime
    public Long save(UserSequenceRequest userSequenceRequest) {
        return userSequenceRepository.save(userSequenceRequest.toEntity()).getId();
    }
}
