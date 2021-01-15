package com.outofmemory.entity;

import com.outofmemory.entity.common.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ComplainInfo extends AuditableEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private String photoUrl;
    private String autoNumber;
    @Enumerated(EnumType.STRING)
    private Status status;

    //todo add location

    public enum Status {
        NEW, CONFIRMED, REJECTED;
    }
}
