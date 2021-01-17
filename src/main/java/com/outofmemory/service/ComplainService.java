package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;

import java.util.List;

public interface ComplainService {
    boolean add(ComplainDto dto);

    boolean update(ComplainDto dto);

    boolean delete(Integer id);

    List<ComplainDto> all();
}
