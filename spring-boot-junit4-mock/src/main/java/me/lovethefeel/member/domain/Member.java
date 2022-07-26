package me.lovethefeel.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    private LocalDateTime created;

    protected Member() {}

    private Member(final Long id, final String memberName, final LocalDateTime created) {
        this.id = id;
        this.memberName = memberName;
        this.created = created;
    }

    public static Member from(final String memberName) {
        return new Member(null, memberName, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", created=" + created +
                '}';
    }
}
