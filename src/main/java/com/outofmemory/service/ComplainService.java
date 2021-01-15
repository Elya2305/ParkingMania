package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;

import java.util.List;

public interface ComplainService {
    boolean add(ComplainDto dto);

    List<ComplainDto> all();
}
