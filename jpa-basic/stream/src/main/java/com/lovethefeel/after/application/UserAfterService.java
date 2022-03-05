package com.lovethefeel.after.application;

import com.lovethefeel.after.domain.UserAfter;
import com.lovethefeel.after.domain.UserAfterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAfterService {

    private final UserAfterRepository userRepository;

    public UserAfter saveUser(final UserAfter user) {
        return userRepository.save(user);
    }

    public List<UserAfter> findByHobby(final String condition) {
        return userRepository.findAll()
                .stream()
                .filter(e -> "Y".equals(e.getUseYn()))
                .filter(e -> {
                    if (!CollectionUtils.isEmpty(e.getHobby())) {
                        return e.getHobby().contains(condition);
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());
    }
}
