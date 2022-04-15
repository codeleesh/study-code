package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private Member(final Long id, final String name, final Address address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public static Member from(final String name) {
        return new Member(null, name, null, Collections.emptyList());
    }

    public static Member of(final String name, final Address address) {
        return new Member(null, name, address, Collections.emptyList());
    }
}
