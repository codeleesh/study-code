package me.lovethefeel.jpahistory.member.domain;

import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
public class MemberHistory {

    @Id
    @Column(name = "member_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "comment")
    private String comment;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    protected MemberHistory() {}

    private MemberHistory(final Long id, final Long memberId, final String memberName, final String comment
            , final Timestamp created, final String createBy) {

        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.comment = comment;
        this.created = created;
        this.createBy = createBy;
    }

    private MemberHistory(final Long memberId, final String memberName, final String comment) {

        this(null, memberId, memberName, comment, null, null);
    }

    public static MemberHistory from(final Long memberId, final String memberName, final String comment) {
        return new MemberHistory(memberId, memberName, comment);
    }

    public static MemberHistory fromEntity(final Long id, final Member member, final String comment) {
        return new MemberHistory(id, member.getMemberName(), comment);
    }

    public static MemberHistory fromEntity(final Member member, final String comment) {
        return new MemberHistory(member.getId(), member.getMemberName(), comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberHistory that = (MemberHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
