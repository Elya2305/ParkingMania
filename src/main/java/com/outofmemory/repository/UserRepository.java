package com.outofmemory.repository;

import com.outofmemory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Optional<User> findByLocalId(String localId);

    boolean existsByLocalId(String id);
}
