package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;

public interface ComplainService {
    ComplainInfo validateAndGetComplaint(ComplainDto dto);
}
