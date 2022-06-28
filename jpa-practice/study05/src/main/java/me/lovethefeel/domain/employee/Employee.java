package me.lovethefeel.domain.employee;

import jakarta.persistence.*;
import me.lovethefeel.domain.common.Address;

/**
 * @AttributeOverride
 * - name 속성은 @Embeddable의 타입 속성
 * - column 속성은 변경할 필드 속성
 */

@Entity
public class Employee {
    @Id
    private String id;

    @Embedded
    private Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "address1", column = @Column(name = "waddr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "waddr2")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "wzipcode"))
    })
    @Embedded
    private Address workAddress;

    protected Employee() {
    }

    public Employee(String id, Address homeAddress, Address workAddress) {
        this.id = id;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public String getId() {
        return id;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }
}
