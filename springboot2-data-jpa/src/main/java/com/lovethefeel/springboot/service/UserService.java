package com.lovethefeel.springboot.service;

import com.lovethefeel.springboot.config.aop.LogExecutionTime;
import com.lovethefeel.springboot.dto.user.UserSequenceRequest;
import com.lovethefeel.springboot.repository.user.SpringDataJpaUserSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final SpringDataJpaUserSequenceRepository springDataJpaUserSequenceRepository;

    @Transactional
    @LogExecutionTime
    public Long save(UserSequenceRequest userSequenceRequest) {
        return springDataJpaUserSequenceRepository.save(userSequenceRequest.toEntity()).getId();
    }
}
