package com.lovethefeel.webflux.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Table(name = "users")
@Entity(name = "users")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(STRING)
    private Sex sex;

    @Embedded
    private MobilePhone mobilePhone;

    public static User from(final Long id, final String userId, final String userName, final Sex sex, final MobilePhone mobilePhone) {
        return new User(id, userId, userName, sex, mobilePhone);
    }

    public static User from(final String userId, final String userName, final Sex sex, final MobilePhone mobilePhone) {
        return new User(null, userId, userName, sex, mobilePhone);
    }
}
