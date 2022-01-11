package com.lovethefeel.springboot.repository.usersequence;

import com.lovethefeel.springboot.domain.user.UserSequence;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.lovethefeel.springboot.domain.user.QUserSequence.userSequence;


@RequiredArgsConstructor
@Repository
public class UserSequenceRepositoryImpl implements UserSequenceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Optional<UserSequence> findById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(userSequence)
                .fetchOne());
    }

    @Transactional(readOnly = true)
    public Boolean exist(String sex) {
        UserSequence fetchOne = queryFactory
                .selectFrom(userSequence)
                .fetchFirst();

        return fetchOne != null;
    }
}

