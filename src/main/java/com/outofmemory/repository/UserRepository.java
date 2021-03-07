package com.outofmemory.repository;

import com.outofmemory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLocalId(String localId);

    boolean existsByLocalId(String id);

    @Transactional
    @Modifying
    @Query("update User usr set usr.status=:status where usr.localId=:localId")
    void updateUserStatus(@Param("localId") String localId, @Param("status") User.Status status);
}
