package com.outofmemory.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String uuid;
    private String username;
    @OneToMany(mappedBy = "owner")
    private List<ComplainInfo> complaints;
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    public void addComplain(ComplainInfo info) {
        this.complaints.add(info);
    }
}
