package me.lovethefeel.jpahistory.member.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    private Timestamp created;

    private String createBy;

    private Timestamp updated;

    private String updateBy;

    protected Member() {}

    private Member(final String memberName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.memberName = memberName;
        this.created = timestamp;
        this.createBy = memberName;
        this.updated = timestamp;
        this.updateBy = memberName;
    }

    public static Member fromCreate(final String memberName) {
        return new Member(memberName);
    }

    public void updateName(final String changeMemberName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.memberName = changeMemberName;
        this.updated = timestamp;
        this.updateBy = changeMemberName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
