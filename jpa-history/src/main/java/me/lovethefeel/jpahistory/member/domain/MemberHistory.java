package me.lovethefeel.jpahistory.member.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
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

    private Timestamp created;

    private String createBy;

    protected MemberHistory() {}

    private MemberHistory(final Long memberId, final String memberName, final String comment) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.memberId = memberId;
        this.memberName = memberName;
        this.comment = comment;
        this.created = timestamp;
        this.createBy = memberName;
    }

    public static MemberHistory from(final Long memberId, final String memberName, final String comment) {
        return new MemberHistory(memberId, memberName, comment);
    }

    public static MemberHistory fromEntity(final Member member, final String comment) {
        return new MemberHistory(member.getId(), member.getMemberName(), comment);
    }
}
