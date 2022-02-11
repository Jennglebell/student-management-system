package com.example.shiro_mysql_integrate.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String descrirption;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;

    public Permission(Long id, String name, String descrirption) {
        this.id = id;
        this.name = name;
        this.descrirption = descrirption;
    }

    public Permission() {
    }

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

    public String getDescrirption() {
        return descrirption;
    }

    public void setDescrirption(String descrirption) {
        this.descrirption = descrirption;
    }
}
