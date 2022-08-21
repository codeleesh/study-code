package me.lovethefeel.jpahistory.write.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Entity
@Table(name = "writes")
@ToString
@AllArgsConstructor(access = PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Write {

    @Id
    @Column(name = "write_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "write_name")
    private String writeName;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    @LastModifiedDate
    private Timestamp updated;

    private String updateBy;

    protected Write() {}

    private Write(final String writeName) {

        this(null, writeName, null, writeName, null, writeName);
    }

    public static Write fromCreate(final String writeName) {
        return new Write(writeName);
    }

    public static Write ofCreate(final Long id, final String writeName, final Timestamp created, final String createBy
            , final Timestamp updated, final String updateBy) {

        return new Write(id, writeName, created, createBy, updated, updateBy);
    }

    public void updateName(final String changeWriteName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.writeName = changeWriteName;
        this.updated = timestamp;
        this.updateBy = changeWriteName;
    }
}
