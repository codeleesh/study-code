package me.lovethefeel.twoway.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Line {

    @Id
    @GeneratedValue
    @Column(name = "line_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "line")
    private List<Subway> subways = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subway> getSubways() {
        return subways;
    }

    public void setSubways(List<Subway> subways) {
        this.subways = subways;
    }
}
