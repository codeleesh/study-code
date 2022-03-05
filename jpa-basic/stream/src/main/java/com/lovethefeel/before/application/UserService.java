package com.lovethefeel.before.application;

import com.lovethefeel.before.domain.User;
import com.lovethefeel.before.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public List<User> findByHobby(final String condition) {
        return userRepository.findAll()
                .stream()
                .filter(e -> "Y".equals(e.getUseYn()))
                .filter(e -> {
                    String hobby = e.getHobby();
                    if (Strings.isNotBlank(hobby)) {
                        return Arrays.stream(e.getHobby()
                                        .trim()
                                        .split(","))
                                .collect(Collectors.toList())
                                .contains(condition);
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());
    }
}
