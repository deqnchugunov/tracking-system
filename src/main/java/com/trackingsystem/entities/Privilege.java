package com.trackingsystem.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "privileges")
public class Privilege {

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
