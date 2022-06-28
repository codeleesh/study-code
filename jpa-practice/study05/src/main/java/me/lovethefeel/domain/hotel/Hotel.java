package me.lovethefeel.domain.hotel;

import jakarta.persistence.*;
import me.lovethefeel.domain.common.Address;

import java.time.LocalDateTime;

/**
 * @Embeddable
 * - 엔티티가 아닌 타입을 한 개 이상의 필드와 매핑할 때 사용
 * - 엔티티의 한 속성으로 @ㄷEmbeddable 적용 타입 사용
 *
 * 같은 @Embeddable 타입 필드가 두 개인 경우?
 * - 서로 같은 컬럼을 매핑하기 때문에
 * - 에러 발생 : Repeated column
 * - 해당 문제를 해결하기 위해서는 @AttributeOverride으로 설정 재정의
 *
 * 정리
 * - @Embeddable을 사용하면 모델을 더 잘 표현할 수 있음
 * - 개별 속성을 모아서 이해 -> 타입으로 더 쉽게 이해
 */

@Entity
@Table(name = "hotel_info")
public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private String id;

    @Column(name = "hotel_nm")
    private String name;

    @Column(name = "hotel_year")
    private int year;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime lastModified;

    protected Hotel() {
    }

    public Hotel(String id, String name, int year, Grade grade, Address address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
        this.address = address;
        this.created = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
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

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
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

