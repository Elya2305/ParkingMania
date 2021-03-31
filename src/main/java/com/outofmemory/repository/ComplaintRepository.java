package com.outofmemory.repository;

import com.outofmemory.entity.ComplainInfo;
import com.outofmemory.entity.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComplaintRepository extends JpaRepository<ComplainInfo, Integer> {
    Page<ComplainInfo> findAllByOwnerIdAndStatus(String ownerId, ComplaintStatus status, Pageable pageable);

    Page<ComplainInfo> findAllByStatus(ComplaintStatus status, Pageable pageable);

    Page<ComplainInfo> findAllByOwnerId(String ownerId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ComplainInfo c set c.status=:status where c.id=:id")
    void updateStatus(ComplaintStatus status, Integer id);

    @Query("select c.status from ComplainInfo c where c.id=:id")
    ComplaintStatus getStatusById(Integer id);
}
