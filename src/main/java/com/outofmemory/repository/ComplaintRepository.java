package com.outofmemory.repository;

import com.outofmemory.entity.ComplainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<ComplainInfo, Integer> {
}
