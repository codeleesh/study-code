package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.common.enums.Sexs;
import com.lovethefeel.springboot.domain.user.UserIdentity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserIdentityRepositoryTest {

    @Autowired
    private UserIdentityRepository userIdentityRepository;

    @BeforeEach
    void init() {
        IntStream.rangeClosed(1, 10).forEach(index ->
                userIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );
    }

    @Test
    void stream_foreach_print1() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();

        // then
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> System.out.println(user.getBalanceAmt()));
    }

    @Test
    void stream_foreach_print2() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();

        // then
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            String name = user.getName();
            BigDecimal balanceAmt = user.getBalanceAmt();
            Long loanAmt = user.getLoanAmt();
            System.out.println("name : " + name + " balanceAmt : " + balanceAmt + " loanAmt : " + loanAmt);
        });
    }

    @Test
    void stream_long_sum() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            System.out.println(" loanAmt : " + loanAmt);
        });

        // when
        long result = userIdentity.stream()
                .filter(t->t.getLoanAmt() < 50)
                .mapToLong(UserIdentity::getLoanAmt)
                .sum();

        System.out.println("합산금액: " + result);
    }

    @Test
    void stream_bigdecimal_sum() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();

        BigDecimal result = userIdentity.stream()
                .map(UserIdentity::getBalanceAmt)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("합산금액: " + result);
    }

    @Test
    void stream_filter_count() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            System.out.println(" loanAmt : " + loanAmt);
        });

        long cnt = userIdentity.stream()
                .filter(user -> user.getLoanAmt() > 50)
                .count();
        System.out.println("50보다 큰 금액의 갯수 : " + cnt);
    }

    @Test
    void stream_filter_save() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            System.out.println(" loanAmt : " + loanAmt);
        });

        List<UserIdentity> userList = userIdentity.stream()
                .filter(user -> user.getLoanAmt() > 50)
                .collect(Collectors.toList());
        System.out.println("filter count : " + userList.size());
    }

    @Test
    void stream_filter_grouping_save() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            String sex = user.getSexs().getValue();
            System.out.println(" loanAmt : " + loanAmt + " sex : " + sex);
        });

        Map<Sexs, List<UserIdentity>> mapBySex = userIdentity.stream()
                .collect(Collectors.groupingBy(UserIdentity::getSexs));

        System.out.println("\n[남성]");
        mapBySex.get(Sexs.MALE)
                .forEach(s -> System.out.print(s.getName() + " "));
        System.out.println("\n[여성]");
        mapBySex.get(Sexs.FEMALE)
                .forEach(s -> System.out.print(s.getName() + " "));
    }

    @Test
    void stream_filter_grouping_average() {

        // given

        // when
        List<UserIdentity> userIdentity = userIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            String sex = user.getSexs().getValue();
            System.out.println(" loanAmt : " + loanAmt + " sex : " + sex);
        });

        Map<Sexs, Double> mapBySex = userIdentity.stream()
                .collect(
                        Collectors.groupingBy(
                                UserIdentity::getSexs,
                                Collectors.averagingLong(UserIdentity::getLoanAmt)
                        )
                );

        System.out.println("\n[남성]");
        System.out.print(mapBySex.get(Sexs.MALE) + " ");
        System.out.println("\n[여성]");
        System.out.print(mapBySex.get(Sexs.FEMALE) + " ");
    }

    @Test
    void 대량_저장() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserIdentity> userIdentityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserIdentity userIdentity = new UserIdentity();
            userIdentityList.add(userIdentity);
        }
        userIdentityRepository.saveAll(userIdentityList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }

}
