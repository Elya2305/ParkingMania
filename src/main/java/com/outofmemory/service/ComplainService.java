package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;

import java.util.List;

public interface ComplainService {
    boolean add(ComplainDto dto);

    boolean update(ComplainDto dto);

    boolean delete(Integer id);

    List<ComplainDto> allOfCurrent(ComplaintStatus status);

    List<ComplainDto> allByStatus(ComplaintStatus status);
}
