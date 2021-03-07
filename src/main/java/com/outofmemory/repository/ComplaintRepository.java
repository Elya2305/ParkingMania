package com.outofmemory.repository;

import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<ComplainInfo, Integer> {
    Optional<ComplainInfo> findById(Integer id);

    List<ComplainInfo> findAllByOwnerIdAndStatus(String ownerId, ComplaintStatus status);

    List<ComplainInfo> findAllByStatus(ComplaintStatus status);

    @Query("select c.status from ComplainInfo c where c.id=:id")
    ComplaintStatus getStatusById(Integer id);
}
