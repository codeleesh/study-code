package me.lovethefeel.member.repository;

import me.lovethefeel.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class MemberRepository {

    private static ConcurrentMap<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(final Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store의 Value List를 보호하기 위해서..
        return new ArrayList<>(store.values());
    }

    public Member registerById(final Long id) {
        Member member = store.get(id);
        member.successRegisterMember();
        return member;
    }

    public void clearStore() {
        store.clear();
    }
}
