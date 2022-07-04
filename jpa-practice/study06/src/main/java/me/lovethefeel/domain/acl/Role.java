package me.lovethefeel.domain.acl;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 값 콜렉션 Set 매핑
 *  - 단순 값을 Set으로 보관하는 모델
 */

@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "role_perm",
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "perm")
    private Set<String> permissions;

    protected Role() {
    }

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void revokeAll() {
        this.permissions.clear(); // SELECT -> DELETE
        // this.permissions = new HashSet<>(); // DELETE
    }

    public void setPermissions(Set<String> newPermissions) {
        this.permissions = newPermissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
