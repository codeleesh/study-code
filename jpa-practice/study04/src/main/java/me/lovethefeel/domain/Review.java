package me.lovethefeel.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private String hotelId;

    private int mark;

    @Column(name = "writer_name")
    private String writerName;
    private String comment;
    private LocalDateTime created;

    protected Review() {
    }

    public Review(final String hotelId, final int mark, final String writerName, final String comment) {
        this.hotelId = hotelId;
        this.mark = mark;
        this.writerName = writerName;
        this.comment = comment;
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public int getMark() {
        return mark;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", hotelId='" + hotelId + '\'' +
                ", mark=" + mark +
                ", writerName='" + writerName + '\'' +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                '}';
    }
}
