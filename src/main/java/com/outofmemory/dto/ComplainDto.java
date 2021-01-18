package com.outofmemory.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ComplainDto {
    private Integer id;
//    private MultipartFile photo; // todo
    private String description;
    private String autoNumber;
    private String address;
    private LocationDto location;
}
