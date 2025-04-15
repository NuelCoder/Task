package com.project.projectapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.projectapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);

}
