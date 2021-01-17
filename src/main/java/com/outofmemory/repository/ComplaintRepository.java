package com.outofmemory.repository;

import com.outofmemory.entity.ComplainInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<ComplainInfo, Integer> {
    Optional<ComplainInfo> findById(Integer id);

    @Query("select ci.status from ComplainInfo ci where ci.id =:id")
    ComplainInfo.Status getStatusById(@Param("id") Integer id);
}
