package com.outofmemory.service;

import com.outofmemory.dto.ComplainDto;
import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import com.outofmemory.utils.api.PageDto;

import java.util.List;

public interface ComplainService {
    boolean add(ComplainDto dto);

    boolean update(ComplainDto dto);

    boolean delete(Integer id);

    PageDto<List<ComplainDto>> allOfCurrent(ComplaintStatus status, int page, int pageSize);

    PageDto<List<ComplainDto>> allByStatus(ComplaintStatus status, int page, int pageSize);
}
