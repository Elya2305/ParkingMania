package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;

import java.util.List;

public interface ComplainService {
    boolean add(ComplainDto dto);

    boolean update(ComplainDto dto);

    boolean delete(Integer id);

    List<ComplainDto> allOfCurrent(ComplainInfo.Status status);

    List<ComplainDto> all(ComplainInfo.Status status);
}
