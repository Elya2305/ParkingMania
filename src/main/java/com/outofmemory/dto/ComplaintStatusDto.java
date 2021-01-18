package com.outofmemory.dto;

import com.outofmemory.entity.ComplainInfo;
import lombok.Data;

@Data
public class ComplaintStatusDto {
    private ComplainInfo.Status status;
}
