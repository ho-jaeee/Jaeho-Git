package com.jaeho.CRUD_project.repository;

import com.jaeho.CRUD_project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
