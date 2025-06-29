package com.jaeho.CRUD_project.repository;

import com.jaeho.CRUD_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // username으로 사용자 조회
}