package com.outofmemory.service;

import com.outofmemory.dto.complaint.ComplainDto;
import com.outofmemory.dto.complaint.ComplaintIdStatusDto;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.utils.api.PageDto;

public interface ComplainService {
    boolean add(ComplainDto dto);

    boolean update(ComplainDto dto);

    boolean delete(Integer id);

    boolean changeStatus(ComplaintIdStatusDto dto);

    PageDto<ComplainDto> allOfCurrent(ComplaintStatus status, int page, int pageSize);

    PageDto<ComplainDto> allByStatus(ComplaintStatus status, int page, int pageSize);
}
