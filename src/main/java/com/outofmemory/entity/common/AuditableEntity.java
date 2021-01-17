package com.outofmemory.entity.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

// todo

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedBy
    private String ownerId;
    @CreatedDate
    private Date dateCreated; // blob ?
    @LastModifiedDate
    private Date  dateModified;
}
