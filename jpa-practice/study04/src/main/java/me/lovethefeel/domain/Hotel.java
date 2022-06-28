package me.lovethefeel.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.*;

/**
 * 엔티티 클래스 제약 조건
 * - @Entity 적용 필요
 * - @Id 적용 필요
 * - 인자 없는 기본 생성자 필요
 *  - 기본 생성자는 public이나 protected로 설정
 *  - jsr 표준으로는 protected
 * - 최상위 클래스로 설정
 * - final이면 안됨
 */

/**
 * 접근 타입
 * - 프로퍼티 접근 방법 보다는 필드 접근 방식 사용
 * - 설정 방법
 *   - @Id 애노테이션을 필드에 붙이면 필드 접근
 *   - @Id 애노테이션을 getter 메서드에 붙이면 프로퍼티 접근
 *   - @Access 애노테이션을 사용해서 명시적으로 지정
 *     - 클래스/개별 필드에 적용 가능
 *     - @Access(AccessType.PROPERTY) / @Access(AccessType.FIELD)
 */

@Entity
@Table(name = "hotel_info")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private String id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_year")
    private int year;

    @Column(name = "hotel_grade")
    @Enumerated(STRING)
    private Grade grade;

    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime lastModified;

    protected Hotel() {
    }

    public Hotel(final String id, String name, final int year, final Grade grade) {
        final LocalDateTime localDateTime = LocalDateTime.now();
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
        this.created = localDateTime;
        this.lastModified = localDateTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return year == hotel.year && Objects.equals(id, hotel.id) && Objects.equals(name, hotel.name) && grade == hotel.grade && Objects.equals(created, hotel.created) && Objects.equals(lastModified, hotel.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, grade, created, lastModified);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", grade=" + grade +
                ", created=" + created +
                ", lastModified=" + lastModified +
                '}';
    }
}
