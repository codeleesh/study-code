package me.lovethefeel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity  // DB 테이블과 매핑 대상
@Table(name = "t_user")  // 테이블과 매핑 대상
public class User {
    @Id // 식별자에 대응
    private String email;
    private String name;
    @Column(name = "create_date")
    private LocalDateTime createDate;

    protected User() {
    }

    public User(String email, String name, LocalDateTime createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, createDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
