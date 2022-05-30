package me.lovethefeel.twoway.domain;

import javax.persistence.*;

@Entity
public class Subway {

    @Id
    @GeneratedValue
    @Column(name = "subway_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id") // 객체와 테이블의 FK와 매핑
    private Line line;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", line=" + line +
                ", name='" + name + '\'' +
                '}';
    }
}
