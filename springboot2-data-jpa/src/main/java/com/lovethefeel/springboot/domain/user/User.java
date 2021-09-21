package com.lovethefeel.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@NoArgsConstructor
@IdClass(UserKey.class)
@Entity(name = "TN_USER")
public class User extends BaseTimeEntity {
    @Id
    private String userId;

    @Id
    private String userName;

    @Column
    private int count;

    @Builder
    public User(String userId, String userName, Integer count) {
        this.userId = userId;
        this.userName = userName;
        this.count = count;
    }
}
