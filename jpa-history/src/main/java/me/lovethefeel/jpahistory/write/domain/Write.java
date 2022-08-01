package me.lovethefeel.jpahistory.write.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Entity
@Table(name = "writes")
public class Write {

    @Id
    @Column(name = "write_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "write_name")
    private String writeName;

    private Timestamp created;

    private String createBy;

    private Timestamp updated;

    private String updateBy;

    protected Write() {}

    private Write(final String writeName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.writeName = writeName;
        this.created = timestamp;
        this.createBy = writeName;
        this.updated = timestamp;
        this.updateBy = writeName;
    }

    public static Write fromCreate(final String writeName) {
        return new Write(writeName);
    }

    public void updateName(final String changeWriteName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.writeName = changeWriteName;
        this.updated = timestamp;
        this.updateBy = changeWriteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Write write = (Write) o;
        return Objects.equals(id, write.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
