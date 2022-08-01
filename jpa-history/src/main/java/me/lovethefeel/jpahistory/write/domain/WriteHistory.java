package me.lovethefeel.jpahistory.write.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
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

    private Timestamp created;

    private String createBy;

    protected WriteHistory() {}

    private WriteHistory(final Long writeId, final String writeName, final String comment) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.writeId = writeId;
        this.writeName = writeName;
        this.comment = comment;
        this.created = timestamp;
        this.createBy = writeName;
    }

    public static WriteHistory from(final Long writeId, final String writeName, final String comment) {
        return new WriteHistory(writeId, writeName, comment);
    }

    public static WriteHistory fromEntity(final Write write, final String comment) {
        return new WriteHistory(write.getId(), write.getWriteName(), comment);
    }

    @Override
    public String toString() {
        return "WriteHistory{" +
                "id=" + id +
                ", writeId=" + writeId +
                ", writeName='" + writeName + '\'' +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
