package ru.jm.CrudSpringBoot.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.

@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role() {
    }
    public Role(String role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
