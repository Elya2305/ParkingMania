package com.outofmemory.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ComplainDto {
    private String userUuid;
    private MultipartFile photo;
    private String description;
    private String autoNumber;
    private LocationDto location;
}
