package com.outofmemory.dto.complaint;

import com.outofmemory.entity.ComplaintStatus;
import lombok.Data;

@Data
public class ComplaintIdStatusDto {
    private Integer id;
    private ComplaintStatus status;
}
