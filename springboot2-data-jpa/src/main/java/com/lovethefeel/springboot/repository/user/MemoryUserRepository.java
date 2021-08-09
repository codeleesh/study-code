package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserSequence;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

    private final static Map<Long, UserSequence> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserSequence save(UserSequence userBigSerial) {
        userBigSerial.setId(sequence++);
        store.put(userBigSerial.getId(), userBigSerial);
        return userBigSerial;
    }

    @Override
    public Optional<UserSequence> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<UserSequence> findByName(String name) {
        return store.values().stream()
                .filter(userBigSerial -> userBigSerial.getName().equals(name))
                .findAny();
    }

    @Override
    public List<UserSequence> findAll() {
        return new ArrayList<>(store.values());
    }
}
