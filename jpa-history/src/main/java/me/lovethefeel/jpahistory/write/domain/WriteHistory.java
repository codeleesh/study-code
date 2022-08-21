package me.lovethefeel.jpahistory.write.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Entity
@ToString
@AllArgsConstructor(access = PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class WriteHistory {

    @Id
    @Column(name = "write_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "write_id")
    private Long writeId;

    @Column(name = "write_name")
    private String writeName;

    @Column(name = "comment")
    private String comment;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    protected WriteHistory() {}

    private WriteHistory(final Long writeId, final String writeName, final String comment) {

        this(null, writeId, writeName, comment, null, null);
    }

    public static WriteHistory from(final Long writeId, final String writeName, final String comment) {
        return new WriteHistory(writeId, writeName, comment);
    }

    public static WriteHistory fromEntity(final Write write, final String comment) {
        return new WriteHistory(write.getId(), write.getWriteName(), comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WriteHistory that = (WriteHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
