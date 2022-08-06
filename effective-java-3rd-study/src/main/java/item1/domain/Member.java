package item1.domain;

import java.time.LocalDateTime;

public class Member {

    private Long id;
    private String memberName;
    private MemberStatus memberStatus;
    private int age;
    private String address;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Member(Long id, String memberName, int age, String address) {
        final LocalDateTime now = LocalDateTime.now();
        this.id = id;
        this.memberName = memberName;
        this.memberStatus = MemberStatus.INPROGRESS;
        this.age = age;
        this.address = address;
        this.created = now;
        this.updated = now;
    }

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}
