package com.outofmemory.entity;

import com.outofmemory.dto.LocationDto;
import com.outofmemory.entity.common.AuditableEntity;
import com.outofmemory.utils.converer.impl.LocationConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

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
    private ComplaintStatus status = ComplaintStatus.NEW;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = LocationConverter.class)
    private LocationDto location;

    private String locationAddress;

}
