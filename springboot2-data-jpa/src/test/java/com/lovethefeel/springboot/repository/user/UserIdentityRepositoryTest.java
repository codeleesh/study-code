package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.common.enums.Sex;
import com.lovethefeel.springboot.domain.user.UserIdentity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserIdentityRepositoryTest {

    @Autowired
    private SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository;

    @Test
    @Transactional
    void stream_foreach_print1() {

        // given
        IntStream.rangeClosed(1, 200).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();

        // then
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> System.out.println(user.getBalanceAmt()));
    }

    @Test
    @Transactional
    void stream_foreach_print2() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();

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
    @Transactional
    public void stream_long_sum() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();
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
    @Transactional
    public void stream_bigdecimal_sum() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();

        BigDecimal result = userIdentity.stream()
                .map(UserIdentity::getBalanceAmt)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("합산금액: " + result);
    }

    @Test
    @Transactional
    public void stream_filter_count() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();
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
    @Transactional
    public void stream_filter_save() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();
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
    @Transactional
    public void stream_filter_grouping_save() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .sex(Sex.getRandom())
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt((long) new SecureRandom().nextInt(100))
                        .build())
        );

        // when
        List<UserIdentity> userIdentity = springDataJpaUserIdentityRepository.findAll();
        Stream<UserIdentity> stream = userIdentity.stream();
        stream.forEach(user -> {
            Long loanAmt = user.getLoanAmt();
            String sex = user.getSex().getValue();
            System.out.println(" loanAmt : " + loanAmt + " sex : " + sex);
        });

        Map<Sex, List<UserIdentity>> mapBySex = userIdentity.stream()
                .collect(Collectors.groupingBy(UserIdentity::getSex));

        System.out.println("\n[남성]");
        mapBySex.get(Sex.MALE)
                .forEach(s -> System.out.print(s.getName() + " "));
        System.out.println("\n[여성]");
        mapBySex.get(Sex.FEMALE)
                .forEach(s -> System.out.print(s.getName() + " "));
    }

    @Test
    @Transactional
    public void 사용자_저장_조회() {

        // given
        UserIdentity userIdentity = UserIdentity.builder()
                .name("이름")
                .build();
        Long id = springDataJpaUserIdentityRepository.save(userIdentity).getId();

        // when
        UserIdentity result = springDataJpaUserIdentityRepository.findById(id).orElse(null);
        // then
        assertThat(userIdentity).isEqualTo(result);
    }

    @Test
    @Transactional
    public void 대량_저장() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserIdentity> userIdentityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserIdentity userIdentity = new UserIdentity();
            userIdentityList.add(userIdentity);
        }
        springDataJpaUserIdentityRepository.saveAll(userIdentityList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }

}
