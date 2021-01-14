package com.outofmemory.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ComplainInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private String photoUrl;
    private String autoNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @ManyToOne
    private User owner;
    @Enumerated(EnumType.STRING)
    private Status status;

    //todo add location

    public enum Status {
        NEW, CONFIRMED, REJECTED;
    }
}
