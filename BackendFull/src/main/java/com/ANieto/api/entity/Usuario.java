package com.ANieto.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Data
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 20)
    private String username;
    @Column(length = 60)
    private String password;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "Users_Authorities",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "rol_id"),uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","rol_id"})})
    private List<Rol> roles;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private static final long serialVersionUID = 1L;
}
