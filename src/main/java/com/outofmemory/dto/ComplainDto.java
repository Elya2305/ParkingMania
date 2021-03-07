package com.outofmemory.dto;

import com.outofmemory.entity.ComplaintStatus;
import lombok.Data;

@Data
public class ComplainDto {
    private Integer id;
//    private MultipartFile photo; // todo
    private String description;
    private String autoNumber;
    private String address;
    private LocationDto location;
    private ComplaintStatus status;
}
