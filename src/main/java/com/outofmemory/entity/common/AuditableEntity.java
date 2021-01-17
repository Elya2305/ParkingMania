package com.outofmemory.entity.common;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

// todo dateCreated, dateModified

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @LastModifiedBy
    private String lastModifiedBy;
    @CreatedBy
    private String ownerId;
//    @CreatedDate
//    private Date dateCreated; // blob ?
//    @LastModifiedDate
//    private Date  dateModified;
}
