package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserIdentity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class UserIdentityRepositoryTest {

    @Autowired
    private SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository;

//    @BeforeEach
//    void init() {
//        IntStream.rangeClosed(1, 200).forEach(index ->
//                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
//                        .name("이름"+index)
//                        .balance_amt(new BigDecimal(new SecureRandom().nextInt(1000000)))
//                        .build())
//        );
//    }

    @Test
    @Transactional
    void 스트림_출력1() {

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
    void 스트림_출력2() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt(Long.valueOf(new SecureRandom().nextInt(100)))
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
    public void 사용자_저장_Long() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
                        .loanAmt(Long.valueOf(new SecureRandom().nextInt(100)))
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
        Long result = userIdentity.stream()
                .filter(t->t.getLoanAmt() < 50)
                .mapToLong(UserIdentity::getLoanAmt)
                .sum();

        System.out.println("합산금액: " + result);
    }

    @Test
    @Transactional
    public void 사용자_저장_BigDecimal() {
        // given
        IntStream.rangeClosed(1, 10).forEach(index ->
                springDataJpaUserIdentityRepository.save(UserIdentity.builder()
                        .name("이름"+index)
                        .balanceAmt(new BigDecimal(new SecureRandom().nextInt(1000000)))
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
    public void 사용자_저장_조회() {

        // given
        UserIdentity userIdentity = UserIdentity.builder()
                .name("이름")
                .build();
        Long id = springDataJpaUserIdentityRepository.save(userIdentity).getId();

        // when
        UserIdentity result = springDataJpaUserIdentityRepository.findById(id).orElse(null);
        // then
        Assertions.assertThat(userIdentity).isEqualTo(result);
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
