package me.lovethefeel.jpahistory.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@ToString
@AllArgsConstructor(access = PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    @LastModifiedDate
    private Timestamp updated;

    private String updateBy;

    protected Member() {}

    private Member(final String memberName) {

        this(null, memberName, null, memberName, null, memberName);
    }
    public static Member fromCreate(final String memberName) {
        return new Member(memberName);
    }

    public static Member ofCreate(final Long id, final String memberName, final Timestamp created, final String createBy
            , final Timestamp updated, final String updateBy) {

        return new Member(id, memberName, created, createBy, updated, updateBy);
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
